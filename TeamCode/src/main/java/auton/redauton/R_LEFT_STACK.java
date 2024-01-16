package auton.redauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "RED Left Stack", group = "auto", preselectTeleOp = "TerraOp")
public class R_LEFT_STACK extends AutoFramework {



    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();
        propCaseDetected = TeamProp.THIRD;

//        AutoFramework auto = this;
//        auto.scan(true, "red", "left");

    }
    AutoModule Extake = new AutoModule(
            intake.moveTime(.3,.8)


    );

    AutoModule IntakeFirst = new AutoModule(
            intake.stageMiddler(.2).attach(intake.moveSmart(1))


    );

    AutoModule IntakeSecond = new AutoModule(
            intake.stageMiddle(.2).attach(intake.moveSmart(1))


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
            addPause(14);
            addWaypoint(0,-55,0);
            addTimedSetpoint(1.0,1,1,0,-65,90);
            addWaypoint(-64,-65,90);
            addTimedSetpoint(1.0,1,.7,-64,-80,90);
            addAutoModule(Extake);
            addWaypoint(-64,-65,90);
            addSetpoint(-125,-65,90);
            addTimedSetpoint(1,1,1.2,-125,-145,90);
            addTimedSetpoint(1,1,2,-200,-145,90);
            addConcurrentAutoModule(PreExtend);



            addWaypoint(-200, -97,91);
            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.3,-225, -90,91);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -90,91);

                        });


                    });

            addAutoModule(Reset);












        }, () -> {
            addPause(18);
            addTimedSetpoint(1.0,1,1.5,0,-65,0);
            addTimedSetpoint(1.0,1,.5,0,-80,0);

            addAutoModule(Extake);
            addWaypoint(0, -65,0);

            addWaypoint(0, -65,90);


            addWaypoint(-150, -72,93);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,1.2,-190, -72,93);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.5,-225, -72,93);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -72,93);

                        });


                    });
            addAutoModule(Reset);




        }, () -> {
//            addPause(18);
            addWaypoint(0,-35,0);
            addWaypoint(0,-55,90);
            addTimedSetpoint(1.0,1,1.2,0,-80,90);
            addAutoModule(Extake);
            addWaypoint(0,-70,-90);
            addTimedSetpoint(1.0,1,1.2,0,-130,90);
            addWaypoint(30,-70,-90);
            addAutoModule(IntakeFirst);

            addWaypoint(-155, -130,90);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1.0,1,1.2,-155,-64,90);
            addTimedSetpoint(1,1,.5,-190, -64,92);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.5,-225, -64,92);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -64,92);

                        });


                    });

            addAutoModule(Reset);
            addTimedSetpoint(1,1,1,-190, -64,92);
            addWaypoint(-155,-64,90);
            addTimedSetpoint(1.0,1,2.5,30,-130,90);
            addAutoModule(IntakeSecond);
            addTimedSetpoint(1.0,1,2,-155,-130,90);

            addTimedSetpoint(1.0,1,.5,-155,-95,90);
            addTimedSetpoint(1,1,.5,-190, -95,92);

            addAutoModule(Drop);
            addTimedSetpoint(1,1,1.5,-225, -95,92);
            addCustomCode(
                    () -> {

                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {

                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -95,92);

                        });


                    });

            addAutoModule(Reset);

//



        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}



