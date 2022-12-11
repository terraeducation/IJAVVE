package robot;

import java.util.ArrayList;

import automodules.AutoModule;
import geometry.framework.CoordinatePlane;
import geometry.position.Pose;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.input.IEncoder;
import teleutil.independent.Independent;
import teleutil.independent.IndependentFunctions;
import teleutil.independent.Machine;
import util.TerraThread;
import util.User;
import util.template.Iterator;

import static global.General.*;
import static robot.RobotUser.gyro;
import static robot.RobotUser.odometry;

public class RobotFramework {
    /**
     * The allRobotParts arraylist contains all of the robotParts in the robot,
     * The RobotPart constructor automatically adds itself to this static arraylist
     */
    public static ArrayList<RobotPart> allRobotParts;
    /**
     * The localPlane Coordinate plane represents the robots local perspective
     */
    public CoordinatePlane localPlane;
    /**
     * The terrathread robotFunctionsThread is used for running robotfunction related tasks,
     * it is usually not necessary to access this directly
     */
    public static TerraThread robotFunctionsThread;
    /**
     * The odometry thread is used to update odometry
     */
    public static TerraThread odometryThread;
    /**
     * Background thread
     */
    public static TerraThread backgroundThread;
    /**
     * Independent thread
     */
    public static TerraThread independentThread;
    /**
     * rfsHandler is used for running rfs code. Stages can be added to the queue
     */
    public RobotFunctions rfsHandler;

    /**
     * Background functions handler
     */
    public BackgroundFunctions backHandler;

    /**
     * Independent Handler
     */
    public IndependentFunctions indHandler;

    public Machine machine;

    /**
     * Configs object, stores all configs
     */
    public final Configs configs = new Configs();

    /**
     * Constructor for creating a robotFramework, this should be overridden by terraBot by extending this class
     * Objects are instantiated here, and rfsHandler is also initialized, which sets the update code
     */
    protected RobotFramework(){
        allRobotParts = new ArrayList<>();
        TerraThread.resetAllThreads();
        configs.setCurrentConfig();
        localPlane = new CoordinatePlane();
        rfsHandler = new RobotFunctions();
        backHandler = new BackgroundFunctions();
        indHandler = new IndependentFunctions();
        machine = new Machine();
        robotFunctionsThread = new TerraThread("RobotFunctionsThread", Constants.ROBOT_FUNCTIONS_REFRESH_RATE);
        odometryThread = new TerraThread("OdometryThread", Constants.ODOMETRY_THREAD_REFRESH_RATE);
        backgroundThread = new TerraThread("BackgroundThread", Constants.BACKGROUND_THREAD_REFRESH_RATE);
        independentThread = new TerraThread("IndependentThread", Constants.INDEPENDENT_THREAD_REFRESH_RATE);
        rfsHandler.init();
        backHandler.init();
        indHandler.init();
    }

    /**
     * This method is run from TerraBot and this initializes all of the robotparts, sets the user to main user
     * It then starts the robotfunctionsthread, odometry thread, and resets the game timer
     * NOTE: Threads are started in init to prevent lag in start
     */
    public void init(){
        setUser(mainUser);
        IEncoder.setEncoderReadingAuto();
        Iterator.forAll(allRobotParts, RobotPart::init);
        TerraThread.startAllThreads();
        gameTime.reset();
    }

    /**
     * Start, starts the robotfunctions by "resuming" (resume and start are the same thing)
     */
    public void start() {
        rfsHandler.resume();
        Iterator.forAll(allRobotParts, RobotPart::reset);
    }

    public void update(){
        checkAccess(mainUser);
        TerraThread.checkAllThreadsForExceptions();
        machine.update();
    }
    /**
     * the stop method stops updating threads, and halts the robot
     * @link halt
     */
    public void stop(){
        cancelAll();
        TerraThread.stopUpdatingAllThreads();
        halt();
    }

    /**
     * Stops all of the robotparts by setting all of the cmotors and cservos to 0 power
     * NOTE: This will only work if the current user is main user, do NOT use this in a thread
     */
    public void halt(){ Iterator.forAll(allRobotParts, RobotPart::halt);}

    /**
     * Adds an automodule (or list of stages) to the robotfunctions to add it to the queue
     * @param autoModule
     */
    public void addAutoModule(AutoModule autoModule){
        rfsHandler.addAutoModule(autoModule);
    }

    public void addBackgroundTask(BackgroundTask backgroundTask){
        backHandler.addBackgroundTask(backgroundTask);
    }

    /**
     * Sets the user to the new user specified
     * NOTE: This does not change the access of the user which must be updated explicity with checkAccess
     * @param newUser
     */
    public synchronized void setUser(User newUser){ Iterator.forAll(allRobotParts, part -> part.switchUser(newUser)); }

    /**
     * Checks the access of the potential user to all of the robot parts
     * This should be called every time a user wants to use the robot, to check if the current user privileges are updated
     * @param potentialUser
     */
    public synchronized void checkAccess(User potentialUser){ Iterator.forAll(allRobotParts, part -> part.checkAccess(potentialUser)); }

    /**
     * Cancel all of the automodules by emptying the robot functions queue
     */
    public void cancelAutoModules(){
        setUserMainAndHalt();
        rfsHandler.emptyQueue();
    }

    public void cancelBackgroundTasks(){
        setUserMainAndHalt();
        backHandler.emptyTaskList();
    }

    public void cancelIndependents(){
        setUserMainAndHalt();
        indHandler.stopCurrentIndependent();
    }

    public void cancelMovements(){
        cancelAutoModules();
        cancelIndependents();
        cancelMachine();
    }

    /**
     * Set the user to main and halt all of the robot parts that aren't the main user
     */
    public void setUserMainAndHalt() {
        Iterator.forAll(allRobotParts, part -> {
            if (!part.getUser().equals(mainUser)){
                part.switchUser(mainUser);
                part.checkAccess(mainUser);
                part.halt();
            }
        });
    }

    /**
     * Resume the automodules
     */
    public void resumeAutoModules(){ rfsHandler.resume(); }

    /**
     * Pause the automodules
     */
    public void pauseAutoModules() { rfsHandler.pauseNow(); }


    public void addIndependent(Independent independent){ indHandler.runIndependent(independent); }

    public void addMachine(Machine machine){ this.machine = machine; this.machine.activate(); }

    public void cancelMachine(){ this.machine.cancel(); }

    public void cancelAll(){ cancelMovements(); cancelBackgroundTasks();  }

    public void savePose(Pose pose){
        storage.addItem("XPos", pose.getX()); storage.addItem("YPos", pose.getY()); storage.addItem("Heading", pose.getAngle()); storage.saveItems();
    }

    public void loadPose(){
        Pose savedPose = new Pose((double) storage.getItem("XPos").getValue(), (double) storage.getItem("YPos").getValue(), (double) storage.getItem("Heading").getValue()); odometry.setCurrentPose(savedPose); gyro.setHeading(savedPose.getAngle());
    }
}
