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
        propCaseDetected = TeamProp.SECOND;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "left");

    }
    AutoModule ExtakeandLift = new AutoModule(
            intake.stageMiddle(.1).attach(intake.moveTime(.5,.2)),
            intake.stageInit(.1).attach(lift.stageLift(1, 10)),
            lift.stageLift(1, 15).attach(outtake.stageThruPivot(.1)),
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
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            lift.stageLift(1, heightMode.getValue(GROUND)).attach(outtake.stageThruPivot(.2)),
            outtake.stageStartPivot(.1)

    );
    @Override
    public void define() {
        customCase(() -> {
            addWaypoint(0,-40,0);
            addWaypoint(34,-76,-90);

            addTimedSetpoint(1.0,1,.8,34,-76,-90);
            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.85,74,-48,-90);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -48,-90);

                        });


                    });
            addAutoModule(Reset);
            addWaypoint(60,-7,0);
            addTimedSetpoint(1.0,1,1.5,105,-7,0);


        }, () -> {
            addWaypoint(0,-40,0);
            addWaypoint(35,-105,-90);
            addTimedSetpoint(1.0,1,.5,18,-104,-90);

            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.5,50,-64.5,-90);

            addTimedSetpoint(1.0,1,.5,65,-55.5,-90);

            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,.1, (odometry.getX() + 5), -64.5,-90);

                        });


                    });
            addAutoModule(Reset);
            addWaypoint(60,-7,0);
            addTimedSetpoint(1.0,1,1,105,-7,0);




        }, () -> {

            addWaypoint(0,-40,0);
            addWaypoint(-10,-70,-90);

            addTimedSetpoint(1.0,1,1,-15,-70,-90);
            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,1,.9,70,-81,-90);

            addTimedSetpoint(1.0,1,.3,71,-81,-90);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -81,-90);

                        });


                    });
            addAutoModule(Reset);
            addWaypoint(60,-7,0);
            addTimedSetpoint(1.0,1,1.5,105,-7,0);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
