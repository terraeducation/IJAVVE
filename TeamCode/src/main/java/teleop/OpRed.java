package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.voltageScale;
import static global.Modes.TeleStatus.RED;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.DPAD_LEFT;
import static teleutil.button.Button.DPAD_RIGHT;
import static teleutil.button.Button.DPAD_UP;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;


@TeleOp(name = "TeleOp", group = "TeleOp")
public class OpRed extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;

gph1.link(RIGHT_TRIGGER, Grab);
gph1.link(LEFT_TRIGGER, Intake);
gph1.link(RIGHT_BUMPER, PlaceHigh);
gph1.link(LEFT_BUMPER, Place);
gph1.link(X, SpecimenGrab);
gph1.link(Y, SpecimenLift);
gph1.link(A, SpecimenUp);
gph1.link(B, SpecimenDown);
gph1.link(DPAD_DOWN, ()-> intake.moveStart1());
        gph1.link(DPAD_RIGHT, ()-> intake.moveStart2());
        gph1.link(DPAD_UP, ()-> intake.moveStart3());
        gph1.link(DPAD_LEFT, () -> intake.moveEnd2());



        teleStatus.set(RED);


    }

    @Override
    public void startTele() {
        /**
         * Start code
         */
       intake.moveStartArm();
       intake.moveLinkStart();
       intake.moveOpen();
       outtake.moveStart();


    }

    @Override
    public void loopTele() {
        drive.newMove(gph1.ly, gph1.lx, gph1.rx);
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
//        log.show("Right", lift.motorRight.getPosition());


        /**
         * extendo encoder positions
         */
//        log.show("left", extendo.motorLeft.getPosition());

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


