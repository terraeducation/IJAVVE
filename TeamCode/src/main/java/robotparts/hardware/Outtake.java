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

        arml.changePosition("start", 0.15);
        armr.changePosition("start", 0.15);
        arml.changePosition("transfer", 0);
        armr.changePosition("transfer", 0);
        arml.changePosition("stop", 0.4);
        armr.changePosition("stop", 0.4);
        arml.changePosition("basket", 0.84);
        armr.changePosition("basket", 0.84);
        arml.changePosition("specimen", 0.77);
        armr.changePosition("specimen", 0.77);
        arml.changePosition("end", 1);
        armr.changePosition("end", 1);


        claw.changePosition("start", 0);
        claw.changePosition("open", 0.3);




        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); claw.setPosition("start");}
    public void moveTransfer(){armr.setPosition("transfer");arml.setPosition("transfer");}
    public void moveOpen(){claw.setPosition("open");}
    public void moveClose(){claw.setPosition("start");}
    public void moveBucket(){armr.setPosition("basket");arml.setPosition("basket");}
    public void moveSpecimen(){armr.setPosition("specimen");arml.setPosition("specimen");}
    public void moveStop(){armr.setPosition("stop");arml.setPosition("stop");}

    public void moveEnd(){armr.setPosition("end"); arml.setPosition("end");}

    public Stage stageEnd(double t){ return super.customTime(this::moveEnd, t);}
    public Stage stageStart(double t){return super.customTime(this::moveStart,t);}
    public Stage stageStop(double t){return super.customTime(this::moveStop, t);}
    public Stage stageBucket(double t){return super.customTime(this::moveBucket,t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen,t);}

    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer,t);}
    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose,t);}





}

