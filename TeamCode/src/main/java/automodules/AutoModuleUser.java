package automodules;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

import automodules.stage.Main;
import automodules.stage.Stage;
import autoutil.reactors.MecanumJunctionReactor2;
import autoutil.reactors.Reactor;
import autoutil.vision.JunctionScannerAll;
import elements.Field;
import elements.Robot;
import geometry.framework.Point;
import geometry.position.Pose;
import global.Modes;
import math.polynomial.Linear;
import robot.RobotUser;
import robotparts.RobotPart;
import robotparts.electronics.output.OLed;
import teleop.TerraOp;
import teleutil.TeleTrack;
import teleutil.independent.Independent;
import teleutil.independent.Machine;
import util.codeseg.CodeSeg;
import util.condition.OutputList;
import util.template.Iterator;
import util.template.Precision;

import static global.General.bot;
import static global.General.log;
import static global.Modes.*;
import static global.Modes.Height.GROUND;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.Height.MIDDLE;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.PLACING;


public interface AutoModuleUser extends RobotUser{

//
//    CodeSeg stack = () -> {bot.addAutoModule(AutoModuleUser.ForwardStackTele(lift.stackedMode)); lift.stackedMode++;};
//    TeleTrack kappaBefore = new TeleTrack(
//            new TeleTrack.Step(heightMode.setTo(LOW)), // LOW
//            new TeleTrack.Step(heightMode.setTo(LOW)), // TERMINAL
//            new TeleTrack.Step(heightMode.setTo(LOW)), // LOW
//            new TeleTrack.Step(heightMode.setTo(LOW)).add(stack), // LOW
//            new TeleTrack.Step(heightMode.setTo(HIGH)).add(stack), // HIGH
//            new TeleTrack.Step(heightMode.setTo(GROUND)).add(stack), // GROUND
//            new TeleTrack.Step(heightMode.setTo(GROUND)), // TERMINAL
//            new TeleTrack.Step(heightMode.setTo(GROUND)), // GROUND
//            new TeleTrack.Step(heightMode.setTo(LOW)) // LOW + CAP
//    );
//
//    static void enableKappa(){ kappaBefore.enable(); }
//    static void disableKappa(){ kappaBefore.disable(); }

    /**
     * Tele
     */


    /**
     * Forward
     */


    AutoModule ForwardTeleHigh = new AutoModule(
            outtakeStatus.ChangeMode(DRIVING),
            outtake.stageEnd(0.1),
            outtake.stageOpen(0.0),
            lift.resetCutoff(),
            lift.stageLift(0.9,0).attach(outtake.stageStartAfter(0.1))
    );

    AutoModule ForwardTeleMiddle = new AutoModule(
            outtakeStatus.ChangeMode(DRIVING),
            outtake.stageEnd(0.1),
            outtake.stageOpen(0.0),
            drive.moveTime(1.0, 0.0, 0.0, 0.22),
            outtake.stageStart(0.0),
            lift.resetCutoff(),
            lift.stageLift(1.0,0)
    );

    AutoModule ForwardTeleLow = new AutoModule(
            outtakeStatus.ChangeMode(DRIVING),
            outtake.stageEnd(0.1),
            outtake.stageOpen(0.0),
            drive.moveTime(1.0, 0.0, 0.0, 0.25),
            outtake.stageStart(0.0),
            lift.resetCutoff(),
            lift.stageLift(1.0,0)
    );

    AutoModule ForwardTeleGround = new AutoModule(
            outtakeStatus.ChangeMode(DRIVING),
            outtake.stageStart(0.0),
            outtake.stageOpen(0.0)
    );

    /**
     * Backward
     */

    AutoModule BackwardPlaceHighTele = new AutoModule(
            lift.changeHigh(false),
            outtake.stageReadyEnd(0.0),
            lift.stageLift(1.0, heightMode.getValue(HIGH)+2)
    );

    AutoModule BackwardGrabHighTele = new AutoModule(
            heightMode.ChangeMode(HIGH),
            outtakeStatus.ChangeMode(PLACING),
            lift.changeHigh(true),
            outtake.stageClose(0.22),
            lift.moveTimeBack(-0.2, 1.0, () -> {if(lift.stacked){ lift.stacked = false; return 0.3;}else{return 0.0;}}),
            outtake.stageMiddle(0.0),
            lift.stageLift(1.0, heightMode.getValue(MIDDLE)+2)
    );

