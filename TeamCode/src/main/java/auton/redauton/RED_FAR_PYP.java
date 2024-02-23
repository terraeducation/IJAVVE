package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name = "REDFAR PYP", group = "auto", preselectTeleOp = "TerraOp")
public class RED_FAR_PYP extends AutoFramework {



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
        auto.scan(true, "red", "left");

    }
    AutoModule Extake = new AutoModule(
            intake.stageStart(.2),
            intake.stageInit(.2)
    );

    AutoModule align = new AutoModule(
            drive.driveSmart(-.2,0,0)
    );
    AutoModule Drop = new AutoModule(
            outtake.stageEnd(.5).attach(outtake.stageTransferPivot(.5)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 21).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageflipStackRotate(.2))


    );
    AutoModule Reset = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );
    //TODO MAKE DISTANCE SENSOR ADJUST Y COORDINATE
    @Override
    public void define() {
        customCase(() -> {
            addPause(18);
            addTimedSetpoint(1.0,.5,1,-3,-45,0);

            addTimedSetpoint(1.0,.5,1,-23,-70,120);
            addPause(.5);
            addAutoModule(Extake);
//            addWaypoint(-5,-20,0);
            addTimedSetpoint(1.0,1,.8,-5,-130,90);
            addWaypoint(-155, -130,90);
            addAutoModule(PreExtend);
            addWaypoint(-180, -130,90);
            addWaypoint(-180,-95.5,90);
            addTimedSetpoint(1.0,1,1.5,-210,-88,91);

            addAutoModule(align);

            addAutoModule(Reset);












        }, () -> {
            addPause(21);
            addWaypoint(30,-30,0);

            addTimedSetpoint(1.0,.5,1,27,-98,-95);


            addAutoModule(Extake);
            addWaypoint(30, -65,-90);

            addWaypoint(0, -65,90);
            addWaypoint(-150, -68,93);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,.8,-180, -73,91);

            addAutoModule(align);

            addAutoModule(Reset);




        }, () -> {
            addPause(18);

            addWaypoint(0,-30,0);
            addWaypoint(0,-35,-20);

            addTimedSetpoint(1.0,.5,1,-12,-72,-95);

            addAutoModule(Extake);
            addWaypoint(10,-50,-35);
            addTimedSetpoint(1.0,1,1,0,-130,90);
            addWaypoint(-155, -130,90);
            addAutoModule(PreExtend);
            addWaypoint(-180, -130,90);
            addWaypoint(-180,-90,90);
            addTimedSetpoint(1.0,2,.8,-219,-58,92);
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
