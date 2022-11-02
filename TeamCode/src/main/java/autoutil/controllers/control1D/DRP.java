package autoutil.controllers.control1D;

import autoutil.paths.PathSegment;
import geometry.position.Pose;
import util.template.Precision;

public class DRP extends Controller1D{
    // Dynamic Rest Power

    private final double kp;
    private double restPower;
    private final Precision precision = new Precision();

    public DRP(double kp, double rp){ this.kp = kp; this.restPower = rp; }

    @Override
    protected double setDefaultAccuracy() { return 0.5; }

    @Override
    protected double setDefaultMinimumTimeReachedTarget() { return 0.3; }

    @Override
    protected double setDefaultRestOutput() { return 0.0; }

    @Override
    protected void updateController(Pose pose, PathSegment pathSegment) {
        precision.throttle(() -> restPower *= isWithinAccuracyRange() ? 0.98 : 1.01, 100);
        setRestOutput(restPower);
    }

    @Override
    protected double setOutput() { return kp*getError(); }

    @Override
    protected boolean hasReachedTarget() { return true; }
}