package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import automodules.stage.Exit;
import autoutil.AutoFramework;
import util.Timer;

@Autonomous(name = "REDFAR TRUSS (3)", group = "auto", preselectTeleOp = "TerraOp")


public class RED_FAR_STACK_THREE_TRUSS extends AutoFramework {
    Timer time = new Timer();
    public boolean timeout(){return time.seconds() > 12;}

    public Exit exitIntake(){return new Exit(this::timeout);}

    public boolean timeout2(){return time.seconds() > 20;}

    public Exit exitIntake2(){return new Exit(this::timeout2);}
    public boolean timeout3(){return time.seconds() > 27;}

    public Exit exitIntake3(){return new Exit(this::timeout3);}

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
            drive.strafeSmart(0,0.6,0),
            drive.driveSmart(-.2,0,0)
    );

    AutoModule ReadyIntake = new AutoModule(
            outtake.stageLock(.1).attach(outtake.stageStartPivot(.1)),
            outtake.stageOpen(.3)
    ).setStartCode(() ->{}
    );
    AutoModule StackIntake2 = new AutoModule(


//            intake.stageStart(.2),
//            drive.moveTime(-.3,0,0,.5),
//            drive.moveTime(0,-0.02,0,.3),


            intake.moveSmart(-.7, exitIntake3()).attach(drive.intakeSmart2(0.0,0.06,0.0, exitIntake3())),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );
    AutoModule StackIntake = new AutoModule(


            intake.stageMiddle(.2),
//            drive.moveTime(-.3,0,0,.5),
//            drive.moveTime(0,-0.02,0,.3),


            intake.moveSmart(-.57, exitIntake()).attach(drive.intakeSmart2(0.068,0.03,0.0, exitIntake())),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );
    AutoModule StackIntake1 = new AutoModule(


//            intake.stageStart(.2),
//            drive.moveTime(-.3,0,0,.5),
//            drive.moveTime(0,-0.02,0,.3),


            intake.moveSmart(-.53, exitIntake2()).attach(drive.intakeSmart2(0,0.1,0.0, exitIntake2()))


    ).setStartCode(()->{
//        time.reset();
            }
    );

    AutoModule StackIntakefirst2 = new AutoModule(
            intake.moveSmart2(-.7).attach(drive.intakeSmart(0,0.15,0))


    ).setStartCode(()->{
//        time.reset();
            }
    );
    AutoModule StackIntake3 = new AutoModule(







            intake.moveSmart(-.7, exitIntake3()).attach(drive.intakeSmart2(0.02,0.15,0, exitIntake3()))

    ).setStartCode(() -> {
//        time.reset();
    });




    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 20).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4)),
            outtake.stageflipStackRotate(.1)



    );

    AutoModule PreExtend1 = new AutoModule(
            lift.stageLift(1, 23).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4)),
            outtake.stageflipStackRotate(.1)



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
            lift.stageLift(1, 8).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.2))




    );
    AutoModule lock = new AutoModule(
            intake.stageStart(.2)




    );
    AutoModule RemovePixels = new AutoModule(
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),

            intake.moveTime(.8,.3),
            intake.stageInit(.1)
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
    @Override
    public void define() {
        time.reset();


        customCase(() -> {


            addTimedSetpoint(1.0,.5,1,0,-30,40);

            addAutoModule(Extake);
            addWaypoint(0,-20,90);



            addConcurrentAutoModule(ReadyIntake);
            addSegment(.8,DefaultWP,25,-30,60);
            addSegment(.4,DefaultWP,30,-54,82);

            addTimedSetpoint(1,.5,1,45,-60,82);
            addAutoModule(lock);

            addSegment(.4,DefaultWP,35,-30,30);
            addTimedSetpoint(1,.5,1,45,-30,40);

//
//
//
////
////
            addAutoModule(StackIntake);

            addConcurrentAutoModule(RemovePixels);

            addSegment(.8,DefaultWP, 0, -15 , 90);

            addSegment(.8,DefaultWP, -130, -15 , 90);

            addConcurrentAutoModule(PreExtend);

            addSegment(.8,DefaultWP, -200, -69 , 90);
            addTimedSetpoint(1,.8,1,-200,-92,90);
            addAutoModule(align);
            addAutoModule(open2);
            addAutoModule(open1);



            addConcurrentAutoModule(Reset);
            addBreakpoint(()-> time.seconds() > 27);

//            addSegment(.7,DefaultWP, -180, -15 , 90);
//            addConcurrentAutoModule(ReadyIntake);
//            addSegment(.8,DefaultWP, -60, -15 , 90);
//
//
//            addSegment(.8,DefaultWP, -10, -15 , 90);
//            addConcurrentAutoModule(lock);
//
//            addSegment(.6,DefaultWP, 20, -20 , 50);
//
//
//            addTimedSetpoint(1,.5,1,35,-60,82);
//
//            addAutoModule(StackIntake1);
//            addConcurrentAutoModule(RemovePixels);
//
//
//            addSegment(.8,DefaultWP, 10, -18 , 90);
//            addSegment(.8,DefaultWP, -140, -18 , 90);
//            addConcurrentAutoModule(PreExtend1);
//
//            addTimedSetpoint(1,.8,1,-203,-44,100);
//            addAutoModule(align);
//            addConcurrentAutoModule(Reset);
            addSegment(.7,DefaultWP, -180, -18 , 90);
            addConcurrentAutoModule(ReadyIntake);
            addSegment(.8,DefaultWP, -100, -15 , 90);


            addSegment(.8,DefaultWP, -10, -18 , 90);
            addConcurrentAutoModule(lock);

            addSegment(.6,DefaultWP, 5, -18 , 50);


            addTimedSetpoint(1,.5,1,35,-60,82);

            addAutoModule(StackIntake3);
            addConcurrentAutoModule(RemovePixels);
            addSegment(.8,DefaultWP, 10, -21 , 90);
            addSegment(.8,DefaultWP, 0, -21 , 90);


            addSegment(.8,DefaultWP, -140, -24 , 90);
            addConcurrentAutoModule(PreExtend1);


            addTimedSetpoint(1,.8,1,-200,-49 ,100);
            addAutoModule(align);

            addAutoModule(Reset);
            addBreakpointReturn();













//
//            //GOES FOR NEXT CYCLE
//
//
//            addSegment(1,DefaultWP,-175,-90,40);
//            addSegment(1,DefaultWP,-120,-130,90);
//
//
//            addConcurrentAutoModule(ReadyIntake);
//
//            addSegment(1,DefaultWP,-80,-130,90);
//
//
//
//
//            addSegment(.7,DefaultWP,-45,-130,90);
//
//
//            addBreakpoint(()-> time.seconds() > 23);
//
//            addSegment(.2,DefaultWP,47.3,-115,92);
//
//
////
//            addAutoModule(StackIntake2);
//            addConcurrentAutoModule(RemovePixels);
//
//
//
//
//
//
//            addSegment(1,DefaultWP,0,-130,90);
//            addBreakpointReturn();
//
//            addConcurrentAutoModule(intakereset);
//            addBreakpoint(()-> time.seconds() > 28);
//
//            addSegment(1,DefaultWP,-136,-130,80);
//
//
//
//            addConcurrentAutoModule(whitePixel);
//
//            addSegment(1,DefaultWP,-170,-100,50);
//
//            addTimedSetpoint(1,.2,.5,-210,-65,90);
//
//
//            addAutoModule(align);
//            addPause(.2);
//
//
//            addAutoModule(Reset);
//            addBreakpointReturn();
//            addTimedSetpoint(1,.2,.5,-230,-65,90);
//
//















        }, () -> {

            addTimedSetpoint(1.0,.5,1,10,-47,0);

            addAutoModule(Extake);



            addConcurrentAutoModule(ReadyIntake);
            addTimedSetpoint(1,.5,.5,20,-60,82);

            addTimedSetpoint(1,.5,1,46,-60,82);

            addAutoModule(lock);

            addSegment(.4,DefaultWP,35,-30,30);
            addTimedSetpoint(1,.5,1,40,-40,40);

            addAutoModule(StackIntake);

            addConcurrentAutoModule(RemovePixels);

            addSegment(.8,DefaultWP, 0, -15 , 90);

            addSegment(.8,DefaultWP, -130, -15 , 90);

            addConcurrentAutoModule(PreExtend);


            addSegment(.8,DefaultWP, -170, -40 , 90);
            addTimedSetpoint(1,.8,1,-200,-72,90);
            addAutoModule(align);
            addAutoModule(open2);
            addAutoModule(open1);



            addConcurrentAutoModule(Reset);
            addBreakpoint(()-> time.seconds() > 27);
            addSegment(.7,DefaultWP, -180, -18 , 90);
            addConcurrentAutoModule(ReadyIntake);
            addSegment(.8,DefaultWP, -100, -15 , 90);


            addSegment(.8,DefaultWP, -10, -18 , 90);
            addConcurrentAutoModule(lock);

            addSegment(.6,DefaultWP, 5, -18 , 50);


            addTimedSetpoint(1,.5,1,35,-60,82);

            addAutoModule(StackIntake3);
            addConcurrentAutoModule(RemovePixels);
            addSegment(.8,DefaultWP, 10, -21 , 90);
            addSegment(.8,DefaultWP, 0, -21 , 90);


            addSegment(.8,DefaultWP, -140, -24 , 90);
            addConcurrentAutoModule(PreExtend1);


            addTimedSetpoint(1,.8,1,-200,-48 ,100);
            addAutoModule(align);

            addAutoModule(Reset);
            addBreakpointReturn();






        }, () -> {
            addTimedSetpoint(1.0,.5,1,-10,-40,-40);


            addAutoModule(Extake);



            addConcurrentAutoModule(ReadyIntake);
            addTimedSetpoint(1,.5,.5,10,-60,82);

            addTimedSetpoint(1,.5,1,45,-60,82);

            addAutoModule(lock);

            addSegment(.4,DefaultWP,35,-30,30);
            addTimedSetpoint(1,.5,1,40,-40,40);

            addAutoModule(StackIntake);

            addConcurrentAutoModule(RemovePixels);

            addSegment(.8,DefaultWP, 0, -15 , 90);

            addSegment(.8,DefaultWP, -130, -15 , 90);

            addConcurrentAutoModule(PreExtend);


            addSegment(.8,DefaultWP, -170, -40 , 90);
            addTimedSetpoint(1,.8,1,-200,-60,90);
            addAutoModule(align);
            addAutoModule(open2);
            addAutoModule(open1);



            addConcurrentAutoModule(Reset);
            addBreakpoint(()-> time.seconds() > 27);
            addSegment(.7,DefaultWP, -180, -18 , 90);
            addConcurrentAutoModule(ReadyIntake);
            addSegment(.8,DefaultWP, -100, -15 , 90);


            addSegment(.8,DefaultWP, -10, -18 , 90);
            addConcurrentAutoModule(lock);

            addSegment(.6,DefaultWP, 5, -18 , 50);


            addTimedSetpoint(1,.5,1,38,-60,82);

            addAutoModule(StackIntake3);
            addConcurrentAutoModule(RemovePixels);
            addSegment(.8,DefaultWP, 10, -18 , 90);
            addSegment(.8,DefaultWP, 0, -18 , 90);


            addSegment(.8,DefaultWP, -140, -24 , 90);
            addConcurrentAutoModule(PreExtend1);


            addTimedSetpoint(1,.8,1,-200,-55 ,100);
            addAutoModule(align);

            addAutoModule(Reset);
            addBreakpointReturn();

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
