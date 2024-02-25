package robotparts.hardware;

import static global.General.log;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.INTAKING;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.outtakeStatus;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import elements.GameElement;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

public class Intake extends RobotPart {

    private IColor cso;

    private CMotor in;
    private CServo drone;
    private PServo link;
    private PServo link2;
    private CMotor hang;

    boolean Pixel;

    @Override
    public void init() {
        in = create("in", ElectronicType.CMOTOR_FORWARD_FLOAT);
        drone = create("drone", ElectronicType.CSERVO_FORWARD);
        link = create("link", ElectronicType.PSERVO_REVERSE);
        link2 = create("link2", ElectronicType.PSERVO_FORWARD);

//        hang = create("hang", ElectronicType.CMOTOR_FORWARD);

        link.changePosition("init", .33);
        link.changePosition("start", .63);
        link.changePosition("middle", .62);
        link.changePosition("middler", .53);

        link2.changePosition("init", .33);
        link2.changePosition("start", .62);
        link2.changePosition("middle", .62);
        link2.changePosition("middler", .57);

    }

    public void moveInit() {
        link.setPosition("init");
        link2.setPosition("init");


    }

    public void moveStart() {
        link.setPosition("start");
        link2.setPosition("start");


    }
    public void moveMiddler() {
        link.setPosition("middler");
        link2.setPosition("middler");


    }



    public void moveMiddle() {
        link.setPosition("middle");
        link2.setPosition("middle");


    }

    public void chubramani(){

        drone.setPower(-1);
//        log.show("mission failed");

    }
//    public void moveHang(double pow) {
//        hang.setPower(pow);
//    }

    @Override
    public CodeSeg move(double pow) {
        in.setPower(pow);
//        ins.setPower(pow);
        return null;
    }

    public Stage moveSmart(double p, Exit exit2){
            return super.customTwoExit(p, exit2, colorSensorsNew.exitIntake());





    }
    public Stage moveSmart2(double p){
        return super.customExit(p, colorSensorsNew.exitIntake());





    }


    public Stage moveOneSmart2(double p, Exit exit){
        return super.customExit(p, colorSensorsNew.exitIntake2());





    }

    public Stage moveOneSmart(double p){
        return super.customExit(p, colorSensorsNew.exitIntake2());





    }


//    public Stage stopSmart(){
//        return
//    }

    public Stage removeSmart(double p){

        return super.customExit(p, colorSensorsNew.exitExtake());

    }

    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageInit(double t){ return super.customTime(this::moveInit, t); }

    public Stage chubramani(double t){ return super.customTime(this::chubramani, t); }


    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t); }

    public Stage stageMiddler(double t){ return super.customTime(this::moveMiddler, t); }

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


