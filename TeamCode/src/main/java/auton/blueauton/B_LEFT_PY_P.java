//package auton.blueauton;
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
//@Autonomous(name = "B. Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
//public class B_LEFT_PY_P extends AutoFramework {
//    @Override
//    public void initialize() {
//        this.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
//        OUTTAKE_OLD.moveStart();
//        OUTTAKE_OLD.openClawHalf();
//        propCaseDetected = TeamProp.FIRST;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "left");
//
//    }
//    AutoModule Extake = new AutoModule(
//            intake.moveTime(.1,1.2)
//
//    );
//    AutoModule Drop = new AutoModule(
////            OUTTAKE_OLD.stageMiddle(.5).attach(OUTTAKE_OLD.stageClose(.2)),
//            lift.stageLift(.8,25).attach(OUTTAKE_OLD.stageMiddler(.2)),
//            OUTTAKE_OLD.stageEndAuto(.4),
//            RobotPart.pause(.5),
//            OUTTAKE_OLD.stageOpen(.2),
//            RobotPart.pause(.5)
//    );
//    AutoModule Drop3rd = new AutoModule(
//            OUTTAKE_OLD.stageMiddle(.2).attach(OUTTAKE_OLD.stageClose(.2)),
//            lift.stageLift(.8,20).attach(OUTTAKE_OLD.stageMiddler(.5)),
//            OUTTAKE_OLD.stageEndAuto(.2),
//            RobotPart.pause(.5),
//            OUTTAKE_OLD.stageOpen(.2),
//            RobotPart.pause(.5),
//            lift.stageLift(.8,28)
//    );
//    AutoModule Reset = new AutoModule(
//            OUTTAKE_OLD.stageStart(1),
//            lift.stageLift(.6,0)
//
//    );
//    @Override
//    public void define() {
//        customCase(() -> {
//            addTimedSetpoint(1.0,1,.1,0,10,0);
//            addTimedSetpoint(1.0,1,1,-39,73,90);
//            addAutoModule(Extake);
//            addWaypoint(.8,-85,45,90);
//
//            addTimedSetpoint(1.0,.8,1,-96.5,41,90);
//            addAutoModule(Drop3rd);
//            addAutoModule(Reset);
//            addTimedSetpoint(1.0,1,.2,-85,45,90);
//            addTimedSetpoint(1.0,1,1,-85,5,0);
//            addTimedSetpoint(1.0,1,1,-110,5,0);
//
//        }, () -> {
//            addTimedSetpoint(1.0,1,.1,0,10,0);
//            addTimedSetpoint(1.0,1,2,-30,95,90);
//            addAutoModule(Extake);
//            addWaypoint(1,-85,59,90);
//            addTimedSetpoint(1.0,1,1.2,-96.5,59,94);
//            addAutoModule(Drop);
//
//            addAutoModule(Reset);
//            addTimedSetpoint(1.0,1,.2,-85,45,90);
//            addTimedSetpoint(1.0,1,1,-85,5,0);
//            addTimedSetpoint(1.0,1,1,-115,5,0);
//
//        }, () -> {
//
//            addTimedSetpoint(1.0,1,1,0,30,0);
//
//            addTimedSetpoint(1,.8,1,0,60,90);
//            addTimedSetpoint(1,.8,1,23,75,90);
//            addAutoModule(Extake);
//            addWaypoint(.8,-70,85,90);
//            addTimedSetpoint(1,1,1.5,-80,85,90);
//            RobotPart.pause(.2);
//            addTimedSetpoint(1,1,1,-98,85.25,95);
//            addAutoModule(Drop3rd);
//            addAutoModule(Reset);
//            addTimedSetpoint(1.0,1,.2,-60,45,90);
//            addTimedSetpoint(1.0,1,1,-85,5,0);
//            addTimedSetpoint(1.0,1,1,-115,5,0);
//        });
//    }
//    @Override
//    public void postProcess() {
//        autoPlane.reflectY();
//        autoPlane.reflectX();
//    }
//
//
//}
