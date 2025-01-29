package automodules;

import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

AutoModule Grab = new AutoModule(
        intake.stageOpen(.1),
  intake.stageLinkEnd(.1).attach(intake.stageEnd(.1))
);

AutoModule Intake = new AutoModule(
  intake.stageEnd2(.1),
        RobotPart.pause(.3),
  intake.stageClose(.1),
  intake.stageLinkStart(.1).attach(intake.stageStartArm(.1))
);







}


