package robotparts.sensors;

import static global.General.hardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;

import automodules.stage.Exit;
import elements.GameElement;
import robotparts.RobotPart;

public class ColorSensors extends RobotPart {
    public ColorSensor cso1, cso2;
    @Override
    public void init() {
        cso1 = hardwareMap.get(ColorSensor.class, "cso1");
        cso2 = hardwareMap.get(ColorSensor.class, "cso2");


    }



    public int getLight1(){
        return cso1.alpha();
    }
    public int getLight2(){
        return cso2.alpha();
    }

    public GameElement isObject(){
        if (cso1.alpha() > 2000 && cso2.alpha() > 2000){
            return GameElement.PIXEL1;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isObjectGone(){
        if (cso1.alpha() < 2000 && cso2.alpha() < 2000){
            return GameElement.PIXELGONE;
        }else{
            return GameElement.NONE;
        }

    }
    public boolean isPixel1(){
        return isObject().equals(GameElement.PIXEL1);
    }
    public boolean isPixelGone(){
        return isObjectGone().equals(GameElement.PIXELGONE);
    }

    public Exit exitIntake(){return new Exit(this::isPixel1);}
    public Exit exitExtake(){return new Exit(this::isPixelGone);}


}
