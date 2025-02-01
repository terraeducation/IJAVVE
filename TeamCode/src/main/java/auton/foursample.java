package auton;

import static global.General.bot;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.PLACING;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name="FourSample", group="auto")
public class foursample extends AutoFramework {

    @Override
    public void initialize() {
        voltageScale =1;
        this.setConfig(NonstopConfig);
        lift.maintain();
        odometry.reset();
        gyro.reset();
        bot.saveLocationOnField();
        outtake.moveStart();
        intake.moveStart();
    }
    AutoModule PlaceFirst = new AutoModule(
            outtake.stageClose(.1),
            intake.stageOpen(.1),
            outtake.stageBucket(.1).attach(lift.stageLift(1,60))
    );
    AutoModule Drop = new AutoModule(
            outtake.stageOpen(.1)

    );
    AutoModule PlaceHigh = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransfer(.1),
            RobotPart.pause(.5),
            outtake.stageClose(.1),
            intake.stageOpen(.1),
            outtake.stageBucket(.1).attach(lift.stageLift(1,60))
    );
    AutoModule Place = new AutoModule(
            intake.stageOpen(.1),
            intake.stageEnd(.1),
            RobotPart.pause(.3),
            intake.stageLinkEnd(.1),
            outtake.stageOpen(.1),
            RobotPart.pause(.2),
            outtake.stageStart(.1).attach(lift.stageLift(.7,0)),
            outtake.stageStop(.1)

    );
    AutoModule Place1 = new AutoModule(
            outtake.stageOpen(.1),
            RobotPart.pause(.2),
            outtake.stageStart(.1).attach(lift.stageLift(.7,0)),
            outtake.stageStop(.1)

    );

    AutoModule Intake = new AutoModule(
            intake.stageOpen(.1),
            intake.stageEnd2(.1),
            RobotPart.pause(.3),
            intake.stageClose(.3),
            intake.stageLinkStart(.1).attach(intake.stageLeave(.1)),
            RobotPart.pause(.6),
            intake.stageStartArm(.1)

    );
    @Override
    public void define() {
        addSegment(1,1,DefaultWP, 0,7,0);
        addAutoModule(PlaceFirst);

        addSegment(1,1,DefaultWP, 21,4  ,0);
        addAutoModule(Drop);
        addPause(1);

        addAutoModule(Place);
        addSegment(1,1,DefaultSP, 5.3,4.5,0);
        addAutoModule(Intake);
        addPause(1);
        addAutoModule(PlaceHigh);
        addSegment(1,1,DefaultWP, 21,4,0);
        addAutoModule(Drop);
        addPause(1);
        addAutoModule(Place);

        addSegment(1,1,DefaultSP, 10.5,4.5,0);
        addAutoModule(Intake);
        addPause(1);
        addAutoModule(PlaceHigh);
        addSegment(1,1,DefaultWP, 21,4,0);
        addAutoModule(Drop);
        addPause(1);
        addAutoModule(Place1);









    }


}
