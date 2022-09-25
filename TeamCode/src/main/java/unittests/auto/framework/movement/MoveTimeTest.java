package unittests.auto.framework.movement;

import robotparts.hardware.mecanum.MecanumDrive;
import unittests.auto.AutoUnitTest;
import util.Timer;

import static global.General.bot;


public class MoveTimeTest extends AutoUnitTest {
    /**
     * Tests moving for some time
     */

    private final MecanumDrive part = drive;

    /**
     * Run method uses while time (Robot moves forward at .3 power for 1 s)
     */
    @Override
    protected void run() {
        whileTime(() -> part.move(0.3, 0, 0), 1);
    }

}