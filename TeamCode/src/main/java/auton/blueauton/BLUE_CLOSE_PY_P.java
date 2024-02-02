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
        outtake.moveStartPivot();
        outtake.closeClaw();

        intake.moveInit();
//        propCaseDetected = TeamProp.FIRST; //TODO FIX
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

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
            lift.stageLift(1, 12).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );
     AutoModule align = new AutoModule(
       drive.driveSmart(-.3,0,0)
     );

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

            addWaypoint(30,-30,0);

            addTimedSetpoint(1.0,.5,1,41,-58,-95);


            addAutoModule(ExtakeandLift);

            addTimedSetpoint(1.0,.5,1,70,-45,-90);
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);
            addWaypoint(60,-10,0);
            addTimedSetpoint(1.0,.3,2,90,-10,-90);








        }, () -> {

            addWaypoint(20,-80,0);
            addTimedSetpoint(1.0,.5,1,28,-91,-95);

            addAutoModule(ExtakeandLift);

            addTimedSetpoint(1.0,.5,1,70,-60,-90);
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);

            addWaypoint(60,-14,0);
            addTimedSetpoint(1.0,.3,2,90,-12,-90);




        }, () -> {
            addWaypoint(0,-30,0);
            addWaypoint(0,-35,-20);

            addTimedSetpoint(1.0,.5,1,-15,-55,-95);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,40,-74,-94);
            addPause(.1);
            addTimedSetpoint(1.0,.5,.7,70,-80,-93);


            addAutoModule(align);
            addPause(.1);

            addAutoModule(Reset);
            addWaypoint(60,-15,0);
            addTimedSetpoint(1.0,.3,2,90,-13,-90);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
