package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.RobotPart;
import static global.General.hardwareMap;

public class DistanceSensors extends RobotPart {
    public DistanceSensor dso;

    @Override
    public void init() {
            dso = hardwareMap.get(DistanceSensor.class, "dso");
    }



    public double getCMDistance() {
        return dso.getDistance(DistanceUnit.CM);
    }

    public double getMETERDistance() {
        return dso.getDistance(DistanceUnit.METER);
    }

    public boolean isClose(){
        return dso.getDistance(DistanceUnit.CM) < 2;
    }


}
