        package auton.unused;


import static autoutil.vision.PixelScannerIntegrate.locations;
import static autoutil.vision.Scanner.RED;
import static global.General.bot;
import static global.General.telemetry;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import automodules.stage.Exit;
import autoutil.AutoFramework;
import autoutil.vision.PixelScannerIntegrate;
import robotparts.RobotPart;
import util.Timer;

//@Autonomous(name = "REDFAR STAGE (3)", group = "auto", preselectTeleOp = "TerraOp")
public class RED_FAR_STACK_THREE extends AutoFramework {
    Timer time = new Timer();
    public boolean timeout(){return time.seconds() > 12;}

    public Exit exitIntake(){return new Exit(this::timeout);}

    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();

//        propCaseDetected = TeamProp.THIRD;

        AutoFramework auto = this;
        auto.scan(true, "red", "left");

    }
        AutoModule Extake = new AutoModule(
                intake.stageStart(.2),
                intake.stageInit(.2)
        );



    AutoModule align = new AutoModule(
            drive.strafeSmart(0,-0.6,0),
            drive.driveSmart(-.2,0,0)
    );

    AutoModule ReadyIntake = new AutoModule(
            outtake.stageLock(.1).attach(outtake.stageStartPivot(.1)),
            outtake.stageOpen(.3)
    ).setStartCode(() ->{}
    );
    AutoModule StackIntake = new AutoModule(


//            intake.stageStart(.2),
//            drive.moveTime(-.3,0,0,.5),
//            drive.moveTime(0,-0.02,0,.3),


            intake.moveSmart(-.53, exitIntake()).attach(drive.intakeSmart2(0,0,0.02, exitIntake())),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );

    AutoModule StackIntakefirst2 = new AutoModule(
            intake.stageStart(.1),
            intake.moveSmart2(-.6).attach(drive.intakeSmart(0,0.15,0)),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );
    AutoModule StackIntake3 = new AutoModule(



            intake.stageStart(.2),
            drive.moveTime(0,0.06,0,.5),



            intake.moveSmart2(-.6).attach(drive.intakeSmart(0,-0.15,0)),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)
    ).setStartCode(() -> {
//        time.reset();
    });
    AutoModule StackIntake2 = new AutoModule(



            intake.stageStart(.2),
            drive.moveTime(0,0.06,0,.5),


            intake.moveSmart2(-.6).attach(drive.intakeSmart(0.0,0.15,-0.01)),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)
    ).setStartCode(() -> {
//        time.reset();
    });

//    AutoModule StackIntake3 = new AutoModule(
//
//
//            intake.moveSmart(-.7).attach(drive.intakeSmart(0,-.17,-0.02))
////            intake.moveTime(-0.5, .2),
////            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3))
//    ).setStartCode(() -> {
//
//    });


    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))



    );

    AutoModule PreExtend1 = new AutoModule(
            lift.stageLift(1, 22).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4)),
            outtake.stageStackRotate(.1)



    );
    AutoModule PreExtend3 = new AutoModule(
            lift.stageLift(1, 22).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))



    );
    AutoModule whitePixel = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2))




    );

    AutoModule whitePixelFinal = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2)),
            outtake.stageStackRotate(.1)
