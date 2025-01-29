package robotparts.hardware;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.robotStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw;


    @Override
    public void init() {
        armr = create("oarmr", ElectronicType.PSERVO_FORWARD);
        arml = create("oarml", ElectronicType.PSERVO_REVERSE);
//        pivot = create("pivot", ElectronicType.PSERVO_FORWARD);
        claw = create("oclaw", ElectronicType.PSERVO_FORWARD);

        arml.changePosition("start", 0);
        armr.changePosition("start", 0);


        claw.changePosition("start", 0);



        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); claw.setPosition("start");}











}

