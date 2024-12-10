package unittests.tele.framework;

import robot.Bot;
import unittests.tele.TeleUnitTest;
import util.User;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;


public class CommonTest extends TeleUnitTest {
    /**
     * Tests if common is working (which it probably is if the code can run)
     * NOTE: This also check the main user
     */
    @Override
    public void start() {
        fault.check("robotPartsIsEmpty", Expectation.SURPRISING, Magnitude.MAJOR, Bot.allRobotParts.size() == 0, false);
    }

    @Override
    public void loop() {
        /**
         * If you can see this common is working
         */
        log.show("Common is Working");
        /**
         * Check the main user (should be TELE)
         */
        log.show("Current User {TELE} ", mainUser.toString());
        /**
         * Throws a fault if the main user is not tele
         * NOTE: If this test does not crash, this works
         */
        fault.check("Main user is not TELE", mainUser.toString().equals(User.TELE.toString()), true);
    }
}