//            outtake.stageStackRotate(.1)




    );
    AutoModule whitePixel2 = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.2))




    );
    AutoModule lock = new AutoModule(
            intake.stageStart(.1)




    );
    AutoModule RemovePixels = new AutoModule(

            intake.moveTime(.8,.3)
    );
    AutoModule RemovePixels2 = new AutoModule(
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.6,.3)
    );
    AutoModule intakereset = new AutoModule(
            intake.stageInit(.1)
    );
    AutoModule open2 = new AutoModule(
            outtake.stageOpen2(.1)
    );

    AutoModule open1 = new AutoModule(
            outtake.stageOpen1(.1)
    );

    AutoModule flippy = new AutoModule(
            outtake.stageflipStackRotate(.1)
    );

    AutoModule flippy2 = new AutoModule(
            outtake.stageflipStackRotate(.1),
            lift.stageLift(1,18)

    );
    AutoModule Reset = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );
    //TODO MAKE DISTANCE SENSOR ADJUST Y COORDINATE
    @Override
    public void define() {
        time.reset();


        customCase(() -> {


            addTimedSetpoint(1.0,.5,1,-3,-45,0);

            addTimedSetpoint(1.0,.5,1,-19.2,-75,90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,.8,-5,-130,90);


            addWaypoint(0,-140,90);

            addConcurrentAutoModule(ReadyIntake);

            addTimedSetpoint(1,1,.8,41,-125,95);
//            addPause(1.2);
            addAutoModule(lock);
            addPause(.2);
            addTimedSetpoint(1,.4,.5,29,-122,95);
            addTimedSetpoint(1,.4,.5,32,-122,95);



            addAutoModule(StackIntake);

            addBreakpoint(()-> time.seconds() < 11);
            addTimedSetpoint(1,1,.8,42,-125,95);
//            addPause(1.2);
            addAutoModule(lock);
            addTimedSetpoint(1,.4,.5,36,-122,95);



            addAutoModule(StackIntake);
            addBreakpointReturn();




            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,0,-130,90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-145,-130,80);

            addConcurrentAutoModule(PreExtend1);



            addSegment(1,DefaultWP,-180,-105,30);







            addTimedSetpoint(1.0,1,1.6,-210,-75,91);
            addAutoModule(align);
            addAutoModule(open2);

            addAutoModule(flippy);

            addTimedSetpoint(1.0,1,.5,-210,-90,91);
            addAutoModule(align);





            addConcurrentAutoModule(Reset);

            //GOES FOR NEXT CYCLE


            addSegment(1,DefaultWP,-175,-90,40);
            addSegment(1,DefaultWP,-120,-130,90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-80,-130,90);




            addSegment(.7,DefaultWP,-45,-130,90);


            addBreakpoint(()-> time.seconds() > 23);

            addSegment(.2,DefaultWP,47.3,-115,92);


//
            addAutoModule(StackIntake2);
            addConcurrentAutoModule(RemovePixels);






            addSegment(1,DefaultWP,0,-130,90);
            addBreakpointReturn();

            addConcurrentAutoModule(intakereset);
            addBreakpoint(()-> time.seconds() > 28);

            addSegment(1,DefaultWP,-136,-130,80);



            addConcurrentAutoModule(whitePixel);

            addSegment(1,DefaultWP,-170,-100,50);

            addTimedSetpoint(1,.2,.5,-210,-65,90);


            addAutoModule(align);
            addPause(.2);


            addAutoModule(Reset);
            addBreakpointReturn();
            addTimedSetpoint(1,.2,.5,-230,-65,90);

















        }, () -> {

            addWaypoint(10,-30,0);

            addWaypoint(33,-100,-95);
            addTimedSetpoint(1,.5,1.2,-5,-100,-97);

            addTimedSetpoint(1,.5,.8,12,-94,-97);




            addAutoModule(Extake);

            addWaypoint(0,-140,90);

            addConcurrentAutoModule(ReadyIntake);

            addTimedSetpoint(1,.8,1,25,-125,95);
            addTimedSetpoint(1,.8,.7,36,-125,95);

//            addPause(1.2);
            addAutoModule(lock);
            addPause(.2);
            addTimedSetpoint(1,.5,.5,9,-125,95);
            addTimedSetpoint(1,.4,.5,17,-122,90);



            addAutoModule(StackIntake);

            addBreakpoint(()-> time.seconds() <12);
            addTimedSetpoint(1,1,.8,27,-122,95);
//            addPause(1.2);
            addAutoModule(lock);
            addTimedSetpoint(1,.4,.5,9,-122,95);
            addTimedSetpoint(1,.4,.5,17,-122,90);




            addAutoModule(StackIntake);
            addBreakpointReturn();




            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,0,-122,90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-145,-122,80);

            addConcurrentAutoModule(PreExtend3);



            addSegment(1,DefaultWP,-180,-100,30);




            addTimedSetpoint(1.0,1,1.6,-210,-32,91);



            addAutoModule(align);



            addConcurrentAutoModule(Reset);

            //GOES FOR NEXT CYCLE


            addSegment(1,DefaultWP,-190,-90,90);
            addSegment(1,DefaultWP,-170,-110,90);
            addSegment(1,DefaultWP,-150,-122,90);


//            addSegment(1,DefaultWP,-120,-125,90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-80,-122,90);




            addSegment(.7,DefaultWP,-45,-122,90);


            addBreakpoint(()-> time.seconds() > 24);

            addSegment(.2,DefaultWP,32,-105,89);


//
            addAutoModule(StackIntakefirst2);
            addConcurrentAutoModule(RemovePixels);






            addSegment(1,DefaultWP,0,-125,90);
            addBreakpointReturn();

            addConcurrentAutoModule(intakereset);
            addBreakpoint(()-> time.seconds() > 28);

            addSegment(1,DefaultWP,-136,-122,80);



            addConcurrentAutoModule(whitePixel);
            addBreakpoint(()-> time.seconds() > 27);

            addSegment(1,DefaultWP,-170,-100,50);

            addTimedSetpoint(1,.2,.5,-210,-34,88);


            addAutoModule(align);
            addPause(.2);


            addAutoModule(Reset);
            addBreakpointReturn();
            addTimedSetpoint(1,.2,.5,-230,-50,90);






        }, () -> {

            addWaypoint(0,-30,0);
            addWaypoint(0,-35,-20);

            addTimedSetpoint(1.0,4,.5,-5,-60,-95);

            addAutoModule(Extake);

            addTimedSetpoint(1.0,1,.8,-5,-130,90);


            addWaypoint(0,-140,90);

            addConcurrentAutoModule(ReadyIntake);

            addTimedSetpoint(1,.8,1,22,-125,95);
            addTimedSetpoint(1,.8,.7,29,-125,95);

//            addPause(1.2);
            addAutoModule(lock);
            addPause(.2);
            addTimedSetpoint(1,.5,.5,9,-125,95);
            addTimedSetpoint(1,.4,.5,16,-125,95);



            addAutoModule(StackIntake);

            addBreakpoint(()-> time.seconds() <12);
            addTimedSetpoint(1,1,.8,27,-125,95);
//            addPause(1.2);
            addAutoModule(lock);
            addTimedSetpoint(1,.4,.5,-11,-125,95);
            addTimedSetpoint(1,.4,.5,-7,-125,95);




            addAutoModule(StackIntake);
            addBreakpointReturn();




            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,0,-125,90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-145,-125,80);

            addConcurrentAutoModule(PreExtend1);



            addSegment(1,DefaultWP,-180,-105,30);







            addTimedSetpoint(1.0,1,1.6,-210,-45,90);
            addAutoModule(align);
            addAutoModule(open2);

            addAutoModule(flippy);

            addTimedSetpoint(1.0,1,.5,-210,-5,90);
            addAutoModule(align);





            addConcurrentAutoModule(Reset);

            //GOES FOR NEXT CYCLE


            addSegment(1,DefaultWP,-180,-125,90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-80,-125,90);




            addSegment(.7,DefaultWP,-45,-125,90);


            addBreakpoint(()-> time.seconds() > 23);

            addSegment(.2,DefaultWP,30,-105,92);


//
            addAutoModule(StackIntake2);
            addConcurrentAutoModule(RemovePixels);






            addSegment(1,DefaultWP,0,-125,90);
            addBreakpointReturn();

            addConcurrentAutoModule(intakereset);
            addBreakpoint(()-> time.seconds() > 28);

            addSegment(1,DefaultWP,-136,-125,80);



            addConcurrentAutoModule(whitePixel);
            addBreakpoint(()-> time.seconds() > 28);

            addSegment(1,DefaultWP,-170,-100,50);

            addTimedSetpoint(1,.2,.5,-210,-23,88);


            addAutoModule(align);
            addPause(.2);


            addAutoModule(Reset);
            addBreakpointReturn();
            addTimedSetpoint(1,.2,.5,-230,-65,90);























        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
