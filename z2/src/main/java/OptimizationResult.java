import java.util.List;

public class OptimizationResult {

    private final List<List<Double>> finalPositions;

    public OptimizationResult(List<List<Double>> finalPositions) {
        this.finalPositions = finalPositions;
    }

    public List<List<Double>> getFinalPositions() {
        return finalPositions;
    }
}
