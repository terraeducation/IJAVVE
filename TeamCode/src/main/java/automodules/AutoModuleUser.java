package automodules;

import automodules.stage.Stop;
import robot.RobotUser;
import robotparts.RobotPart;
import teleutil.independent.Independent;
import teleutil.independent.Machine;

import static global.Modes.*;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.GROUND;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.Height.MIDDLE;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.PLACING;


public interface AutoModuleUser extends RobotUser {

    /**
     * Forward
     */

    AutoModule Intake = new AutoModule(

        intake.moveSmart(-.6)


    ).setStartCode(() ->
    {
//        outtake.stageStart(.2);


    });
    AutoModule Extake = new AutoModule(


            intake.moveTime(.8, 1)


    ).setStartCode(() ->
    {



    });

    AutoModule HangReady = new AutoModule(
            intake.stageMiddle(.1),
            lift.stageLift(.2,33)
    ).setStartCode(() ->{

    });
    AutoModule Hang = new AutoModule(
            lift.stageLift(.2,heightMode.getValue(GROUND))
    ).setStartCode(() ->{

    });


//    AutoModule PlaceReady = new AutoModule(
//            outtake.stageOpenHalf(.2).attach(outtake.stageMiddle(.2)),
//            outtake.stageClose(.2)
//
//
//    ).setStartCode(() ->
//    {
//
//
//        intake.stageStart(.2);
//
//
//    });
    //
//    AutoModule ManualClose = new AutoModule(
//            outtake.stageClose(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//
//    });
//    AutoModule ManualOpenFull = new AutoModule(
//            outtake.stageOpen(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//    });
//
//    AutoModule ManualOpenHalf = new AutoModule(
//            outtake.stageOpenHalf(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//    });
//    AutoModule PlaceAll = new AutoModule(
//            outtake.stageOpen(.2),
//            RobotPart.pause(.1),
//            outtake.stageMiddle(.2).attach(lift.stageLift(.8, heightMode.getValue(GROUND))),
//            outtake.stageStart(.2).attach(outtake.stageStartClaw(.2))
//
//    ).setStartCode(() -> {
//        driveMode.set(FAST);
//        heightMode.set(GROUND);
//
//
//    });

//    AutoModule PlaceOne = new AutoModule(
//            outtake.stageOpenHalf(.01)
//
//    ).setStartCode(() -> {
//
//
//
//    });


    AutoModule PlaceLow = new AutoModule(
            lift.stageLift(.5, 4),
            RobotPart.pause(2),
            outtake.stageTransferRotate(1),

            RobotPart.pause(0.05),
            lift.stageLift(.5, 11),
            RobotPart.pause(2),
            outtake.stageEnd(3).attach(outtake.stageTransfer(3))



            ).setStartCode(() -> {
        driveMode.set(SLOW);
        heightMode.set(LOW);
        outtakeStatus.set(PLACING);


    });
//    AutoModule PlaceMid = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(MIDDLE)).attach(outtake.stageMiddler(.5)),
//            outtake.stageEnd(.1).attach(outtake.stageClose(.1))
//
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(MIDDLE);
//        outtakeStatus.set(PLACING);
//
//
//
//    });

    AutoModule PlaceHigh = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)).attach(outtake.stageEndContinuousWithFlip(.5, 0))
    ).setStartCode(() -> {
        driveMode.set(SLOW);
        heightMode.set(HIGH);
    });

    AutoModule ResetLift = new AutoModule(lift.moveTime(-0.3, 0.5),  lift.resetLift()).setStartCode(() -> {
        lift.ground = false;
        outtakeStatus.set(DRIVING);
    });

}


