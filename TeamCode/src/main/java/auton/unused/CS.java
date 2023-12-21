package auton.unused;

import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name = "CS", group = "Auton")
public class CS extends AutoFramework {
    @Override
    public void define() {
//        DistanceSensor dso = hardwareMap.get(DistanceSensor.class, "dso");
//        log.show("Distance in inches", dso.getDistance(DistanceUnit.INCH));
//        telemetry.update();
    }

    @Override
    public void initialize() {
        log.show("Distance in Inches", colorSensorsNew.getINDistance());
        telemetry.update();
        log.show("Distance in CM", colorSensorsNew.getCMDistance());
        telemetry.update();
    }
}
