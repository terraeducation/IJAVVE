package robot;

import static robot.RobotConfig.setConfig;

public class Configs implements RobotUser{

    /**
     * Used to test only the test part
     */
    RobotConfig TestConfig = new RobotConfig(customTestPart);
    RobotConfig TestConfig2 = new RobotConfig(testPart2);
    RobotConfig TestConfig3 = new RobotConfig(drive, camera);

    /**
     * Used for PowerPlay robot
     */
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry, distanceSensors);
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry);

    RobotConfig CenterStage = new RobotConfig(drive, gyro, intake, lift, odometry);

    /**
     * Current Config
     */
    public void setCurrentConfig(){
        setConfig(CenterStage);
    }

}
