package robotparts.hardware;

import static java.lang.Math.abs;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import geometry.position.Vector;
import math.misc.Logistic;
import math.polynomial.Linear;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import util.Timer;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

import static global.General.bot;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.GROUND;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.driveMode;
import static global.Modes.heightMode;
import static global.Modes.robotStatus;
//import static global.Modes.driveMode;

public class Drive extends RobotPart {

    public CMotor fr, br, fl, bl;

    private final Precision precision = new Precision();
    private final Precision precision2 = new Precision();

    private final Timer timer = new Timer();

    public boolean noStrafeLock = false;

    public double[] currentPower = new double[3];
    public double[] deltaPower = new double[3];

    public boolean turnFast = false;

    public boolean using = false;





    @Override
    public void init() {
//        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
//        br = create("br", ElectronicType.CMOTOR_REVERSE);
//        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
//        bl = create("bl", ElectronicType.CMOTOR_FORWARD);


        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
        br = create("br", ElectronicType.CMOTOR_FORWARD);
        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
        bl = create("bl", ElectronicType.CMOTOR_FORWARD);



        noStrafeLock = false;

        driveMode.set(FAST);
        precision.reset();
        precision2.reset();

        currentPower = new double[3];
        deltaPower = new double[3];

        turnFast = false;
        using = false;

        timer.reset();

        //        throw new RuntimeException("HA HA YOU NOOB VIRUS VIRUS VIRUS");
    }



    @Override
    public void move(double f, double s, double t) {
        Vector power = new Vector(Precision.clip(s, 1), Precision.clip(f, 1));
        power.scaleX(1.2);
        power.limitLength(1);
        f = power.getY(); s = power.getX(); t = Precision.clip(t, 1);
        fr.setPower(f + s - t);
        br.setPower(f - s - t);
        fl.setPower(f - s + t);
        bl.setPower(f + s + t);
    }

    public void moveWithoutVS(double f, double s, double t) {
        Vector power = new Vector(Precision.clip(s, 1), Precision.clip(f, 1));
        power.scaleX(1.2);
        power.limitLength(1);
        f = power.getY(); s = power.getX(); t = Precision.clip(t, 1);
        fr.setPowerRaw(f - s - t);
        br.setPowerRaw(f + s - t);
        fl.setPowerRaw(f + s + t);
        bl.setPowerRaw(f - s + t);
    }

    public void help(double[] power, int i, double cutoff, double accel, double decel){
        if(Math.abs(power[i]) > cutoff){
//            deltaPower[i] += Math.abs(accel*power[i]);
//            currentPower[i] = Math.signum(power[i]) * (deltaPower[i] + cutoff);
            currentPower[i] = Math.signum(power[i])*cutoff;
        }else{
            currentPower[i] = power[i];
//            deltaPower[i] = Math.max(0, deltaPower[i] - decel);
        }
    }




    public void newMove(double f, double s, double t) {
        if (robotStatus.modeIs(DRIVING)) {


            fl.setPower(f + s + .7 * t);
            bl.setPower(f - s + .7 * t);
            fr.setPower(f - s - .7 * t);
            br.setPower(f + s - .7 * t);

        }else{
            fl.setPower(.4 * f + .3 * s + .25 * t);
            bl.setPower(.4 * f - .3 * s + .25 * t);
            fr.setPower(.4 * f - .3 * s - .25 * t);
            br.setPower(.4 * f + .3 * s - .25 * t);        }


        }


    @Override
    public Stage moveTime(double fp, double sp, double tp, double t) {
        return super.moveTime(fp, sp, tp, t);
    }

    @Override
    public Stage moveTime(double fp, double sp, double tp, ReturnCodeSeg<Double> t) {
        return super.moveTime(fp, sp, tp, t);
    }
    public Stage driveSmart(double fp, double sp, double tp){
        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
    }

    public Stage drivecloseSmart(double fp, double sp, double tp){
        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
    }
// Sample exit code
//    public Stage driveSmart2(double fp, double sp, double tp, Exit exit2){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
//    }
//    public Stage strafeSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitStrafe());
//    }
//
//    public Stage turnRightSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitRight());
//    }
//    public Stage turnLeftSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitLeft());
//    }
//
//    public Stage intakeSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, colorSensorsNew.exitIntake());
//    }

    @Override
    public AutoModule MoveTime(double fp, double sp, double tp, double t) {
        return super.MoveTime(fp, sp, tp, t);
    }
//TODO10 Restore Antitipping power


}
