package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Drive.FAST;
import static teleutil.button.Button.*;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class NithinOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;

        gph1.link(LEFT_BUMPER, Intake);
        gph1.link(LEFT_TRIGGER, PlaceReady);

        gph1.link(X, PlaceLow);
        gph1.link(A, PlaceMid);
        gph1.link(Y, intake::moveMiddle);
        gph1.link(DPAD_UP, HangReady);
        gph1.link(DPAD_DOWN, Hang);
        gph1.link(DPAD_RIGHT, outtake::moveStart);
        gph1.link(DPAD_LEFT, intake::chubramani);

        gph1.link(B, Extake);

        gph1.link(RIGHT_TRIGGER, PlaceAll);
        gph1.link(RIGHT_BUMPER, PlaceOne);

        /**
         * Start code
         */
        outtake.moveStart();
        outtake.openClawFull();
        intake.moveStart();
        driveMode.set(FAST);
        lift.reset();

    }

    @Override
    public void startTele() {
        lift.reset();
        outtake.moveStart();


    }

    @Override
    public void loopTele() {

        drive.newMove(gph1.ry, gph1.rx, gph1.lx);
        lift.move(gph2.ry);

        log.show("DriveMode", driveMode.get());


















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


