import java.util.List;

public class UpdateUtils {
    
    private List<List<Double>> updatedPositions;
    private List<Double> updatedRanges;

    public void addNextPos(List<Double> nextPos) {
        updatedPositions.add(nextPos);
    }

    public void addNextRange(double nextRange) {
        updatedRanges.add(nextRange);
    }

    public List<List<Double>> getUpdatedPositions() {
        return updatedPositions;
    }

    public List<Double> getUpdatedRanges() {
        return updatedRanges;
    }
}
