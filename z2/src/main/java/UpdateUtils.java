import java.util.ArrayList;
import java.util.List;

public class UpdateUtils {
    
    private final List<List<Double>> updatedPositions;
    private final List<Double> updatedRanges;

    public UpdateUtils() {
        this.updatedPositions = new ArrayList<>();
        this.updatedRanges = new ArrayList<>();
    }

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