    OutputList BackwardHighTele = new OutputList(outtakeStatus::get).addOption(DRIVING, BackwardGrabHighTele).addOption(PLACING, ForwardTeleHigh);

    AutoModule BackwardGrabMiddleTele = new AutoModule(
            outtakeStatus.ChangeMode(PLACING),
            heightMode.ChangeMode(MIDDLE),
            outtake.stageClose(0.22),
            lift.moveTimeBack(-0.2, 1.0, () -> {if(lift.stacked){ lift.stacked = false; return 0.3;}else{return 0.0;}}),
            lift.stageLift(1.0, heightMode.getValue(MIDDLE)+2).attach(outtake.stageReadyEndAfter(0.1))
    );

    OutputList BackwardMiddleTele = new OutputList(outtakeStatus::get).addOption(DRIVING, BackwardGrabMiddleTele).addOption(PLACING, ForwardTeleMiddle);

    AutoModule BackwardGrabLowTele = new AutoModule(
            outtakeStatus.ChangeMode(PLACING),
            heightMode.ChangeMode(LOW),
            outtake.stageClose(0.22),
            lift.moveTimeBack(-0.2, 1.0, () -> {if(lift.stacked){ lift.stacked = false; return 0.3;}else{return 0.0;}}),
            lift.changeCutoff(0.0),
            outtake.stageReadyEndAfter(0.0),
            lift.stageLift(1.0, heightMode.getValue(LOW)+2)
    );

    OutputList BackwardLowTele = new OutputList(outtakeStatus::get).addOption(DRIVING, BackwardGrabLowTele).addOption(PLACING, ForwardTeleLow);


    AutoModule BackwardGrabGroundTele = new AutoModule(
            outtakeStatus.ChangeMode(PLACING),
            heightMode.ChangeMode(GROUND),
            lift.changeGround(true),
            outtake.stageClose(0.22),
            outtake.stageStart(0.0),
            lift.moveTimeBack(-0.2, 1.0, () -> {if(lift.stacked){ lift.stacked = false; return 0.3;}else{return 0.0;}}),
            lift.changeCutoff(0.0),
            lift.stageLift(1.0, heightMode.getValue(GROUND)+2)
    );

    AutoModule BackwardPlaceGroundTele = new AutoModule(
            lift.changeGround(false),
            outtakeStatus.ChangeMode(PLACING),
            heightMode.ChangeMode(GROUND),
            lift.resetCutoff(),
            lift.moveTime(-0.35, 0.35)
    );

    OutputList BackwardGroundTele = new OutputList(outtakeStatus::get).addOption(DRIVING, BackwardGrabGroundTele).addOption(PLACING, ForwardTeleGround);

    AutoModule ForwardTeleBottom = new AutoModule(
            outtakeStatus.ChangeMode(DRIVING),
            lift.changeHigh(false),
            outtake.stageStart(0.0),
            outtake.stageOpen(0.0),
            lift.resetCutoff(),
            lift.stageLift(1.0,0)
    );


    AutoModule ResetLift = new AutoModule(lift.moveTime(-0.3, 0.5),  lift.resetLift() );
//    AutoModule RetractOdometry = new AutoModule(drive.stageRetract());
//    AutoModule EngageOdometry = new AutoModule(drive.stageEngage());
    AutoModule UprightCone = new AutoModule(lift.stageLift(1.0, 15));
    AutoModule TakeOffCone = new AutoModule(heightMode.ChangeMode(HIGH), outtakeStatus.ChangeMode(PLACING), outtake.stageClose(0.0), lift.stageLift(1.0, heightMode.getValue(HIGH)+3.5).attach(outtake.stageReadyStartAfter(0.5)),RobotPart.pause(0.3),outtake.stageFlip(0.0));

    static AutoModule ForwardStackTele(int i){return new AutoModule(
            lift.changeCutoff(2),
            outtake.stageOpen(0.0),
            outtake.stageStart(0.0),
            lift.stageLift(1.0,  i == 0 ? 14.5 : Math.max(15.0 - (i*15.0/5.0), 0))
    );}

