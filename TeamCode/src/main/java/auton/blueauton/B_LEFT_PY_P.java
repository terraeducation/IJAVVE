package auton.blueauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "B. Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_LEFT_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveStartPivot();
        outtake.closeClaw();

        intake.moveInit();
        propCaseDetected = TeamProp.FIRST;
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

    }
    AutoModule ExtakeandLift = new AutoModule(
            intake.stageMiddle(.1).attach(intake.moveTime(.5,.2)),
            lift.stageLift(1, 12).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))

    );

    AutoModule Drop = new AutoModule(
            lift.stageLift(1, 7),
            lift.stageLift(1, 18).attach(outtake.stageThruPivot(.1)),
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
            addWaypoint(0,-40,0);
            addWaypoint(40,-76,0);

            addTimedSetpoint(1.0,1,.5,34,-76,-90);

            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.4,60,-60.5,-90);

            addTimedSetpoint(1.0,1,.55,73,-52,-90);
//            while (distanceSensorsNew.getCMDistanceRight() > 21.5 && distanceSensorsNew.getCMDistanceLeft() > 21.5)  {
//
//                            drive.move(.5);
//
//
//
//
//                    }
            addAutoModule(Reset);
            addWaypoint(60,-7,0);
            addTimedSetpoint(1.0,1,.8,107,-9,0);

        }, () -> {
            addWaypoint(0,-40,0);
            addWaypoint(33,-90,0);
            addTimedSetpoint(1.0,1,.5,15,-104,-90);

            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.3,60,-77,-90);

            addTimedSetpoint(1.0,1,.75,72,-77,-90);

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
            addWaypoint(60,-5,0);
            addTimedSetpoint(1.0,1,.9,107,-5,0);




        }, () -> {

            addWaypoint(0,-40,0);
            addWaypoint(-10,-70,-90);

            addTimedSetpoint(1.0,1,.5,-15,-70,-90);
            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.7,60,-81,-90);

            addTimedSetpoint(1.0,1,.4,71,-81,-90);
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
            addWaypoint(60,-5,0);
            addTimedSetpoint(1.0,1,1,105,-5,0);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
