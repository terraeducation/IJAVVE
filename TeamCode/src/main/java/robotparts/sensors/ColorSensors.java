package robotparts.sensors;

import static global.General.hardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;

import automodules.stage.Exit;
import elements.GameElement;
import robotparts.RobotPart;

public class ColorSensors extends RobotPart {
    public ColorSensor cso;
    @Override
    public void init() {
        cso = hardwareMap.get(ColorSensor.class, "cso");

    }



    public int getLight(){
        return cso.alpha();
    }
    public GameElement isObject(){
        if (cso.alpha() > 2000){
            return GameElement.PIXEL;
        }else{
            return GameElement.NONE;
        }

    }
    public boolean isPixel(){
        return isObject().equals(GameElement.PIXEL);
    }
    public Exit exitIntake(){return new Exit(this::isPixel);}

}
