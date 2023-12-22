package auton.redauton;

import static global.General.bot;
import static global.General.log;

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
            intake.moveTime(.1,1.2)


    );
    AutoModule Drop = new AutoModule(
            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
            lift.stageLift(.8,27).attach(outtake.stageMiddler(.5)),
            outtake.stageEndAuto(.2),
            RobotPart.pause(.5),
            outtake.stageOpen(.2),
            RobotPart.pause(.5),
            lift.stageLift(.8,28)
    );
    AutoModule Drop2 = new AutoModule(
            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
            lift.stageLift(.8,24).attach(outtake.stageMiddler(.5)),
            outtake.stageEndAuto(.2),
            RobotPart.pause(.5),
            outtake.stageOpen(.2),
            RobotPart.pause(.5),
            lift.stageLift(.8,28)
    );
    AutoModule Drop3rd = new AutoModule(
            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
            lift.stageLift(.8,22).attach(outtake.stageMiddler(.5)),
            outtake.stageEndAuto(.2),
            RobotPart.pause(.5),
            outtake.stageOpen(.2),
            RobotPart.pause(.5),
            lift.stageLift(.8,25)
    );
    AutoModule Reset = new AutoModule(
            outtake.stageStart(1),
            lift.stageLift(.6,0)

    );
    @Override
    public void define() {
        customCase(() -> {
            addPause(10);
            addTimedSetpoint(1.0,1,1,0,30,0);

            addTimedSetpoint(1,.8,1,2,70,-90);
            addAutoModule(Extake);
            addTimedSetpoint(1,.8,.3,20,75,-90);

            addTimedSetpoint(1.0,1,1,10,115,-90);
            addTimedSetpoint(1.0,1,3,215,115,-90);
            addTimedSetpoint(1.0,1,1.5,215,60,-92);
            addTimedSetpoint(1.0,1,.5,235,60,-92);

            addAutoModule(Drop2);
            addAutoModule(Reset);





        }, () -> {
            addPause(10);
            addTimedSetpoint(1.0,1,1.5,-10,110,0);
            addAutoModule(Extake);
            addTimedSetpoint(1,.8,.5,0,120,0);
            addTimedSetpoint(1.0,1,1,20,115,-90);
            addTimedSetpoint(1.0,1,3,215,115,-90);
            addTimedSetpoint(1.0,1,1.5,215,43,-92);
            addTimedSetpoint(1.0,1,1.5,235,41,-92);
            addAutoModule(Drop2);
            addAutoModule(Reset);


        }, () -> {
            addPause(10);
            addTimedSetpoint(1.0,1,1,0,65,0);
            addTimedSetpoint(1,1,.5,38,69,95);
            addAutoModule(Extake);
            addTimedSetpoint(1,1,.5,0,65,95);
            addTimedSetpoint(1,.8,.5,0,120,0);
            addTimedSetpoint(1.0,1,1,20,115,-90);
            addTimedSetpoint(1.0,1,3,215,115,-90);
            addTimedSetpoint(1.0,1,1.5,235,31,-92);
            addAutoModule(Drop);
            addAutoModule(Reset);
        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
