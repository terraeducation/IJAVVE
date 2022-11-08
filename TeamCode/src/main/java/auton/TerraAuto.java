package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import autoutil.AutoFramework;
import elements.FieldSide;

import static global.General.log;


@Autonomous(name = "TerraAutoTest", group = "auto")
public class TerraAuto extends AutoFramework {
    {
//        fieldSide = FieldSide.RED; // TODO TEST
    }
    @Override
    public void initAuto() {
        setConfig(mecanumDefaultConfig);
        scan();
    }

    @Override
    public void define() {
        addWaypoint(0,20,0);
        addWaypoint(20,20,90);
        addConcurrentAutoModule(DuckNew);
        addWaypoint(20,0,90);
        addCancelAutoModules();
        addSetpoint(20,0,90);
        addSetpoint(0,0,0);
    }
}
