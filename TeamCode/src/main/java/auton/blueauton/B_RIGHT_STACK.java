package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.Case;
import robotparts.RobotPart;


@Autonomous(name = "B. Right RStack", group = "auto", preselectTeleOp = "TerraOp")
public class B_RIGHT_STACK extends AutoFramework {


    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.openClawFull();
        caseDetected = Case.FIRST;

    }
    AutoModule PullStack = new AutoModule(
      intake.moveTime(-1,.8)


    );
    AutoModule Extake = new AutoModule(
            outtake.stageOpenHalf(.1),
            intake.moveTime(.5,.25),
            outtake.stageClose(.1)


    );
    AutoModule DropStackStart = new AutoModule(
            outtake.stageClose(.1),
            intake.stageMiddle(.2)



    );
    AutoModule Drop = new AutoModule(
            outtake.stageMiddle(.2).attach(outtake.stageClose(.2)),
            lift.stageLift(1,30),
            outtake.stageEnd(.2),
            RobotPart.pause(.1),
            outtake.stageOpen(.2),
            RobotPart.pause(.1)
    );
    AutoModule Reset = new AutoModule(
            outtake.stageStart(1),
            lift.stageLift(.8,0)

    );
    @Override
    public void define() {
        customCase(() -> {

            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 55, -90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,0,125,90);
            addTimedSetpoint(1.0,1,1,40,130,90);
            addAutoModule(DropStackStart);
            addAutoModule(PullStack);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,2,-230,35,90);
            addAutoModule(Drop);
            RobotPart.pause(.5);
            addAutoModule(Reset);
            addTimedSetpoint(1.0,1,.2,-200,35,90);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,2,35,130,90);
            addAutoModule(PullStack);
            addTimedSetpoint(1.0,1,3,-200,125,90);
            addTimedSetpoint(1.0,1,2,-230,35,90);
            addAutoModule(Drop);
            RobotPart.pause(.5);
            addAutoModule(Reset);

//            addTimedSetpoint(1.0,1,3,-200,125,90);
//            addTimedSetpoint(1.0,1,1,-250,10,90);
//            addAutoModule(Drop);
//            RobotPart.pause(1);
//            addAutoModule(Reset);




        }, () -> {
            addTimedSetpoint(1.0,1,1,0,130,0);
            addAutoModule(Extake);
//            addTimedSetpoint(1.0,1,1,0,125,-90);
//            addTimedSetpoint(1.0,1,3,-200,125,90);
//            addTimedSetpoint(1.0,1,1,-250,10,90);
//            addAutoModule(Drop);
//            RobotPart.pause(1);
//            addAutoModule(Reset);

        }, () -> {
            addTimedSetpoint(1.0, 1, .2, 0, 15, 0);
            addTimedSetpoint(1.0, 1, 1, 0, 55, 90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,0,125,90);
//            addTimedSetpoint(1.0,1,3,-200,125,90);
//            addTimedSetpoint(1.0,1,1,-250,10,90);
//            addAutoModule(Drop);
//            RobotPart.pause(1);
//            addAutoModule(Reset);
        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }

}
