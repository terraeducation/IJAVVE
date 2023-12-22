package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

    @Autonomous(name = "B. Left Stack", group = "auto", preselectTeleOp = "TerraOp")
    public class B_LEFT_STACK extends AutoFramework {
        @Override
        public void initialize() {
            this.setConfig(NonstopConfig);
            bot.saveLocationOnField();
            outtake.moveStart();
            outtake.openClawHalf();
            propCaseDetected = TeamProp.FIRST;
            AutoFramework auto = this;
            auto.scan(true, "blue", "left");

        }
        AutoModule Intake = new AutoModule(
                outtake.stageClose(.2),
                intake.stageMiddle(.1),
                intake.moveTime(-.9,2)

        );
        AutoModule Intake2 = new AutoModule(
                intake.stageMiddler(.1),
                intake.moveTime(-.9,1)

        );
        AutoModule Extake = new AutoModule(
                intake.moveTime(.2,.6)

        );
        AutoModule Drop = new AutoModule(
                outtake.stageMiddle(.5).attach(outtake.stageClose(.2)),
                lift.stageLift(1,25).attach(outtake.stageMiddler(.2)),
                outtake.stageEndAuto(.4),
                RobotPart.pause(.5),
                outtake.stageOpen(.2),
                RobotPart.pause(.5)
        );
        AutoModule DropFast = new AutoModule(
                outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
                lift.stageLift(1,30).attach(outtake.stageMiddler(.2)),
                outtake.stageEndAuto(.4),
                outtake.stageOpen(.2),
                RobotPart.pause(.4)
        );


        AutoModule ReadyDrop = new AutoModule(
                outtake.stageOpenHalf(.2),
                outtake.stageMiddle(.2),
                outtake.stageClose(.2)

        );
        AutoModule Drop3rd = new AutoModule(
                outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
                lift.stageLift(.8,20).attach(outtake.stageMiddler(.5)),
                outtake.stageEndAuto(.2),
                RobotPart.pause(.5),
                outtake.stageOpen(.2),
                RobotPart.pause(.5),
                lift.stageLift(.8,28)
        );
        AutoModule Reset = new AutoModule(
                outtake.stageStart(.4),
                lift.stageLift(1,0)

        );
        AutoModule ResetFast = new AutoModule(
                outtake.stageStart(.1),
                lift.stageLift(1,0)

        );
        @Override
        public void define() {
            customCase(() -> {
                addTimedSetpoint(1.0,1,.1,0,10,0);
                addTimedSetpoint(1.0,1,1,-39,73,90);
                addAutoModule(Extake);
                addWaypoint(.8,-85,45,90);

                addTimedSetpoint(1.0,.8,1,-96.5,43,90);
                addAutoModule(Drop3rd);
                RobotPart.pause(1);
                addAutoModule(Reset);
                addWaypoint(1,-85,60,90);
                addTimedSetpoint(1.0,1,1,-85,125,90);
                addTimedSetpoint(1.0,1,3.7,165,130,94);
                addTimedSetpoint(1.0,1,.5,175,129,94);
                addAutoModule(Intake);
                addAutoModule(ReadyDrop);
                addTimedSetpoint(1.0,1,3,-85,125,90);
                addTimedSetpoint(1.0,1,1,-93,70,93);
                addTimedSetpoint(1.0,1,.4,-98,70,93);
                addAutoModule(DropFast);
                addAutoModule(ResetFast);

            }, () -> {
                addTimedSetpoint(1.0,1,.1,0,10,0);
                addTimedSetpoint(1.0,1,2,-30,95,90);
                addAutoModule(Extake);
                addWaypoint(1,-85,59,90);
                addTimedSetpoint(1.0,1,1.2,-95,60,93);
                addTimedSetpoint(1.0,1,.4,-97.5,60,93);
                addAutoModule(Drop);
                addAutoModule(Reset);
                addWaypoint(1,-85,60,90);
                addTimedSetpoint(1.0,1,1,-85,125,90);
                addTimedSetpoint(1.0,1,3.7,165,130,94);
                addTimedSetpoint(1.0,1,.5,175,130,94);
                addAutoModule(Intake);
                addAutoModule(ReadyDrop);
                addTimedSetpoint(1.0,1,3,-85,125,90);
                addTimedSetpoint(1.0,1,1,-93,70,93);
                addTimedSetpoint(1.0,1,.4,-98,70,93);
                addAutoModule(DropFast);
                addAutoModule(ResetFast);









//                addTimedSetpoint(1.0,1,1,-85,5,0);
//                addTimedSetpoint(1.0,1,1,-115,5,0);

            }, () -> {

                addTimedSetpoint(1.0,1,1,0,30,0);

                addTimedSetpoint(1,.8,1,0,60,90);
                addTimedSetpoint(1,.8,1,20,75,90);
                addAutoModule(Extake);
                addWaypoint(.8,-70,85,90);
                addTimedSetpoint(1,1,1.5,-80,85,90);
                RobotPart.pause(.2);
                addTimedSetpoint(1,1,1,-98,85.25,95);
                addAutoModule(Drop3rd);
                addAutoModule(Reset);
                addWaypoint(1,-85,60,90);
                addTimedSetpoint(1.0,1,1,-85,125,90);
                addTimedSetpoint(1.0,1,3.7,165,130,94);
                addTimedSetpoint(1.0,1,.5,175,130,94);
                addTimedSetpoint(1.0,1,.5,173,134,94);
                addAutoModule(Intake);
                addAutoModule(ReadyDrop);
                addTimedSetpoint(1.0,1,3,-85,125,90);
                addTimedSetpoint(1.0,1,1,-93,70,93);
                addTimedSetpoint(1.0,1,.4,-98,70,93);
                addAutoModule(DropFast);
                addAutoModule(ResetFast);
            });
        }
        @Override
        public void postProcess() {
            autoPlane.reflectY();
            autoPlane.reflectX();
        }


    }


