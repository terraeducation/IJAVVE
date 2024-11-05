package auton.redauton;


import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name = "RED_P3_YP_RIGHT",group = "auto",preselectTeleOp = "TerraOp")

public class RED_P3_YP_RIGHT extends AutoFramework {

    @Override
    public void define(){

        addTimedSetpoint(1,1,1,20,-55,45);
        addPause(1);
        addSegment(1,DefaultSP,0.57,-20,-40); //first sample
        addPause(1);
        addSegment(1,DefaultSP,-5,-10,25);
        addPause(1);
        addSegment(1,DefaultSP,-15,-20,-50); // second sample
        addPause(1);
        addSegment(1,DefaultSP,-20,-20,25);
        addPause(1);
        addSegment(1,DefaultSP,-30,-20,-50); // third sample
        addPause(1);
        addSegment(1,DefaultSP,-35,-30,25);



    }

    @Override
    public void initialize(){
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }





}
