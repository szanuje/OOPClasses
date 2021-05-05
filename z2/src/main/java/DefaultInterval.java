import interfaces.OptimizationProblem;

public class DefaultInterval implements OptimizationProblem.Interval {

    private final double min;
    private final double max;

    public DefaultInterval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double getMin() {
        return min;
    }

    @Override
    public double getMax() {
        return max;
    }
}
