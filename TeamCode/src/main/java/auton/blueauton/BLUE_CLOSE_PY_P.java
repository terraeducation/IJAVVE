package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "BLUECLOSE PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class BLUE_CLOSE_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

    }
    AutoModule ExtakeandLift = new AutoModule(

            intake.stageStart(.2),
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
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))
    );
    AutoModule align = new AutoModule(
            drive.driveSmart(-.18,0,0)
    );

    AutoModule alignCloser = new AutoModule(
            drive.drivecloseSmart(-.18,0,0)
    );
    @Override
    public void define() {
        customCase(() -> {
            addWaypoint(30,-30,0);

            addTimedSetpoint(1.0,.5,1,40,-75,-90);


            addAutoModule(ExtakeandLift);



            addTimedSetpoint(1.0,.5,1,60,-55,-90);

            addTimedSetpoint(1.0,.5,.4,70,-55,-95);
            addAutoModule(alignCloser);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(60,-20,0);
            addTimedSetpoint(1.0,.5,1,100,-10,-90);






        }, () -> {
            addWaypoint(20,-80,0);
            addSegment(.3,DefaultWP, 47,-85,-91);
            addTimedSetpoint(1.0,.5,1,30,-110,-91);




            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,50,-67.5,-90);

            addTimedSetpoint(1.0,.5,.2,60,-67.5,-90    );
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(60,-9,0);
            addTimedSetpoint(1.0,.5,1.3,100,-10,-90);




        }, () -> {
            addWaypoint(0,-30,0);
            addWaypoint(0,-50,-20);

            addTimedSetpoint(1.0,.5,1,-11,-75,-90);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,40,-74,-90);

            addTimedSetpoint(1.0,.5,.7,60,-80,-90);
            addAutoModule(alignCloser);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(60,-15,0);
            addTimedSetpoint(1.0,.5,1.5,100,-10,-90);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
