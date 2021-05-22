import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agent {
    private List<Double> position;
    private double lastPositionValue;
    private double range;
    private double light;

    private Agent() {
        //
    }

    public Agent(List<Double> position, double range, double light) {
        this.position = position;
        this.range = range;
        this.light = light;
    }

    public Double getLastPositionValue() {
        return lastPositionValue;
    }

    public void setLastPositionValue(Double lastPositionValue) {
        this.lastPositionValue = lastPositionValue;
    }

    public List<Double> getPosition() {
        return new ArrayList<>(position);
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Double.compare(agent.range, range) == 0 &&
                Double.compare(agent.light, light) == 0 &&
                Objects.equals(position, agent.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, range, light);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "position=" + position +
                ", range=" + range +
                ", light=" + light +
                '}';
    }
}