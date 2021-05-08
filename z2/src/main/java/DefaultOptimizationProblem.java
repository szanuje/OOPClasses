import interfaces.OptimizationProblem;

import java.util.List;

public class DefaultOptimizationProblem implements OptimizationProblem {

    private final int numberOfDimensions;

    public DefaultOptimizationProblem(int numberOfDimensions) {
        this.numberOfDimensions = numberOfDimensions;
    }

    @Override
    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    @Override
    public List<Interval> getSearchArea() {
        return null;
    }

    @Override
    public double getValue(List<Double> position) {
        return 0;
    }
}
