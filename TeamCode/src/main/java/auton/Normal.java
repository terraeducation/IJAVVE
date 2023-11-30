package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "Normal" + "", group = "auto", preselectTeleOp = "NithinOp")

public class Normal extends AutoFramework {

    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();

    }


    public void define() {
        addTimedSetpoint(1,1,1,0,70,0);
//        addTimedSetpoint(1,1,1,0,80,90);
        addTimedSetpoint(1,1,1,-70,80,90);
        addTimedSetpoint(1,1,1,-70,120,90);
        //        addTimedSetpoint(1,1,4,-40,105,90);
//        addTimedSetpoint(1,1,2,10,105,90);




    }


    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }

    //    @Autonomous(name = "C. RIGHT SAFE", group = "auto", preselectTeleOp = "TerraOp")
//    public static class RIGHT extends TerraAutoSafe {{ fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.LOWER; startPose = new Pose(20.5, Field.width/2.0 - Field.tileWidth - GameItems.Cone.height - 16,90); }}
//
//    @Autonomous(name = "D. LEFT SAFE", group = "auto", preselectTeleOp = "TerraOp")
//    public static class LEFT extends TerraAutoSafe {{ fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.UPPER; startPose = new Pose(20.5, Field.width/2.0 + Field.tileWidth + GameItems.Cone.height + 16,90); }}


    }



