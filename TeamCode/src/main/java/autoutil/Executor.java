package autoutil;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import automodules.stage.Stage;
import autoutil.generators.AutoModuleGenerator;
import autoutil.generators.Generator;
import autoutil.generators.PauseGenerator;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.Reactor;
import util.codeseg.ParameterCodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.template.Iterator;

import static global.General.bot;

public class Executor implements Iterator {

    protected final Reactor reactor;
    protected final Generator generator;
    protected ReturnCodeSeg<Boolean> whileOpModeIsActive;
    protected boolean isIndependent = false;

    public Executor(LinearOpMode opMode, Generator generator, Reactor reactor){
        whileOpModeIsActive = opMode::opModeIsActive; this.generator = generator; this.reactor = reactor;
    }

    public final void followPath() {
        reactor.init();
        reactor.setTarget(generator.getTarget());
        Stage stage = generator.getStage(reactor);
        stage.start();
        whileActive(() -> !stage.shouldStop(), stage::loop);
        stage.runOnStop();
    }

    public void makeIndependent(){ isIndependent = true; }

    @Override
    public boolean condition() {
        if(isIndependent){
            return !bot.independentRunner.disabled;
        }else{
            return whileOpModeIsActive.run();
        }
    }
}