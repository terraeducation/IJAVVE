package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name = "REDCLOSE PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class RED_CLOSE_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();
//        propCaseDetected = TeamProp.THIRD;
        AutoFramework auto = this;
        auto.scan(true, "red", "right");

    }
    AutoModule ExtakeandLift = new AutoModule(

            intake.stageStart(.2),
            RobotPart.pause(.1),
            intake.stageInit(.2),
            lift.stageLift(1, 13).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))

    );

    AutoModule Drop = new AutoModule(
            lift.stageLift(1, 7),
            lift.stageLift(1, 15).attach(outtake.stageThruPivot(.1)),
            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule Reset = new AutoModule(

            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),
            outtake.stageStartRotate(.05),

            outtake.stageThruPivot(.1).attach(lift.stageLift(1,0)),
            outtake.stageDownPivot(.1).attach(outtake.stageLock(.1))

    );
    AutoModule align = new AutoModule(
            drive.driveSmart(-.3,0,0)
    );
    @Override
    public void define() {
        customCase(() -> {
            addWaypoint(0,-30,0);
            addWaypoint(0,-50,20);

            addTimedSetpoint(1.0,.5,1,5,-70,90);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,-40,-74,91);

            addTimedSetpoint(1.0,.5,.7,-70,-85,91);
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(-60,-15,0);
            addTimedSetpoint(1.0,.5,1.5,-80,-10,90);



        }, () -> {

            addWaypoint(-20,-80,0);
            addTimedSetpoint(1.0,.5,1,-40,-102,91);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,-60,-74,90);

            addTimedSetpoint(1.0,.5,1,-70,-74,90    );
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(-60,-9,0);
            addTimedSetpoint(1.0,.5,1.3,-90,-10,90);


        }, () -> {
            addWaypoint(-30,-30,0);

            addTimedSetpoint(1.0,.5,1,-47,-60,90);


            addAutoModule(ExtakeandLift);



            addTimedSetpoint(1.0,.5,2,-60,-60.5,90);

            addTimedSetpoint(1.0,.5,.8,-70,-51,95);
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(-60,-20,0);
            addTimedSetpoint(1.0,.5,1,-90,-10,90);
        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
