package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.Case;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "R. Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class R_LEFT_PY_P extends AutoFramework {


    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.openClawHalf();
        propCaseDetected = TeamProp.FIRST;
        AutoFramework auto = this;
        auto.scan(true, "red", "left");
    }
    AutoModule Extake = new AutoModule(
            intake.moveTime(.5,.25)


    );
    AutoModule Drop = new AutoModule(
            outtake.stageMiddle(.5).attach(outtake.stageClose(.5)),
            lift.stageLift(.7,30),
            outtake.stageEnd(1),
            RobotPart.pause(.5),
            outtake.stageOpen(.2),
            RobotPart.pause(.5)
    );
    AutoModule Reset = new AutoModule(
            outtake.stageStart(1),
            lift.stageLift(.6,0)

    );
    @Override
    public void define() {
        propCaseDetected = TeamProp.FIRST;
        customCase(() -> {

            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 55, -90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,0,125,-90);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,1,-250,10,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);




        }, () -> {
            addTimedSetpoint(1.0,1,1,0,130,0);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,0,125,-90);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,1,-250,10,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);

        }, () -> {
            addTimedSetpoint(1.0, 1, .2, 0, 15, 0);
            addTimedSetpoint(1.0, 1, 1, 0, 55, 90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,0,125,90);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,1,-250,10,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);
        });
    }
//    @Override
//    public void postProcess() {
////        autoPlane.reflectY();
////        autoPlane.reflectX();
//    }


}

