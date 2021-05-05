import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import interfaces.SwarmOptimization;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultSwarmOptimization implements SwarmOptimization {

    @Override
    public void setInitialPositions(List<List<Double>> positions) {

    }

    @Override
    public List<List<Double>> getFinalPositions() {
        return null;
    }

    @Override
    public void optimize(long workingTime, TimeUnit unit) {

    }

    @Override
    public void setOptimizationProblem(OptimizationProblem problem) {

    }

    @Override
    public void setModelCostants(ModelConstants constants) {

    }
}
