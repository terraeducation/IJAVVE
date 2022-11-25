package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

//@Disabled
@Autonomous(name = "TerraAutoTest2", group = "auto")
public class TerraAutoTest2 extends AutoFramework {

    @Override
    public void initAuto() {
        setConfig(mecanumDefaultConfig);
//        bot.addBackgroundTask(lift.holdPosition());
//        scan();

    }

    @Override
    public void define() {

        addScaledSetpoint(0.3, 300, 0, 0);


//        addSetpoint(0, 0,-90);
//        addSetpoint(100,0,-90);
//        addSetpoint(0, 0, -90);
//
//        addScaledSetpoint(0.1, 0, 20, 0);
//        addSetpoint(0,0,0);
//        addScaledSetpoint(1.0, 0, 20, 0);
//        addSetpoint(0,0,0);
//        addScaledWaypoint(1.5, 0, 30, 0);
//        addScaledWaypoint(0.5, 0, 0,0);
//        addConcurrentAutoModule(Backward);
//        addPause(0.5);
//        addConcurrentAutoModule(Forward);
//        addPause(5);
//        addWaypoint(6,-20, 0);
//        addWaypoint(8,-40,0);
//        addWaypoint(6, -20, 0);
//        addWaypoint(7, -10, 0);
//
//        addWaypoint(6, -138, 50);
//        addWaypoint(7, -128, 90);

//        addWaypoint(20, -140, 90);
//        addWaypoint(55, -130, 70);
//        addAutoModule(Backward);
//        addSetpoint(62, -70, 0);
//        addSetpoint(7, -30, 55);
//        addWaypoint(15,20,0);
//        addWaypoint(0,40,90);
//        addSetpoint(0,0, 180);
//        addSetpoint(0,0,360);
//        addSetpoint(20,40,90);
    }
}