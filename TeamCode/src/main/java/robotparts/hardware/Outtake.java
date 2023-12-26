package robotparts.hardware;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw1, claw2, pivot, rotate;
//    public PServo forklift;

//    public boolean cycleMachine = false;
//    public boolean pauseMachine = false;
//    public boolean skipMachine = false;

    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_FORWARD);
        arml = create("arml", ElectronicType.PSERVO_REVERSE);


        arml.changePosition("start", 0); //.21 difference
        armr.changePosition("start", 0);


        arml.addPosition("middle", 0.3);
        armr.addPosition("middle", 0.3);

        arml.addPosition("middler", 0.37);
        armr.addPosition("middler", 0.37);


        arml.changePosition("end", .6);
        armr.changePosition("end", 0.6);



        claw1 = create("claw1", ElectronicType.PSERVO_FORWARD);



        claw1.addPosition("close", 1);
        claw1.addPosition("open", 0.5);

        claw2 = create("claw2", ElectronicType.PSERVO_REVERSE);



        claw2.addPosition("close", .12);
        claw2.addPosition("open", .05);


        pivot = create("pivot", ElectronicType.PSERVO_REVERSE);



        pivot.addPosition("start", 0);
        pivot.addPosition("transfer", 0.4);


        rotate = create("rotate", ElectronicType.PSERVO_FORWARD);



        rotate.addPosition("start", .4);
        rotate.addPosition("transfer", .35);


        outtakeStatus.set(DRIVING);
    }

    public void changeArmPosition(String name, double pos){ armr.changePosition(name, pos); arml.changePosition(name, pos); }


    public void arm(double pos){ armr.setPosition(pos); arml.setPosition(pos); }

    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); rotate.setPosition("start"); pivot.setPosition("start");}
    public void moveStarttest(){ rotate.setPosition("transfer");   }

    public void moveEnd(){ armr.setPosition("end"); arml.setPosition("end"); }
    public void moveTransfer(){ pivot.setPosition("transfer");}
    public void moveTransferRotate(){ rotate.setPosition("transfer");}


    public void moveEndAuto(){ armr.setPosition("e"); arml.setPosition("e"); }

    public void moveEnderAuto(){ armr.setPosition("ee"); arml.setPosition("ee"); }


    public void openClaw(){ claw1.setPosition("open"); claw2.setPosition("open"); }

    public void closeClaw(){ claw1.setPosition("close"); claw2.setPosition("close"); }


    public void readyStart(){ armr.setPosition("startHalf"); arml.setPosition("startHalf"); }
    public void readyEnd(){ armr.setPosition("endHalf"); arml.setPosition("endHalf"); }

    public void moveMiddle(){ armr.setPosition("middle"); arml.setPosition("middle"); }

    public void moveMiddler(){ armr.setPosition("middler"); arml.setPosition("middler"); }


    public Stage stageReadyStart(double t){return super.customTime(this::readyStart, t);}
    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageEnd(double t){ return super.customTime(this::moveEnd, t); }

    public Stage stageTransfer(double t) {return super.customTime(this::moveTransfer, t);}
    public Stage stageTransferRotate(double t) {return super.customTime(this::moveTransferRotate, t);}

    public Stage stageEndAuto(double t){ return super.customTime(this::moveEndAuto, t); }
    public Stage stageEnderAuto(double t){ return super.customTime(this::moveEnderAuto, t); }


    public Stage stageOpen(double t){ return super.customTime(this::openClaw, t); }
    public Stage stageClose(double t){ return super.customTime(this::closeClaw, t); }

//
//    public Stage stageCloseAfter(double t){ return super.customTimeAfter(this::closeClaw, t);}
//
//    public Stage stageEndAfter(double t){ return super.customTimeAfter(this::moveEnd, t); }
//    public Stage stageOpenAfter(double t){ return super.customTimeAfter(this::openClawHalf, t); }

    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t);}
//    public Stage stageMiddler(double t){ return super.customTime(this::moveMiddler, t);}

    public Stage stageReadyEnd(double t){ return super.customTime(this::readyEnd, t); }
    public Stage stageReadyEndAfter(double t){ return super.customTimeAfter(this::readyEnd, t); }
    public Stage stageStartAfter(double t){ return super.customTimeAfter(this::moveStart, t); }
    public Stage stageReadyStartAfter(double t){ return super.customTimeAfter(this::readyStart, t); }

    public Stage stage(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }

    public Stage stageWithFlip(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }
    public Stage stageAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }
    public Stage stageWithFlipAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }

    public Stage stageEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }

    public Stage stageReadyEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }



    public Stage stageStartContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "e", t); }





    public Stage stageStartContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "start", t); }

    public Stage stageReadyEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }




    public Stage stageEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }



    public Stage stageMiddleWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("middle"); arml.setPosition("middle");}, t);}
    public Stage stageReadyEndWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("endHalf"); arml.setPosition("endHalf");}, t); }















}