    /**
     * Misc
     */
    Independent MoveToZero = new Independent() { @Override public void define() {addSetpoint(0.0, 0.01, 0.0); }};
    Independent MoveToCycleStart = new Independent() {
        @Override
        public void define() {
            addCustomCode(ResetOdometryForCycle(cyclePointStart), 0.4);
            addWaypoint(1.0, -13, -26, 0);
            addSegment(0.8, 0.7, mecanumNonstopSetPoint, 0, 1.0, 0);
            addCustomCode(DriveMode(true));
            addAutoModule(ForwardTeleBottom);
        }
    };


    /**
     * Cycle
     */
    Point cyclePointStart = new Point(Field.halfWidth-19, -51);
    Point cyclePoint = new Point(46.5, -50);
    static CodeSeg ResetOdometryForCycle(Point point) {return  () -> {
        distanceSensors.ready();
        Point point1 = new Point(distanceSensors.getRightDistance() - point.getX(),-distanceSensors.getFrontDistance() - point.getY());
        odometry.setCurrentPoint(point1); odometry.setCurrentPoint(point1);
        odometry.setHeading(0); odometry.setHeading(0);
        gyro.reset(); gyro.reset();
    };}

    static CodeSeg DriveMode(boolean slow){return () -> {drive.slow = slow;};}

//    static CodeSeg SoftResetOdometryForCycle(Point point) {return  () -> {
//        distanceSensors.ready();
//        double front = distanceSensors.getFrontDistance();
//        double right = distanceSensors.getRightDistance();
//        if(Precision.range(front, 45, 51) && Precision.range(right, 44, 50)){
//            Point point1 = new Point(right - point.getX(),-front - point.getY());
//            odometry.setPoseUsingOffset(point1);
//        }
//    };}

    static AutoModule BackwardCycle(Height height, double offset) {return new AutoModule(
            outtake.stageClose(0.18),
            outtake.stageReadyEnd(0.0),
            lift.stageLift(1.0, heightMode.getValue(height)+offset)
    );}

    static AutoModule BackwardCycleMove(Height height, double offset) {return new AutoModule(
            outtake.stageClose(0.0),
            drive.moveTime(0.5,0.2),
            outtake.stageReadyEnd(0.0),
            lift.stageLift(1.0, heightMode.getValue(height)+offset)
    );}

    AutoModule ForwardCycle = new AutoModule(
            outtake.stageEnd(0.1),
            lift.moveTime(-0.7, 0.1).attach(outtake.stageOpen(0.0)),
            lift.stageLift(1.0,0).attach(outtake.stageStartAfter(0.05))
    );
    // TODO FIX ATTACH

//    AutoModule ForwardCycle = new AutoModule(
//            outtake.stageEnd(0.1),
//            Stage.attach(lift.moveTime(-0.7, 0.15), outtake.stageOpen(0.0)),
////            lift.moveTime(-0.7, 0.15),
//            outtake.stageStart(0.0),
//            lift.stageLift(1.0, 0)
//
//                    //.attach(outtake.stageOpen(0.0)),
////            Stage.attach(lift.stageLift(1.0, 0), outtake.stageStartAfter(0.05))
////            lift.stageLift(1.0,0)
//                    //.attach(outtake.stageStartAfter(0.05))
//    );


    AutoModule StageStart = new AutoModule(outtake.stageStart(0.0));
    AutoModule ReadyStart = new AutoModule(outtake.stageReadyStart(0.0));

    AutoModule HoldMiddle = new AutoModule(outtake.stageClose(0.18), outtake.stageMiddle(0.0));

    AutoModule ForwardCycleLow = new AutoModule(
            lift.moveTime(1.0, 0.1).attach(outtake.stageEnd(0.2)),
            outtake.stageOpen(0.0),
            lift.stageLift(1.0,0).attach(outtake.stageStartAfter(0.1))
    );

    Independent CycleFirst = new Independent() {
        @Override
        public void define() {
            addCustomCode(ResetOdometryForCycle(cyclePoint), 0.4);
        }
    };

