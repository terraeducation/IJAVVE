package automodules;

import automodules.stage.Stage;
import robot.RobotUser;
import robotparts.RobotPart;

import static global.Modes.*;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.GROUND;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.INTAKING;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.OuttakeStatus.PLACING2;


public interface AutoModuleUser extends RobotUser {

    /**
     * Forward
     */

    AutoModule L_BUMPER = new AutoModule(
        outtake.stageOpen1(.1)
    ).setStartCode(() ->{

    });

    AutoModule R_BUMPER = new AutoModule(
        outtake.stageOpen2(.1)
    ).setStartCode(() ->{

    });

    AutoModule L_TRIGGER = new AutoModule(
        outtake.stageOpen(.1),
        outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            lift.stageLift(1, 5).attach(outtake.stageThruPivot(.1)),
        outtake.stageStartPivot(.1).attach(lift.stageLift(1,0))

    ).setStartCode(() ->{
        heightMode.set(GROUND);
        outtakeStatus.set(DRIVING);


    });

        AutoModule R_TRIGGER = new AutoModule(
                outtake.stageLock(.3),
                intake.stageStart(.2).attach(outtake.stageOpen(.1)),
                intake.moveSmart(-.45),
                outtake.stageClose(.5),
                intake.moveTime(1,.2),
                intake.stageInit(.2)


        ).setStartCode(() -> {
            outtakeStatus.set(INTAKING);

        });


    AutoModule A_BUTTON = new AutoModule(
            lift.stageLift(1, 35)

            ).setStartCode(() ->{
        heightMode.set(MEDIUM);


    });

    AutoModule IntakeMid = new AutoModule(

            intake.stageInit(.2)

    ).setStartCode(() -> {

    });

    AutoModule IntakeMider = new AutoModule(
            intake.stageMiddler(.2)

    ).setStartCode(() -> {


    });

//    AutoModule Y_BUTTON = new AutoModule(
//            lift.stageLift(1, adjustHeight)
//
//    ).setStartCode(() ->{
//
//
//    });

    AutoModule X_BUTTON = new AutoModule(
//            outtake.stageMiddle(.2),
            outtake.stageClose(.1),
            lift.stageLift(1, 15).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.4).attach(outtake.stageTransferPivot(.4)),
            RobotPart.pause(.3),
            outtake.stageEndPivot(.2).attach(outtake.stageStartRotate(.2))


    ).setStartCode(() ->{
        heightMode.set(LOW);
        outtakeStatus.set(PLACING);
    });

    AutoModule B_BUTTON = new AutoModule(
            lift.stageLift(1, 45)

    ).setStartCode(() ->{
        heightMode.set(HIGH);

    });

    AutoModule UP_DPAD = new AutoModule(
            outtake.stageStackRotate(.2)

    ).setStartCode(() ->{

    });

    AutoModule DOWN_DPAD = new AutoModule(
        intake.removeSmart(.8).attach(intake.stageMiddle(.1))
    ).setStartCode(() ->{

    });

    AutoModule LEFT_DPAD = new AutoModule(
        outtake.stageLeftRotate(.2)

    ).setStartCode(() ->{

    });

    AutoModule RIGHT_DPAD = new AutoModule(
            outtake.stageTransferRotate(.2)


    ).setStartCode(() ->{

    });

//
    AutoModule CancelIntake = new AutoModule(
        outtake.stageClose(.5),
        intake.moveTime(0,.1).attach(intake.stageInit(.1))



).setStartCode(() ->{
        outtakeStatus.set(DRIVING);



    });

    AutoModule levelone = new AutoModule(
            lift.stageLift(1, 20)

    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);


    });

    AutoModule leveltwo = new AutoModule(
            lift.stageLift(1, 25)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING);

//
    });

    AutoModule levelthree = new AutoModule(
            lift.stageLift(1, 30)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);

//
    });

    AutoModule levelfour = new AutoModule(
            lift.stageLift(1, 37)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING);

//
    });

    AutoModule levelfive = new AutoModule(
            lift.stageLift(1, 40)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);

//
    });
    AutoModule levelsix = new AutoModule(
            lift.stageLift(1, 45)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING);

//
    });

    AutoModule levelseven = new AutoModule(
            lift.stageLift(1, 50)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);

//
    });



//    AutoModule Extake = new AutoModule(
//
//
//            intake.moveTime(.8, 1)
//
//
//    ).setStartCode(() ->
//    {
//
//
//
//    });
//
    AutoModule HangReady = new AutoModule(
            lift.stageLift(1,40)
    ).setStartCode(() ->{

    });
    AutoModule Hang = new AutoModule(
            lift.stageLift(.5,heightMode.getValue(GROUND))
    ).setStartCode(() ->{

    });
//

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


//    AutoModule PlaceLow = new AutoModule(
//            lift.stageLift(1, 4),
//            RobotPart.pause(2),
//            outtake.stageTransferRotate(.2),
//            lift.stageLift(1, 20),
//            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
//            outtake.stageStartRotate(.2)
//
//
//
//            ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(LOW);
//        outtakeStatus.set(PLACING);
//
//
//    });
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

//    AutoModule PlaceHigh = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)).attach(outtake.stageEndContinuousWithFlip(.5, 0))
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(HIGH);
//    });
//
//    AutoModule ResetLift = new AutoModule(lift.moveTime(-0.3, 0.5),  lift.resetLift()).setStartCode(() -> {
//        lift.ground = false;
//        outtakeStatus.set(DRIVING);
//    });
//
}


