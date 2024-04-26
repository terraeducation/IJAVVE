package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import automodules.AutoModule;
import automodules.stage.Exit;
import autoutil.AutoFramework;
import elements.GameElement;
import robotparts.RobotPart;
import util.Timer;

@Autonomous(name = "Mega Weapon", group = "auto", preselectTeleOp = "TerraOp")
public class themegaweapon extends AutoFramework {








    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();
        intake.purple();
//        propCaseDetected = TeamProp.THIRD;
//        AutoFramework auto = this;
//        auto.scan(true, "red", "right");

    }
    AutoModule drop = new AutoModule(



    );
    AutoModule ExtakeandLift = new AutoModule(
//            intake.stageLockInit(.1),

            lift.stageLift(1, 13).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageflipStackRotate(.2))

    );
    AutoModule ReadyIntake = new AutoModule(
//            intake.stageLockReady(.1),

            outtake.stageLock(.3).attach(outtake.stageDownPivot(.5)),
            intake.stageMiddler(.2).attach(outtake.stageOpen(.1))
    ).setStartCode(() ->{}
    );

    AutoModule ReadyIntake2 = new AutoModule(
            outtake.stageLock(.3).attach(outtake.stageDownPivot(.5)),
            intake.stageStart(.2).attach(outtake.stageOpen(.1))
    ).setStartCode(() ->{}
    );
    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 5).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))




    );

     AutoModule whitePixel = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2))




    );

    AutoModule whitePixel2 = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.2))




    );
    AutoModule align = new AutoModule(
            drive.driveSmart(-.25,0,0)
    );
    AutoModule align2 = new AutoModule(
            drive.driveSmart(-.45,0,0)
    );
    AutoModule lock = new AutoModule(
            intake.stageLockClose(.1)




    );
    AutoModule unlock = new AutoModule(
            intake.stageLockReady(.1)




    );
    AutoModule PreExtend1 = new AutoModule(
            lift.stageLift(1, 23).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4)),
            outtake.stageflipStackRotate(.1)



    );
    AutoModule RemovePixels = new AutoModule(

            intake.moveTime(.8,1)
    );
    AutoModule intakereset = new AutoModule(
            intake.moveTime(.5,.3),
            intake.stageInit(.1)
    );
     AutoModule Reset = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.1).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );


    AutoModule alignCloser = new AutoModule(
            drive.drivecloseSmart(-.18,0,0)
    );
    AutoModule intakee = new AutoModule(

            intake.moveOneSmart(-1)



    );
    AutoModule intakeepart2 = new AutoModule(
            intake.stageMiddle(.1),
            intake.moveSmart2(-1),
            outtake.stageClose(.5)



    );


    AutoModule intakee3 = new AutoModule(
            outtake.stageLock(.3).attach(outtake.stageDownPivot(.5)),
            intake.stageStart(.2).attach(outtake.stageOpen(.1)),
            intake.moveOneSmart(-1),
            outtake.stageClose(.5)


    );
    AutoModule intakee2 = new AutoModule(

            intake.moveSmart2(-1).attach(drive.intakeoneSmart(.35,0,0)),
            outtake.stageClose(.5)


    );
    AutoModule resetIntake = new AutoModule(

            intake.moveTime(1,.2),
            intake.stageInit(.2)
    );

    @Override
    public void define() {

            addSegment(1,DefaultWP,-20,-30,0);

        addConcurrentAutoModule(ExtakeandLift);







        addSegment(1,DefaultWP,-70,-62 ,100);


            addAutoModule(align);
            addConcurrentAutoModule(Reset);
        addSegment(1,DefaultWP,-60,-120,90);



            addConcurrentAutoModule(ReadyIntake);



        addSegment(1,DefaultWP,100.5,-120,90);



//        addSegment(1,DefaultWP,154,-108,79);
                addSegment(1,DefaultWP,156.5,-110.5,85);







        addAutoModule(intakee);

        addAutoModule(intakeepart2);


        addConcurrentAutoModule(intakereset);






        addSegment(1,DefaultWP,0,-120,90);
        addConcurrentAutoModule(PreExtend1);
        addSegment(1,DefaultWP,-70,-88,80);
        addAutoModule(align2);

        addConcurrentAutoModule(Reset);

        addSegment(1,DefaultWP,0,-127,90);


        addConcurrentAutoModule(ReadyIntake2);
        addSegment(1,DefaultWP,30,-127,90);


        addSegment(1,DefaultWP,135,-114,85);
        addAutoModule(intakee2);

        addConcurrentAutoModule(intakereset);






        addSegment(1,DefaultWP,0,-120,90);
        addConcurrentAutoModule(PreExtend1);
        addSegment(1,DefaultWP,-71,-88,80);
        addAutoModule(align2);
        addConcurrentAutoModule(Reset);

        addSegment(1,DefaultWP,0,-127,90);


        addConcurrentAutoModule(ReadyIntake);
        addSegment(1,DefaultWP,30,-127,90);

        addSegment(1,DefaultWP,154,-127,90);

        addSegment(1,DefaultWP,160,-70,110);
        addAutoModule(intakee);
        addConcurrentAutoModule(intakereset);






        addSegment(1,DefaultWP,0,-120,90);
        addConcurrentAutoModule(PreExtend1);
        addSegment(1,DefaultWP,-65,-88,80);
        addAutoModule(align2);
        addConcurrentAutoModule(Reset);

        addSegment(1,DefaultWP,0,-127,90);


        addConcurrentAutoModule(ReadyIntake2);
        addSegment(1,DefaultWP,30,-127,90);

        addSegment(1,DefaultWP,154,-127,90);

//        addSegment(1,DefaultWP,160,-70,110);
        addAutoModule(intakee2);
        addConcurrentAutoModule(intakereset);






        addSegment(1,DefaultWP,0,-120,90);
        addConcurrentAutoModule(PreExtend1);
        addSegment(1,DefaultWP,-67.5,-88,80);
        addAutoModule(align2);
        addConcurrentAutoModule(Reset);

        addSegment(1,DefaultWP,0,-127,90);


        addConcurrentAutoModule(ReadyIntake2);
        addSegment(1,DefaultWP,30,-127,90);

        addSegment(1,DefaultWP,154,-107,90);
        addAutoModule(intakee3);

        addSegment(1,DefaultWP,160,-70,110);
        addAutoModule(intakee2);
        addConcurrentAutoModule(intakereset);



        addSegment(1,DefaultWP,0,-120,90);
        addConcurrentAutoModule(PreExtend1);
        addSegment(1,DefaultWP,-65,-88,80);
        addAutoModule(align2);
        addAutoModule(Reset);








//            addSegment(.3,DefaultWP,170.2,-120,90);
//            addAutoModule(unlock);
//
//            addSegment(.3,DefaultWP,162,-120,90);
//
////
////
////
////
//
//
//
//
//            addConcurrentAutoModule(RemovePixels);
//            addSegment(1,DefaultWP,140,-125,90);
//            addSegment(1,DefaultWP,0,-125,90);
//
//
//            addConcurrentAutoModule(intakereset);
//
//            addSegment(1,DefaultWP,-30,-125,80);
//
//            addConcurrentAutoModule(whitePixel);
//
//            addSegment(1,DefaultWP,-60,-90,50);
//
//            addTimedSetpoint(1,.2,.5,-60,-84,88);
//
//
//            addAutoModule(align);
//            addPause(.1);
//
//            addConcurrentAutoModule(Reset);
//
//
//            addSegment(1,DefaultWP,-80,-90,40);
//            addBreakpoint(()-> time.seconds() > 25.5);
//
//            addSegment(1,DefaultWP,-60,-125,90);
//
//
//            addConcurrentAutoModule(ReadyIntake);
//
//            addSegment(1,DefaultWP,60,-130,90);
//
//
//
//
//            addSegment(.7,DefaultWP,120,-130,90);
//
//
//
//            addSegment(.2,DefaultWP,189.5,-125,90);
//
//            addAutoModule(StackIntake2);
//
//
//
//            addConcurrentAutoModule(RemovePixels);
//
//            addSegment(1,DefaultWP,140,-130,90);
//
//            addSegment(1,DefaultWP,0,-125,90);
//            addConcurrentAutoModule(intakereset);
//
//            addSegment(1,DefaultWP,-15,-125,80);
//            addBreakpoint(()-> time.seconds() < 25.5);
//            addSegment(1,DefaultWP,-50,-125,90);
//            addAutoModule(PreExtend);
//            addPause(.1);
//
//            addAutoModule(Reset);
//            addBreakpointReturn();
//
//            addBreakpoint(()-> time.seconds() > 25.5);
//
//
//
//
//            addConcurrentAutoModule(whitePixel2);
//
//            addSegment(1,DefaultWP,-60,-90,50);
//
//            addTimedSetpoint(1,.2,.5,-75,-84,88);
//
//
//            addAutoModule(align);
//            addPause(.1);
//
//            addAutoModule(Reset);
//            addBreakpointReturn();
//
//            addTimedSetpoint(1.0,.5,1,-90,-120,89);
//
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