    static Independent Cycle(int i) {return new Independent() {
        @Override
        public void define() {
            addSegment(0.25, 0.2, mecanumNonstopSetPoint,  -1, 16+(i*0.1),0.1);
            addConcurrentAutoModuleWithCancel(BackwardCycle(HIGH, 5), 0.2);
            addWaypoint(0.52, -1, -26, 0.1);
            addSegment(0.3, 0.8, mecanumNonstopSetPoint, -1, -32.5, 0.1);
            addConcurrentAutoModuleWithCancel(ForwardCycle);
            addCustomCode(() -> {
                ArrayList<Double> values = new ArrayList<>();
                whileNotExit(() -> values.size() > 3 || odometry.getY() > 10, () -> {
                    drive.move(0.4,0.12*Math.signum(Precision.attract(-odometry.getX()-1, 1)), 0.008*odometry.getHeading());
                    distanceSensors.ready();
                    double distance = distanceSensors.getRightDistance();
                    if(distance < 50){ values.add(distance); }
                });
                double avgDis = Iterator.forAllAverage(values);
                double dis  = -distanceSensors.getFrontDistance()-cyclePoint.getY();
                double y = odometry.getY();
                if(!Precision.range(dis-y, 10)){ dis = y; }else{ dis = (dis*0.75) + (y*0.25);}
                Point point = new Point(((avgDis-49.0)*0.75)+(odometry.getX()*0.25), dis);
                odometry.setPoseUsingOffset(point);
            });
        }
    };}

    Independent CycleLast = new Independent() {
        @Override
        public void define() {
            addSegment(0.8, 0.7, mecanumNonstopSetPoint, -2, 0.01, 0);
            addCustomCode(DriveMode(false));
        }
    };

    Machine MachineCycle = new Machine()
            .addIndependent(CycleFirst)
            .addIndependent(8, AutoModuleUser::Cycle)
            .addIndependent(AutoModuleUser.CycleLast)
    ;






    /**
     * Cycle Extra
     */


    // TODO FIX POSITIONS
    Machine MachineCycleExtra = new Machine()
            .addInstruction(DriveMode(true))
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addCustomCode(ResetOdometryForCycle(cyclePoint), 0.4);
                    addSegment(0.3, 0.3, mecanumNonstopSetPoint,  -2, 16,0.01);
                    addAutoModule(HoldMiddle);
                    addWaypoint(0.6,-2,-10,-10.0);
                    addConcurrentAutoModule(BackwardCycle(MIDDLE, 4));
                    addWaypoint(1.0, -25.0, -12.0, -25.0);
                    addSegment(0.8, 0.55, mecanumNonstopSetPoint, -48.5, -25.0, -25.0);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(ForwardCycle);
                    addWaypoint(0.5, -23.5, 16.0, -21.0);
                    addSegment(0.5, 0.1, mecanumNonstopSetPoint, -30.0, 22.0, -21.0);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(BackwardCycleMove(LOW, 2), 0.25);
                    addWaypoint(1.0, -25.0, 25.0, 0.0);
                    addWaypoint(1.0, -30.0, 5.0, -45);
                    addWaypoint(1.0, -50.0, -8.0, -85);
                    addSegment(1.3, 0.65, mecanumNonstopSetPoint, -99.0, -35.0, -50);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(ForwardCycleLow, 0.1);
                    addWaypoint(1.0, -70.0, -20.0, -75);
                    addWaypoint(1.0, -52.0, -10.0, -42);
                    addWaypoint(0.65, -30.0, 0.0, 0.0);
                    addSegment(1.1, 0.5, mecanumNonstopWayPoint,  -25.0, 36.0, -21.0);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(BackwardCycleMove(LOW, 1), 0.25);
                    addSegment(1.3, 0.5, mecanumNonstopSetPoint, -35.0, 23.0, -57.0);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(ForwardCycleLow,0.1);
                    addWaypoint(-25.0, 36.0, -57.0);
                    addSegment(1.0, 0.8, mecanumNonstopSetPoint, -32.0, 20.0, -21.0);
                    addSegment(0.6, 0.5, mecanumNonstopWayPoint,  -25.0, 36.0, -21.0);
                }
            })
            .addIndependentWithPause(new Independent() {
                @Override
                public void define() {
                    addConcurrentAutoModuleWithCancel(BackwardGrabLowTele, 0.25);
                    addSegment(0.5, 0.7, mecanumNonstopWayPoint,  -25.0, 40.0, -90.0);
                    addWaypoint(1.0, -64.0, 40.0, -90.0);
                    addWaypoint(1.0, -128.0, 37.0, -125.0);
                }
            })
            .addInstruction(DriveMode(false))
    ;

}
