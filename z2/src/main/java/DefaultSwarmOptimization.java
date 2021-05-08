import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import interfaces.SwarmOptimization;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultSwarmOptimization implements SwarmOptimization {

    private ModelConstants modelConstants;
    private OptimizationProblem optimizationProblem;
    private List<List<Double>> initialPositions;
    private OptimizationResult optimizationResult;

    @Override
    public void setInitialPositions(List<List<Double>> positions) {
        this.initialPositions = positions;
    }

    @Override
    public List<List<Double>> getFinalPositions() {
        return optimizationResult.getFinalPositions();
    }

    @Override
    public void optimize(long workingTime, TimeUnit unit) {
        Optimizer optimizer = new DefaultOptimizer();
        this.optimizationResult = optimizer.optimize(this);
    }

    @Override
    public void setOptimizationProblem(OptimizationProblem problem) {
        this.optimizationProblem = problem;
    }

    @Override
    public void setModelCostants(ModelConstants constants) {
        this.modelConstants = constants;
    }
}
