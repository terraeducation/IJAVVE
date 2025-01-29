package robotparts.hardware;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

public class Intake extends RobotPart {



    private PServo linkager, linkagel, iarmr, iarml, iclaw;



    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_REVERSE);
        iarml = create("iarml", ElectronicType.PSERVO_FORWARD);
//        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkr", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkl", ElectronicType.PSERVO_FORWARD);

        iarml.changePosition("start", 0.58);
        iarmr.changePosition("start", 0.7);

        iarml.changePosition("end", 0.35);
        iarmr.changePosition("end", 0.37);

        iarml.changePosition("end2", 0.1);
        iarmr.changePosition("end2", 0.12);
//
//        ipivot.changePosition("start", 0);
//
        iclaw.changePosition("start", 0);
        iclaw.changePosition("end", 0.2);

//
        linkager.changePosition("start", 0.0);
        linkagel.changePosition("start", 0.0);
        linkager.changePosition("end", 0.29);
        linkagel.changePosition("end", 0.29);







    }

    public void moveStart(){ iclaw.setPosition("start"); iarml.setPosition("start");iarmr.setPosition("start");linkager.setPosition("start"); linkagel.setPosition("start");}

    public void moveStartArm(){iarml.setPosition("start");iarmr.setPosition("start");}

    public void moveEnd2(){iarml.setPosition("end2"); iarmr.setPosition("end2");}

    public void moveEnd(){iarml.setPosition("end");iarmr.setPosition("end");iclaw.setPosition("start");}
    public void moveClose(){iclaw.setPosition("end");}
    public void moveOpen(){iclaw.setPosition("start");}
    public void moveLinkEnd(){linkagel.setPosition("end");linkager.setPosition("end");}
    public void moveLinkStart(){linkagel.setPosition("start");linkager.setPosition("start");}

    public Stage stageStart(double t){return super.customTime(this::moveStart, t);}
    public Stage stageEnd(double t){return super.customTime(this::moveEnd, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose, t);}
    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}
    public Stage stageLinkEnd(double t){return super.customTime(this::moveLinkEnd, t);}
    public Stage stageLinkStart(double t){return super.customTime(this::moveLinkStart, t);}
    public Stage stageEnd2(double t){return super.customTime(this::moveEnd2,t);}
    public Stage stageStartArm(double t){return super.customTime(this::moveStartArm,t);}

    @Override
    public Stage moveTime(double p, ReturnCodeSeg<Double> t) { return super.moveTime(p, t); }

    @Override
    public Stage moveFull(double p){
        return super.moveFull(p);
    }

    @Override
    public AutoModule MoveTime(double p, double t) {
        return super.MoveTime(p, t);
    }
}


