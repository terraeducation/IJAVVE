package teleop;

import static global.General.log;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestOp TESTING ONLY", group = "TeleOp")
public class TestOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;

               /**
         * Start code
         */


    }

    @Override
    public void startTele() {



    }

    @Override
    public void loopTele() {


        /**
         * Gets Distance
         */

        log.show("right distance (cm)", distanceSensorsNew.getCMDistanceRight());
        log.show("left distance (cm)", distanceSensorsNew.getCMDistanceLeft());

        /**
         * Gets light of color sensor
         */
        log.show("light 1", colorSensorsNew.getLight1());
        log.show("light 2", colorSensorsNew.getLight2());


        /**
         * odo pose
         */
        log.show("pose", odometry.getPose());

        /**
         * Outtake Status
         */
        log.show("OuttakeStatus", robotStatus.get());

        /**
         * Heading
         */
        log.show("Gyro Heading", gyro.getHeading());


        /**
         * lift encoder positions
         */
        log.show("Right Motor", lift.motorRight.getPosition());
        log.show("Left Motor", lift.motorLeft.getPosition());


        /**
         * drive mode
         */
        log.show("DriveMode", driveMode.get());

    }

}


