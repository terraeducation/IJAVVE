package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.RobotPart;
import static global.General.hardwareMap;

public class DistanceSensors extends RobotPart {
    public DistanceSensor dsol, dsor;

    @Override
    public void init() {
            dsol = hardwareMap.get(DistanceSensor.class, "dsol");
            dsor = hardwareMap.get(DistanceSensor.class, "dsor");

    }



    public double getCMDistance() {
        return dsol.getDistance(DistanceUnit.CM);
    }

    public double getMETERDistance() {
        return dsol.getDistance(DistanceUnit.METER);
    }

    public boolean isClose(){
        return dsor.getDistance(DistanceUnit.CM) < 2;
    }


}
