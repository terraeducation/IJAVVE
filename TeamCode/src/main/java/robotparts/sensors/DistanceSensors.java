package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import automodules.stage.Exit;
import robotparts.RobotPart;

import static global.General.hardwareMap;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.outtakeStatus;

public class DistanceSensors extends RobotPart {
    public DistanceSensor dsol, dsor;

    @Override
    public void init() {
            dsol = hardwareMap.get(DistanceSensor.class, "dsol");
            dsor = hardwareMap.get(DistanceSensor.class, "dsor");


    }



    public double getCMDistanceLeft() {
        return dsol.getDistance(DistanceUnit.CM);
    }
    public double getCMDistanceRight() {
        return dsor.getDistance(DistanceUnit.CM);
    }



    public double getMETERDistance() {
        return dsol.getDistance(DistanceUnit.METER);
    }

    public boolean isClose(){

        return (dsor.getDistance(DistanceUnit.CM) < 25.3) && (dsor.getDistance(DistanceUnit.CM) < 25.3);
    }


    public Exit exitDrive(){return new Exit(this::isClose);}

//    public Stage moveDist(double t){ return super.customTime(this::moveDist, t);}
    public boolean IsRightClose(){
        if(outtakeStatus.modeIs(PLACING)) {
            return dsor.getDistance(DistanceUnit.CM) < 50;
        }
        return false;
    }
    public boolean IsLeftClose(){
        if(outtakeStatus.modeIs(PLACING)) {
            return dsol.getDistance(DistanceUnit.CM) < 30;
        }
        return false;
    }

    public boolean IsRightSuperClose(){
        return dsor.getDistance(DistanceUnit.CM) == 20;
    }
    public boolean IsLeftSuperClose(){
        return dsol.getDistance(DistanceUnit.CM) == 20;
    }


}
