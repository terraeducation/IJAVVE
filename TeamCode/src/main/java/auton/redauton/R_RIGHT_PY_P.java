package auton.redauton;

import static global.General.bot;
import static global.General.log;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;
import elements.TeamProp;
import geometry.position.Pose;
import robotparts.RobotPart;

@Autonomous(name = "RED Right PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class R_RIGHT_PY_P extends AutoFramework {
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
        auto.scan(true, "red", "right");

    }
    AutoModule ExtakeandLift = new AutoModule(
            intake.stageMiddler(.2),
            intake.moveTime(.53,.2),
            intake.stageInit(.2),
            lift.stageLift(1, 13).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))

    );

    AutoModule Drop = new AutoModule(
            lift.stageLift(1, 7),
            lift.stageLift(1, 15).attach(outtake.stageThruPivot(.1)),
            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule Reset = new AutoModule(

            outtake.stageOpen(.1).attach(intake.stageInit(.2)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            lift.stageLift(1, 5).attach(outtake.stageThruPivot(.1)),
            outtake.stageStartPivot(.1).attach(lift.stageLift(1,0))

    );
    @Override
    public void define() {
        customCase(() -> {
            addWaypoint(10,-30,0);
            addWaypoint(30,-40,20);

//            addTimedSetpoint(1.0,.5,1.5,-18,-55,-80);
//            addTimedSetpoint(1.0,.5,1,-30,-50,-80);
            addTimedSetpoint(1.0,.5,1,30,-60,30);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,-40,-74,91);
            addPause(.2);
            addTimedSetpoint(1.0,.5,.7,-75,-78,91);
            addPause(.5);
            addTimedSetpoint(1.0,.5,.7,-88.5,-85,91);
            addPause(.5);



//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -87.5,90);
//
//
//
//
//                        });
//
//
//
//
//
//
//
//                    });
            addAutoModule(Reset);
            addWaypoint(-60,-15,0);
            addTimedSetpoint(1.0,.5,1.5,-90,-10,90);



        }, () -> {

            addTimedSetpoint(1.0,.5,1,-20,-70,40);
            addTimedSetpoint(1.0,.5,1,-10,-74,55);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,-60,-74,90);
            addPause(.5);

            addTimedSetpoint(1.0,.5,1,-88.5,-74,90    );
            addPause(.5);


//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -64.5,90);
//
//                        });
//
//
//                    });
            addAutoModule(Reset);
            addWaypoint(-60,-9,0);
            addTimedSetpoint(1.0,.5,1.3,-90,-10,90);


        }, () -> {
            addTimedSetpoint(1.0,.5,1,-14,-30,0);

            addTimedSetpoint(1.0,.5,1,-14,-43,0);


            addAutoModule(ExtakeandLift);

            addWaypoint(-30,-20,0);

            addTimedSetpoint(1.0,.5,2,-60,-60.5,90);
            addPause(.5);

            addTimedSetpoint(1.0,.5,.8,-83,-51,95);
            addPause(.5);
            addTimedSetpoint(1.0,.5,.8,-86,-51,95);


//
//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -48,90);
//
//                        });
//
//
//                    });
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(-60,-20,0);
            addTimedSetpoint(1.0,.5,1,-90,-10,90);
        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
