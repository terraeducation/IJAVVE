package auton.blueauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "B. Right PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_RIGHT_PY_P extends AutoFramework {


    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();

        propCaseDetected = TeamProp.FIRST;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "right");

    }
    AutoModule Extake = new AutoModule(
            intake.moveTime(.3,.7)


    );

    AutoModule Drop = new AutoModule(
            outtake.stageEnd(.5).attach(outtake.stageTransferPivot(.5)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 7),
            lift.stageLift(1, 18).attach(outtake.stageThruPivot(.1))

    );
    AutoModule Reset = new AutoModule(
            RobotPart.pause(.2),
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            lift.stageLift(1, heightMode.getValue(GROUND)).attach(outtake.stageThruPivot(.2)),
            outtake.stageStartPivot(.1)

    );
    @Override
    public void define() {
        customCase(() -> {
            addPause(15);
            addWaypoint(0,-55,0);
            addTimedSetpoint(1.0,1,1,0,-65,-90);
            addWaypoint(60,-65,-90);
            addTimedSetpoint(1.0,1,.7,60,-87,-90);
            addAutoModule(Extake);
            addWaypoint(60,-65,-90);
            addWaypoint(115,-65,-90);
            addTimedSetpoint(1,1,1.2,115,-135,-90);
            addTimedSetpoint(1,1,2,200,-135,-90);
            addConcurrentAutoModule(PreExtend);
            addWaypoint(200, -68,-91);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.3,217, -68,-91);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -68,-91);

                        });


                    });

            addAutoModule(Reset);












        }, () -> {
            addPause(18);
            addTimedSetpoint(1.0,1,1.5,0,-55,0);
            addTimedSetpoint(1.0,1,.5,0,-75,0);

            addAutoModule(Extake);
            addWaypoint(0, -65,0);

            addWaypoint(0, -65,-90);
            addWaypoint(150, -70,-93);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,1.2,190, -84,-93);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.5,219, -84,-93);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -84,-93);

                        });


                    });
            addAutoModule(Reset);




        }, () -> {
            addPause(18);
            addWaypoint(0,-35,0);
            addWaypoint(-5,-55,-90);
            addTimedSetpoint(1.0,1,1.2,-7,-80,-90);
            addAutoModule(Extake);
            addWaypoint(0,-70,-90);
            addTimedSetpoint(1.0,1,1.2,0,-130,-90);
            addWaypoint(155, -130,-90);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1.0,1,1.2,155,-95,-90);
            addTimedSetpoint(1,1,.5,190, -95,-92);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.5,215, -97,-92);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -97,-92);

                        });


                    });

            addAutoModule(Reset);




        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
