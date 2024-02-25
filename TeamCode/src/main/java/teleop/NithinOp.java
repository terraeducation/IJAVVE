package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static autoutil.vision.PixelScannerIntegrate.locations;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.SLOW;
import static global.Modes.Drive.SUPERSLOW;
import static global.Modes.Height.currentHeight;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.INTAKING;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.OuttakeStatus.PLACING2;
import static teleutil.button.Button.*;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class NithinOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;

        gph1.link(LEFT_BUMPER, R_BUMPER);
        gph1.link(RIGHT_BUMPER, L_BUMPER);
        gph1.linkWithCancel(RIGHT_TRIGGER, outtakeStatus.isMode(DRIVING), X_BUTTON, L_TRIGGER);
        gph1.linkWithCancel(LEFT_TRIGGER, driveMode.isMode(SLOW), R_TRIGGER, CancelIntake);
        gph1.linkWithCancel(LEFT_STICK_BUTTON,  driveMode.isMode(SLOW), IntakeMid, CancelIntake);

        gph1.link(DPAD_RIGHT,  outtakeStatus.isMode(DRIVING), chubramani, RIGHT_DPAD );
        gph1.link(DPAD_LEFT, outtakeStatus.isMode(DRIVING), HangStart, LEFT_DPAD);
        gph1.link(DPAD_UP, outtakeStatus.isMode(DRIVING), HangReady,  UP_DPAD);
        gph1.link(DPAD_DOWN, driveMode.isMode(SLOW), Hang,  DOWN_DPAD );
        gph1.link(RIGHT_STICK_BUTTON, joyoi);



        gph1.link(X, outtakeStatus.isMode(PLACING), outtakeStatus.isMode(PLACING2), levelone, leveltwo);
        gph1.link(A, outtakeStatus.isMode(PLACING), outtakeStatus.isMode(PLACING2), levelthree, levelfour);
        gph1.link(B, outtakeStatus.isMode(PLACING), outtakeStatus.isMode(PLACING2), levelfive, levelsix);
        gph1.link(Y, outtakeStatus.isMode(PLACING), outtakeStatus.isMode(PLACING2), levelseven, levelseven);






//        gph2.link(RIGHT_BUMPER, outtake::openClaw);
//        gph2.link(LEFT_BUMPER, outtake::closeClaw);
//        gph2.linkWithCancel(RIGHT_TRIGGER, outtakeStatus.isMode(DRIVING), X_BUTTON, L_TRIGGER);
//        gph2.linkWithCancel(X, outtakeStatus.isMode(PLACING), levelone, leveltwo);
//        gph2.linkWithCancel(A, outtakeStatus.isMode(PLACING), levelthree, levelfour);
//        gph2.linkWithCancel(B, outtakeStatus.isMode(PLACING), levelfive, levelsix);
//        gph2.linkWithCancel(Y, outtakeStatus.isMode(PLACING), levelseven, levelseven);










        /**
         * Start code
         */
        outtake.moveStart();
        outtake.moveStartRotate();
        outtake.openClaw();
        outtake.moveStartPivot();
        intake.moveInit();
        driveMode.set(SLOW);
        outtakeStatus.set(DRIVING);


    }

    @Override
    public void startTele() {


    }

    @Override
    public void loopTele() {

        drive.newMove(gph1.ry, gph1.rx, gph1.lx);
        lift.move(gph2.ry);


        /**
         * Gets Distance
         */

//        log.show("right distance (cm)", distanceSensorsNew.getCMDistanceRight());
//        log.show("left distance (cm)", distanceSensorsNew.getCMDistanceLeft());

        /**
         * Gets light of color sensor
         */
//        log.show("light 1", colorSensorsNew.getLight1());
//        log.show("light 2", colorSensorsNew.getLight2());


        /**
         * odo pose
         */
//        log.show("pose", odometry.getPose());

        /**
         * Outtake Status
         */
//        log.show("OuttakeStatus", outtakeStatus.get());

        /**
         * Heading
         */
//        log.show("heading", gyro.getHeading());


        /**
         * lift encoder positions
         */
        log.show("Right", lift.motorRight.getPosition());
        log.show("Left", lift.motorLeft.getPosition());


        /**
         * drive mode
         */
//        log.show("DriveMode", driveMode.get());

        /**
         * outtake status
         */
//                log.show("outske", outtakeStatus.get());
        /**
         * heights
         */
//        log.show("current height", current.getValue(currentHeight));

    }

}


