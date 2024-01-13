package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Drive.FAST;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.PLACING;
import static teleutil.button.Button.*;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class NithinOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;

        gph1.link(LEFT_BUMPER, L_BUMPER);
        gph1.link(RIGHT_BUMPER, R_BUMPER);
        gph1.link(RIGHT_TRIGGER, L_TRIGGER);
        gph1.link(LEFT_TRIGGER, R_TRIGGER);

        gph1.link(DPAD_DOWN, DOWN_DPAD);
        gph1.link(DPAD_RIGHT, RIGHT_DPAD);
        gph1.link(DPAD_LEFT, LEFT_DPAD);
        gph1.link(DPAD_UP, UP_DPAD);


        gph1.link(X, X_BUTTON);
        gph1.link(A, A_BUTTON);
        gph1.link(B, B_BUTTON);
        gph1.link(Y, Y_BUTTON);



        gph2.link(A, outtake::openClaw);
        gph2.link(B, outtake::closeClaw);



        /**
         * Start code
         */
        outtake.moveStart();
        intake.moveInit();
        driveMode.set(FAST);
        outtakeStatus.set(DRIVING);
        lift.reset();

    }

    @Override
    public void startTele() {
        lift.reset();
//        Outtake.moveStart();


    }

    @Override
    public void loopTele() {

        drive.newMove(gph1.ry, gph1.rx, gph1.lx);
        lift.move(gph2.ry);

//        log.show("", odometry.getEncX());

        /**
         * Gets Distance
         */

//        log.show("right distance (cm)", distanceSensorsNew.getCMDistanceRight());
//        log.show("left distance (cm)", distanceSensorsNew.getCMDistanceLeft());

        /**
         * Gets light of color sensor
         */
//        log.show("light", colorSensorsNew.getLight());

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
//        log.show("Right", lift.motorRight.getPosition());
//        log.show("Left", lift.motorLeft.getPosition());


        /**
         * drive mode
         */
//        log.show("DriveMode", driveMode.get());

    }

}


