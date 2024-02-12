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
//@Autonomous(name = "B. Right Stack ", group = "auto", preselectTeleOp = "TerraOp")
//public class BLUE_FAR_STACK extends AutoFramework {
//
//
//    @Override
//    public void initialize() {
//
//        this.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
////        outtake.moveStart();
////        outtake.openClawHalf();
//        propCaseDetected = TeamProp.FIRST;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "right");
//
//    }
//    AutoModule Intake = new AutoModule(
//            intake.stageMiddle(.1),
//            intake.moveTime(-.8,2)
//
//    );
//    AutoModule Intake2 = new AutoModule(
//            intake.stageStart(.1),
//            intake.moveTime(-1,2)
//
//    );
//
//    AutoModule Extake = new AutoModule(
//            intake.moveTime(.2,.8)
//
//
//    );
//    AutoModule Drop = new AutoModule(
//            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
//            lift.stageLift(.8,20),
//            outtake.stageEndAuto(.2),
//            RobotPart.pause(.5),
//            outtake.stageOpen(.2),
//            RobotPart.pause(.5),
//            lift.stageLift(.8,25)
//    );
//    AutoModule Drop3rd = new AutoModule(
//            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
//            lift.stageLift(.8,22),
//            outtake.stageEndAuto(.2),
//            RobotPart.pause(.5),
//            outtake.stageOpen(.2),
//            RobotPart.pause(.5),
//            lift.stageLift(.8,25)
//    );
//    AutoModule Reset = new AutoModule(
//            outtake.stageStart(1),
//            lift.stageLift(.6,0)
//
//    );
//
//    AutoModule dynamicModule = new AutoModule(
//            outtake.stageStart(colorSensorsNew.exitIntake()),
//            lift.stageLift(.6,0)
//
//    );
//    @Override
//    public void define() {
//        customCase(() -> {
//            addPause(10);
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
//
//
//        }, () -> {
//            addPause(10);
//
//            addTimedSetpoint(1.0,1,2,0,115,0);
//            addAutoModule(Extake);
//            addTimedSetpoint(1,.8,.5,0,120,0);
//            addTimedSetpoint(1.0,1,1,-20,125,90);
//            addTimedSetpoint(1.0,1,3,-215,125,90);
//            addTimedSetpoint(1.0,1,1.5,-235,62,92);
//            addAutoModule(Drop);
//            RobotPart.pause(.5);
//            addAutoModule(Reset);
//            addTimedSetpoint(1.0,1,.5,-200,62,92);
//
//            addTimedSetpoint(1.0,1,1,-180,125,90);
//            addTimedSetpoint(1.0,1,1.5,0,125,90);
//            addTimedSetpoint(1.0,1,1.5,44,130,94);
//            addAutoModule(Intake);
//            addTimedSetpoint(1.0,1,1.5,42,130,94);
//
//            addAutoModule(Intake2);
//
//
//
////
//        }, () -> {
//            addPause(10);
//
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
