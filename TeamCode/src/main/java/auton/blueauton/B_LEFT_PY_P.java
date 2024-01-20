package auton.blueauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "BLUE Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_LEFT_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveStartPivot();
        outtake.closeClaw();

        intake.moveInit();
//        propCaseDetected = TeamProp.FIRST;
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

    }
    AutoModule ExtakeandLift = new AutoModule(
            intake.moveTime(.5,.2).attach(intake.stageMiddler(.2)),
            intake.stageInit(.2),
            lift.stageLift(1, 12).attach(outtake.stageThruPivot(.2)),

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


            addTimedSetpoint(1.0,.5,1,20,-30,0);

            addTimedSetpoint(1.0,.5,1,20,-43,0);

            addAutoModule(ExtakeandLift);
            addWaypoint(30,-20,0);

            addTimedSetpoint(1.0,.5,2,60,-60.5,-90);
            addPause(.2);

            addTimedSetpoint(1.0,.5,1,82,-50,-90);
            addPause(.5);

//            while (distanceSensorsNew.getCMDistanceRight() > 21.5 && distanceSensorsNew.getCMDistanceLeft() > 21.5)  {
//
//                            addWaypoint(odometry.getX()+5, -60.5, -90);
//
//
//
//
//                    }
            addAutoModule(Reset);
            addWaypoint(60,-10,0);
            addTimedSetpoint(1.0,.3,2,90,-10,-90);

        }, () -> {


            addTimedSetpoint(1.0,.5,1,20,-70,-35);
            addTimedSetpoint(1.0,.5,1,10,-80,-30);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,60,-67,-90);
            addPause(.2);

            addTimedSetpoint(1.0,.5,1,80,-67,-90);
            addPause(.5);


//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            whileActive(drive.move(.5));
//
//                        });
//
//
//                    });
            addAutoModule(Reset);
            addWaypoint(60,-14,0);
            addTimedSetpoint(1.0,.3,2,90,-12,-90);




        }, () -> {

            addWaypoint(0,-30,0);
            addWaypoint(-30,-30,-10);

//            addTimedSetpoint(1.0,.5,1.5,-18,-55,-80);
//            addTimedSetpoint(1.0,.5,1,-30,-50,-80);
            addTimedSetpoint(1.0,.5,1,-30,-50,-30);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,40,-74,-94);
            addPause(.2);
            addTimedSetpoint(1.0,.5,.7,77,-78,-93);
            addPause(.5);

//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -81,-90);
//
//                        });
//
//
//                    });
            addAutoModule(Reset);
            addWaypoint(60,-15,0);
            addTimedSetpoint(1.0,.3,2,90,-13,-90);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
