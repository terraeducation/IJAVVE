package auton.unused;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static global.General.bot;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import autoutil.AutoFramework;
import elements.TeamProp;

@Autonomous(name = "DS", group = "Auton")
public class DS extends AutoFramework {
    @Override
    public void define() {
//        DistanceSensor dso = hardwareMap.get(DistanceSensor.class, "dso");
//        log.show("Distance in inches", dso.getDistance(DistanceUnit.INCH));
//        telemetry.update();
    }

    @Override
    public void initialize() {
//        this.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
//        outtake.moveStart();
//        outtake.openClawHalf();
//        propCaseDetected = TeamProp.FIRST;
//        AutoFramework auto = this;
//        auto.scan(true, "blue", "right");
        log.show("Distance in Inches", distanceSensorsNew.getINDistance());
        telemetry.update();
    }
}
