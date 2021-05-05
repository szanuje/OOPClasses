import interfaces.OptimizationProblem;

import java.util.List;

public class DefaultOptimizationProblem implements OptimizationProblem {

    @Override
    public int getNumberOfDimensions() {
        return 0;
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
