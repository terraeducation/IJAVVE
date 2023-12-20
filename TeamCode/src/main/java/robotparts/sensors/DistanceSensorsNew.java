package robotparts.sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.RobotPart;
import static global.General.hardwareMap;

public class DistanceSensorsNew extends RobotPart {
    public DistanceSensor dso;

    @Override
    public void init() {
        dso = hardwareMap.get(DistanceSensor.class, "dso");
    }

    public void start() {
//        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) dso;
    }

    public double getMMDistance() {
        return dso.getDistance(DistanceUnit.MM);
    }

    public double getCMDistance() {
        return dso.getDistance(DistanceUnit.CM);
    }

    public double getINDistance() {
        return dso.getDistance(DistanceUnit.INCH);
    }

    public double getMETERDistance() {
        return dso.getDistance(DistanceUnit.METER);
    }


}
