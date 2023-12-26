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
//@Autonomous(name = "B. Right PY&P ", group = "auto", preselectTeleOp = "TerraOp")
//public class B_RIGHT_PY_P extends AutoFramework {
//
//
//    @Override
//    public void initialize() {
//
//        this.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
//        OUTTAKE_OLD.moveStart();
//        OUTTAKE_OLD.openClawHalf();
//        propCaseDetected = TeamProp.FIRST;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "right");
//
//    }
//    AutoModule Extake = new AutoModule(
//            intake.moveTime(.2,.85)
//
//
//    );
//    AutoModule Drop = new AutoModule(
//            OUTTAKE_OLD.stageMiddle(.2).attach(OUTTAKE_OLD.stageClose(.2)),
//            lift.stageLift(.8,22).attach(OUTTAKE_OLD.stageMiddler(.5)),
//            OUTTAKE_OLD.stageEndAuto(.2),
//            RobotPart.pause(.5),
//            OUTTAKE_OLD.stageOpen(.2),
//            RobotPart.pause(.5),
//            lift.stageLift(.8,25)
//    );
//    AutoModule Drop3rd = new AutoModule(
//            OUTTAKE_OLD.stageMiddle(.2).attach(OUTTAKE_OLD.stageClose(.2)),
//            lift.stageLift(.8,22).attach(OUTTAKE_OLD.stageMiddler(.5)),
//            OUTTAKE_OLD.stageEndAuto(.2),
//            RobotPart.pause(.5),
//            OUTTAKE_OLD.stageOpen(.2),
//            RobotPart.pause(.5),
//            lift.stageLift(.8,25)
//    );
//    AutoModule Reset = new AutoModule(
//            OUTTAKE_OLD.stageStart(1),
//            lift.stageLift(.6,0)
//
//    );
//    @Override
//    public void define() {
//        customCase(() -> {
//
//            addTimedSetpoint(1.0,1,1,0,65,0);
//
//            addTimedSetpoint(1,1,.5,-40,69,-95);
//            addAutoModule(Extake);
//            addTimedSetpoint(1,1,.5,0,65,-95);
//            addTimedSetpoint(1,.8,.5,0,120,0);
//            addTimedSetpoint(1.0,1,1,-20,125,90);
//            addTimedSetpoint(1.0,1,3,-215,125,90);
//            addTimedSetpoint(1.0,1,1.5,-235,48,92);
//            addAutoModule(Drop);
//            RobotPart.pause(.5);
//            addAutoModule(Reset);
//
////            addTimedSetpoint(1,1,.5,-30,20,90);
////            addTimedSetpoint(1,.8,.5,-2,85,90);
////            addAutoModule(Extake);
////            addTimedSetpoint(1,.8,.3,-20,75,90);
////
////            addTimedSetpoint(1.0,1,1,-10,125,90);
////            addTimedSetpoint(1.0,1,3,-215,125,90);
////            addTimedSetpoint(1.0,1,1.5,-235,70,92);
////            addAutoModule(Drop3rd);
////            RobotPart.pause(.5);
////            addAutoModule(Reset);
//
//
//
//
//
//        }, () -> {
//
//            addTimedSetpoint(1.0,1,1.5,0,110,0);
//            addAutoModule(Extake);
//            addTimedSetpoint(1,.8,.5,0,120,0);
//            addTimedSetpoint(1.0,1,1,-20,125,90);
//            addTimedSetpoint(1.0,1,3,-215,125,90);
//            addTimedSetpoint(1.0,1,1.5,-235,60,92);
//            addAutoModule(Drop);
//            RobotPart.pause(.5);
//            addAutoModule(Reset);
//
//
//        }, () -> {
//            addTimedSetpoint(1.0,1,1,0,30,0);
//
//            addTimedSetpoint(1,.8,1,0,60,90);
//            addTimedSetpoint(1,.8,.5,-2,85,90);
//            addAutoModule(Extake);
//            addTimedSetpoint(1,.8,.3,-20,75,90);
//
//            addTimedSetpoint(1.0,1,1,-10,125,90);
//            addTimedSetpoint(1.0,1,3,-215,125,90);
//            addTimedSetpoint(1.0,1,1.5,-235,75,92);
//            addAutoModule(Drop3rd);
//            RobotPart.pause(1);
//            addAutoModule(Reset);
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
