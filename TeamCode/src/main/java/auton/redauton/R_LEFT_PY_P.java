package auton.redauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "RED Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class R_LEFT_PY_P extends AutoFramework {


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
            intake.stageMiddler(.1),
            intake.moveTime(.5,.3),
            intake.stageInit(.1)

    );

    AutoModule Drop = new AutoModule(
            outtake.stageEnd(.5).attach(outtake.stageTransferPivot(.5)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 16).attach(outtake.stageThruPivot(.2)),

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
    //TODO MAKE DISTANCE SENSOR ADJUST Y COORDINATE
    @Override
    public void define() {
        customCase(() -> {
            addPause(20);
            addWaypoint(0,-35,0);
            addWaypoint(5,-55,90);
            addTimedSetpoint(1.0,1,.9,7,-80,90);
            addAutoModule(Extake);
            addWaypoint(0,-70,90);
            addTimedSetpoint(1.0,1,.8,0,-130,90);
            addWaypoint(-155, -130,90.5);
            addAutoModule(PreExtend);


            addTimedSetpoint(1.0,1,.8,-220,-94,93);

//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, -(odometry.getX() + 5), -90,91);
//
//                        });
//
//
//                    });

            addAutoModule(Reset);












        }, () -> {
            addPause(22);
            addTimedSetpoint(1.0,.5,1,20,-70,-35);
            addTimedSetpoint(1.0,.5,1,10,-76,-30);


            addAutoModule(Extake);
            addWaypoint(0, -65,0);

            addWaypoint(0, -65,90);
            addWaypoint(-155, -70,90);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,.8,-180, -70,91);


            addTimedSetpoint(1,1,.6,-220, -73,91);
//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -84,-93);
//
//                        });
//
//
//                    });
            addPause(.2);
            addAutoModule(Reset);





        }, () -> {
            addPause(18);

            addWaypoint(0,-35,0);
            addWaypoint(5,-55,-90);
            addTimedSetpoint(1.0,1,.9,-25,-70,-90);
            addAutoModule(Extake);
            addWaypoint(0,-70,90);
            addTimedSetpoint(1.0,1,1,0,-125,90);
            addWaypoint(-200, -120,90);
            addTimedSetpoint(1.0,1,1.7,-200,-60,90);
            addAutoModule(PreExtend);

            addTimedSetpoint(1.0,1,.5,-228,-64,92);

//            addCustomCode(
//                    () -> {
//
//                        whileNotExit(() -> distanceSensorsNew.getCMDistanceRight() < 21.5 && distanceSensorsNew.getCMDistanceLeft() < 21.5, () -> {
//
//                            addTimedSetpoint(1,1,1, (odometry.getX() + 5), -84,-93);
//
//                        });
//
//
//                    });
            addPause(.2);
            addAutoModule(Reset);



        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
