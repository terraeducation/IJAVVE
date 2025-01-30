package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

AutoModule Grab = new AutoModule(
        intake.stageOpen(.1),
        intake.stageEnd(.1),
  RobotPart.pause(.3),
  intake.stageLinkEnd(.1)
).setStartCode(
        robotStatus.setTo(PLACING)
);

AutoModule Intake = new AutoModule(
  intake.stageOpen(.1),
        intake.stageEnd2(.1),
        RobotPart.pause(.3),
  intake.stageClose(.3),
  intake.stageLinkStart(.1).attach(intake.stageEnd(.1)),
        RobotPart.pause(.6),
        intake.stageStartArm(.1)

).setStartCode(
        robotStatus.setTo(DRIVING)
);;

AutoModule PlaceHigh = new AutoModule(
  outtake.stageOpen(.1),
  outtake.stageTransfer(.1),
  outtake.stageClose(.1),
  intake.stageOpen(.1),
  outtake.stageBucket(.1).attach(lift.stageLift(1,54))
).setStartCode(
        robotStatus.setTo(PLACING)
);
    AutoModule Place = new AutoModule(
            outtake.stageOpen(.1),
            RobotPart.pause(.2),
            outtake.stageStart(.1).attach(lift.stageLift(.7,0))

    ).setStartCode(
            robotStatus.setTo(DRIVING)
    );

    AutoModule SpecimenGrab = new AutoModule(
      outtake.stageOpen(.1),
      outtake.stageEnd(.1)
    );

    AutoModule SpecimenLift = new AutoModule(
            outtake.stageClose(.1),
            lift.stageLift(1,5)
    );

    AutoModule SpecimenUp = new AutoModule(
            lift.stageLift(1,30).attach(outtake.stageBucket(.1))
    ).setStartCode(
            robotStatus.setTo(PLACING)
    );
    AutoModule SpecimenDown = new AutoModule(
            outtake.stageEnd(.1),
            lift.stageLift(1,15),
            outtake.stageStart(.1).attach(lift.stageLift(.3,0))
    ).setStartCode(
            robotStatus.setTo(DRIVING)
    );








}


