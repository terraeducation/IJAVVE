package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.Case;
import robotparts.RobotPart;

@Autonomous(name = "B. Right PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_RIGHT_PY_P extends AutoFramework {


    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.openClawHalf();

    }
    AutoModule Extake = new AutoModule(
            intake.moveTime(.5,.25)


    );
    AutoModule Drop = new AutoModule(
            outtake.stageMiddle(.5).attach(outtake.stageClose(.5)),
            lift.stageLift(.7,35),
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
        customCase(() -> {

            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 35, 90);
            addTimedSetpoint(1.0,1,1,-42,65,90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,-50,65,90);
            addTimedSetpoint(1.0,1,2,-50,125,90);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,1,-210,55,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);




        }, () -> {
            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 35, 90);
            addTimedSetpoint(1.0,1,1,-35,95,90);
            addAutoModule(Extake);


        }, () -> {
            addTimedSetpoint(1.0, 1, .2, 0, 15, 0);
            addTimedSetpoint(1.0, 1, 1, 16, 60, 90);
            addAutoModule(Extake);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
