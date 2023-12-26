//package auton.redauton;
//
//import static global.General.bot;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import automodules.AutoModule;
//import autoutil.AutoFramework;
//import elements.TeamProp;
//import robotparts.RobotPart;
//
//    @Autonomous(name = "R. Right Stack ", group = "auto", preselectTeleOp = "TerraOp")
//    public class R_RIGHT_STACK extends AutoFramework {
//        @Override
//        public void initialize() {
//            this.setConfig(NonstopConfig);
//            bot.saveLocationOnField();
//            OUTTAKE_OLD.moveStart();
//            OUTTAKE_OLD.openClawHalf();
//            propCaseDetected = TeamProp.FIRST;
//            AutoFramework auto = this;
//            auto.scan(true, "red", "right");
//
//        }
//        AutoModule Extake = new AutoModule(
//                intake.moveTime(.1,1)
//
//        );
//        AutoModule Intake = new AutoModule(
//                intake.stageMiddle(.1),
//                intake.moveTime(-.9,2)
//
//        );
//        AutoModule Intake2 = new AutoModule(
//                intake.stageMiddler(.1),
//                intake.moveTime(-.9,1)
//
//        );
//        AutoModule Drop = new AutoModule(
//                OUTTAKE_OLD.stageMiddle(.5).attach(OUTTAKE_OLD.stageClose(.2)),
//                lift.stageLift(.8,25).attach(OUTTAKE_OLD.stageMiddler(.2)),
//                OUTTAKE_OLD.stageEndAuto(.4),
//                RobotPart.pause(.5),
//                OUTTAKE_OLD.stageOpen(.2),
//                RobotPart.pause(.5)
//        );
//        AutoModule Drop3rd = new AutoModule(
//                OUTTAKE_OLD.stageMiddle(.2).attach(OUTTAKE_OLD.stageClose(.2)),
//                lift.stageLift(.8,23).attach(OUTTAKE_OLD.stageMiddler(.5)),
//                OUTTAKE_OLD.stageEndAuto(.2),
//                RobotPart.pause(.5),
//                OUTTAKE_OLD.stageOpen(.2),
//                RobotPart.pause(.5),
//                lift.stageLift(.8,28)
//        );
//        AutoModule Reset = new AutoModule(
//                OUTTAKE_OLD.stageStart(1),
//                lift.stageLift(.6,0)
//
//        );
//
//        AutoModule ResetFast = new AutoModule(
//                OUTTAKE_OLD.stageStart(.1),
//                lift.stageLift(1,0)
//
//        );
//
//        AutoModule DropFast = new AutoModule(
//                OUTTAKE_OLD.stageMiddle(.2).attach(OUTTAKE_OLD.stageClose(.2)),
//                lift.stageLift(1,25).attach(OUTTAKE_OLD.stageMiddler(.2)),
//                OUTTAKE_OLD.stageEndAuto(.4),
//                OUTTAKE_OLD.stageOpen(.2),
//                RobotPart.pause(.4)
//        );
//        AutoModule ReadyDrop = new AutoModule(
//                OUTTAKE_OLD.stageOpenHalf(.2),
//                OUTTAKE_OLD.stageMiddle(.2)
//
//        );
//        @Override
//        public void define() {
//            customCase(() -> {
//                addTimedSetpoint(1.0,1,.1,0,10,0);
//                addTimedSetpoint(1.0,1,1,0,60,-90);
//                addTimedSetpoint(1.0,1,1,-28,60,-90);
//                addAutoModule(Extake);
//                addWaypoint(.8,85,75,-90);
//
//                addTimedSetpoint(1.0,.8,1,95.5,75,-90);
//                addAutoModule(Drop3rd);
//                addAutoModule(Reset);
//                addWaypoint(1,85,60,-90);
//                addTimedSetpoint(1.0,1,1,45,125,-90);
//                addTimedSetpoint(1.0,1,3.7,-165,130,-94);
//                addTimedSetpoint(1.0,1,.5,-175,130,-94);
//                addTimedSetpoint(1.0,1,.5,-173,134,-94);
//                addAutoModule(Intake);
//                addTimedSetpoint(1.0,1,.5,-175,134,-93);
//                addAutoModule(Intake);
//                addAutoModule(ReadyDrop);
//                addTimedSetpoint(1.0,1,3,85,125,-90);
//                addTimedSetpoint(1.0,1,1,93,80,-93);
//                addTimedSetpoint(1.0,1,.4,97.5,80,-93);
//                addAutoModule(DropFast);
//                addAutoModule(ResetFast);
//
//            }, () -> {
//                addTimedSetpoint(1.0,1,.1,0,10,0);
//                addTimedSetpoint(1.0,1,2,30,95,-90);
//                addAutoModule(Extake);
//                addWaypoint(1,85,59,-90);
//                addTimedSetpoint(1.0,1,1.2,96.5,57,-94);
//                addAutoModule(Drop);
//                addAutoModule(Reset);
//                addWaypoint(1,85,60,-90);
//                addTimedSetpoint(1.0,1,1,45,125,-90);
//                addTimedSetpoint(1.0,1,3.7,-165,130,-94);
//                addTimedSetpoint(1.0,1,.5,-175,130,-94);
//                addTimedSetpoint(1.0,1,.5,-173,134,-94);
//                addAutoModule(Intake);
//                addTimedSetpoint(1.0,1,.5,-175,134,-93);
//                addAutoModule(Intake);
//                addAutoModule(ReadyDrop);
//                addTimedSetpoint(1.0,1,3,85,125,-90);
//                addTimedSetpoint(1.0,1,1,93,80,-93);
//                addTimedSetpoint(1.0,1,.4,97.5,80,-93);
//                addAutoModule(DropFast);
//                addAutoModule(ResetFast);
//
//            }, () -> {
//
//                addTimedSetpoint(1.0,1,1,0,30,0);
//
//                addTimedSetpoint(1,.8,1,0,60,-90);
//                addTimedSetpoint(1,.8,1,38,75,-90);
//                addAutoModule(Extake);
//                addWaypoint(.8,70,85,-90);
//                addTimedSetpoint(1,1,1.5,80,40,-90);
//                RobotPart.pause(.2);
//                addTimedSetpoint(1,1,1,98,40,-95);
//                addAutoModule(Drop3rd);
//                addAutoModule(Reset);
//                addWaypoint(1,85,60,-90);
//                addTimedSetpoint(1.0,1,1,45,125,-90);
//                addTimedSetpoint(1.0,1,3.7,-165,130,-94);
//                addTimedSetpoint(1.0,1,.5,-175,130,-94);
//                addTimedSetpoint(1.0,1,.5,-173,134,-94);
//                addAutoModule(Intake);
//                addTimedSetpoint(1.0,1,.5,-175,134,-93);
//                addAutoModule(Intake);
//                addAutoModule(ReadyDrop);
//                addTimedSetpoint(1.0,1,3,85,125,-90);
//                addTimedSetpoint(1.0,1,1,93,80,-93);
//                addTimedSetpoint(1.0,1,.4,97.5,80,-93);
//                addAutoModule(DropFast);
//                addAutoModule(ResetFast);
//            });
//        }
//        @Override
//        public void postProcess() {
//            autoPlane.reflectY();
//            autoPlane.reflectX();
//        }
//
//
//    }
//
//
