//package auton;
//
//
//import automodules.AutoModule;
//import autoutil.AutoFramework;
//import elements.Case;
//import robotparts.RobotPart;
//
//import static global.General.bot;
//import static global.Modes.Height.HIGH;
//
//public class TerraAutoRam extends AutoFramework {
//    boolean[] halt = {false};
//
//    boolean normal = false;
//
//    public static void normalInit(AutoFramework auto){
//        auto.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
//        lift.maintain();
//        OUTTAKE_OLD.closeClaw();
//        wait(0.5);
//        OUTTAKE_OLD.arm(0.42);
////        auto.scan(false);
//    }
//
//    static AutoModule BackwardFirst = new AutoModule(
//            lift.stageLift(1.0, heightMode.getValue(HIGH)+2).attach(OUTTAKE_OLD.stageReadyEndContinuous(0.6))
//    ).setStartCode(OUTTAKE_OLD::moveMiddle);
//
//    static AutoModule Forward = new AutoModule(
//            RobotPart.pause(1.0),
//            OUTTAKE_OLD.stageOpen(0.4),
//            lift.stageLift(1.0,0).attach(OUTTAKE_OLD.stageStartAfter(0.2))
//    ).setStartCode(OUTTAKE_OLD::moveEnd);
//
//    @Override
//    public void initialize() {
//        normalInit(this);
//        caseDetected = Case.SECOND;
//    }
//
//    public static void signal(AutoFramework f){
//        f.customSide(() -> {
//            f.addSegment(0.8, DefaultWP, 0, 30, 0);
//            f.addSegment(0.5, DefaultWP, -3,90,-30);
//            f.addSegment(1.0, nStopNewHaltSP, 11, 123, -30);
//        }, () -> {
//            f.addSegment(0.8, DefaultWP, 0, 30, 0);
//            f.addSegment(0.5, DefaultWP, -3,90,-30);
//            f.addSegment(1.0, nStopNewHaltSP, 11, 123, -30);
//        });
//    }
//
//    @Override
//    public void define() {
//
//        if(!normal) {
//            signal(this);
//            addSegment(0.6, DefaultWP, 5, 105, -30);
//        }else{
//            addTimedSetpoint(1.0, 0.4, 0.6, 0, 40, -90);
//            addSegment(0.5, DefaultWP, 0, 70, 0);
//            addTimedSetpoint(1.0, 0.5, 0.8, -20, 100, 0);
//        }
//
//        customNumber(5, i -> {
//            addTimedSetpoint(1.0, 0.5,1.5,2, 70, 0);
//            addSegment(1.5, 1.0, NonstopSP, 2, 155, 0);
//            addCustomCode(() -> {
//                if (odometry.getY() < -155) {
//                    halt[0] = true;
//                }
//                drive.halt();
//            });
//            addBreakpoint(() -> halt[0]);
//        });
//        addBreakpointReturn();
//
//        customSide(() -> {
//            addTimedSetpoint(1.0, 0.5, 0.6, -9, 126, 30);
//            addConcurrentAutoModuleWithCancel(BackwardFirst, 0.2);
//            addTimedSetpoint(1.0, 0.5, 1.3, -13, 138, 30);
//        }, () -> {
//            addTimedSetpoint(1.0, 0.5, 0.6, -14, 125, 30);
//            addConcurrentAutoModuleWithCancel(BackwardFirst, 0.2);
//            addTimedSetpoint(1.0, 0.5, 1.3, -18, 136, 30);
//        });
//        addConcurrentAutoModuleWithCancel(Forward, 1.2);
//        addTimedSetpoint(1.0, 0.4, 1.0, 0, 120, 0);
//        addSegment(0.5, nStopNewSP, 0, 73, 0);
//        customCase(() -> {
//            addTimedSetpoint(1.0, 0.5,2.0,-58, 73, 0);
//        }, () -> {
//
//        }, () -> {
//            addTimedSetpoint(1.0, 0.5,2.0,58, 73, 0);
//        });
//        addPause(0.1);
//    }
//
//
//    @Override
//    public void postProcess() { autoPlane.reflectY(); autoPlane.reflectX(); }
//
//
////    @Autonomous(name = "G. RIGHT RAM (No SS) ", group = "auto", preselectTeleOp = "TerraOp")
////    public static class RIGHT extends TerraAutoRam {{ normal = true; fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.LOWER; startPose = new Pose(20.5, Field.width/2.0 - Field.tileWidth - GameItems.Cone.height - 16,90); }}
////
////    @Autonomous(name = "H. LEFT RAM (No SS) ", group = "auto", preselectTeleOp = "TerraOp")
////    public static class LEFT extends TerraAutoRam {{ normal = true; fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.UPPER; startPose = new Pose(20.5, Field.width/2.0 + Field.tileWidth + GameItems.Cone.height + 16,90); }}
////
////
////    @Autonomous(name = "I. RIGHT RAM (SS) ", group = "auto", preselectTeleOp = "TerraOp")
////    public static class RIGHT_RAM extends TerraAutoRam {{ fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.LOWER; startPose = new Pose(20.5, Field.width/2.0 - Field.tileWidth - GameItems.Cone.height - 16,90); }}
////
////    @Autonomous(name = "J. LEFT RAM (SS) ", group = "auto", preselectTeleOp = "TerraOp")
////    public static class LEFT_RAM extends TerraAutoRam {{ fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.UPPER; startPose = new Pose(20.5, Field.width/2.0 + Field.tileWidth + GameItems.Cone.height + 16,90); }}
////
//}
