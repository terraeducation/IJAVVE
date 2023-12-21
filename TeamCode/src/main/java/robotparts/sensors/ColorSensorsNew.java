package robotparts.sensors;

import static global.General.hardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.input.IColor;

public class ColorSensorsNew extends RobotPart {
    private IColor cso;
    @Override
    public void init() {
        cso = create("cso", ElectronicType.ICOLOR);
    }
    public void start() {
//        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) dso;
    }

    public double getMMDistance() {
        return cso.getDistance();
    }

    public double getCMDistance() {
        return cso.getDistance(DistanceUnit.CM);
    }

    public double getINDistance() {
        return cso.getDistance(DistanceUnit.INCH);
    }

    public double getMETERDistance() {
        return cso.getDistance(DistanceUnit.METER);
    }
}