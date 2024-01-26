package auton.blueauton;

import static global.General.bot;
import static global.Modes.Height.GROUND;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "BLUE Right PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_RIGHT_PY_P extends AutoFramework {


    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();

//        propCaseDetected = TeamProp.FIRST;
        AutoFramework auto = this;
        auto.scan(true, "blue", "right");

    }
    AutoModule Extake = new AutoModule(
            intake.stageMiddler(.1),
            intake.moveTime(.5,.2),
            intake.stageInit(.1)


    );

    AutoModule Drop = new AutoModule(
            outtake.stageEnd(.5).attach(outtake.stageTransferPivot(.5)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 16.5).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))

    );

    AutoModule align = new AutoModule(
            drive.driveSmart(-.3,0,0)
    );
    //TODO READD PAUSES
    AutoModule Reset = new AutoModule(

            outtake.stageOpen(.1).attach(intake.stageInit(.2)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            lift.stageLift(1, 5).attach(outtake.stageThruPivot(.1)),
            outtake.stageStartPivot(.1).attach(lift.stageLift(1,0))

    );
    @Override
    public void define() {
        customCase(() -> {
            addPause(18);

            addWaypoint(0,-20,0);

            addTimedSetpoint(1.0,.5,1,35,-60,30);
            addTimedSetpoint(1.0,.5,1,30,-60,35 );

            addAutoModule(Extake);
            addWaypoint(-10,-50,35);
            addTimedSetpoint(1.0,1,1,0,-130,-90);
            addWaypoint(155, -130,-90);
            addAutoModule(PreExtend);
            addWaypoint(180, -130,-90);
            addWaypoint(200,-90,-90);
            addAutoModule(align);


          addAutoModule(Reset);












        }, () -> {
            addPause(21);
            addTimedSetpoint(1.0,.5,1,-20,-70,35);
            addTimedSetpoint(1.0,.5,1,-10,-75,35);


            addAutoModule(Extake);
            addWaypoint(0, -65,0);

            addWaypoint(0, -65,-90);
            addWaypoint(150, -68,-93);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,.8,180, -82,-91);


            addTimedSetpoint(1,1,.6,210, -84,-91);
            addAutoModule(align);

            addAutoModule(Reset);




        }, () -> {
            addPause(17);
            addTimedSetpoint(1.0,.5,1,-13,-43,0);

            addAutoModule(Extake);
            addWaypoint(0,-15,0);
            addWaypoint(0,-120,0);
            addTimedSetpoint(1.0,1,.8,0,-130,-90);
            addWaypoint(155, -130,-90);
            addAutoModule(PreExtend);
            addWaypoint(180, -130,-90);
            addWaypoint(200,-95.5,-90);
            addAutoModule(align);

            addAutoModule(Reset);




        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
