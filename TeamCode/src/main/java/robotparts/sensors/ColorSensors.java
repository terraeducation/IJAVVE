package robotparts.sensors;

import static global.General.hardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;

import automodules.stage.Exit;
import automodules.stage.Stage;
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

//    double cso1Val = cso1.alpha();
//    double cso2Val = cso2.alpha();

//    public double endIntake1(){
//        return cso1Val = 501;
//
//    }
//
//    public double endIntake2(){
//        return cso2Val = 501;
//
//    }

    public GameElement isObject(){
        if (cso1.alpha() > 500 && cso2.alpha() > 500){
            return GameElement.PIXEL1;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isoneObject(){
        if (cso1.alpha() > 500 || cso2.alpha() > 500){
            return GameElement.PIXEL1;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isObjectGone(){
        if (cso1.alpha() < 500 && cso2.alpha() < 500){
            return GameElement.PIXELGONE;
        }else{
            return GameElement.NONE;
        }

    }


    public boolean isPixel1(){
        return isObject().equals(GameElement.PIXEL1);
    }
    public boolean isPixel(){
        return isoneObject().equals(GameElement.PIXEL1);
    }

    public boolean isPixelGone(){
        return isObjectGone().equals(GameElement.PIXELGONE);
    }

    public Exit exitIntake(){return new Exit(this::isPixel1);}
    public Exit exitIntake2(){return new Exit(this::isPixel);}

    public Exit exitExtake(){return new Exit(this::isPixelGone);}


}
