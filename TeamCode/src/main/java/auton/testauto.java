package auton;

import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;
import util.User;

@Autonomous(name = "terraautotest" + "", group = "auto", preselectTeleOp = "NithinOp")

public class testauto extends AutoFramework {
    @Override
    public void initialize() {


        this.setConfig(NonstopConfig);
    }


    @Override

    public void define() {
//        odometry.checkAccess(User.AUTO);
//        odometry.update();
        addTimedSetpoint(1,.1,2,10,0,0);
        addTimedSetpoint(1,.1,2,0,0,90);


        //        addTimedSetpoint(1,.1,5,-2,0,0);

    }




}
