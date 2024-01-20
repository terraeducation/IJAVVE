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
        in = create("in", ElectronicType.CMOTOR_FORWARD);
        drone = create("drone", ElectronicType.CSERVO_FORWARD);
        link = create("link", ElectronicType.PSERVO_REVERSE);
        link2 = create("link2", ElectronicType.PSERVO_FORWARD);

//        hang = create("hang", ElectronicType.CMOTOR_FORWARD);

        link.changePosition("init", .15);
        link.changePosition("start", .72);
        link.changePosition("middle", .56);
        link.changePosition("middler", .30);

        link2.changePosition("init", .15);
        link2.changePosition("start", .72);
        link2.changePosition("middle", .52);
        link2.changePosition("middler", .30);

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

        drone.setPower(1);
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

    public Stage moveSmart(double p){
            return super.customExit(p, colorSensorsNew.exitIntake());





    }

//    public Stage stopSmart(){
//        return
//    }

    public Stage removeSmart(double p){

        return super.customExit(p, colorSensorsNew.exitExtake());

    }

    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageInit(double t){ return super.customTime(this::moveInit, t); }

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


