import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import interfaces.SwarmOptimization;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Swarm implements SwarmOptimization {

    private ModelConstants modelConstants;
    private OptimizationProblem optimizationProblem;
    private List<List<Double>> initialPositions;
    private List<List<Double>> finalPositions;
    private List<Agent> agents;

    public Swarm() {
        this.agents = new ArrayList<>();
    }

    @Override
    public void setInitialPositions(List<List<Double>> positions) {
        this.initialPositions = positions;
    }

    @Override
    public List<List<Double>> getFinalPositions() {
        return finalPositions;
    }

    @Override
    public void optimize(long workingTime, TimeUnit unit) {
        AgentOperations agentOperations = new AgentOperations(optimizationProblem, modelConstants);
        initAgents();
        Instant shouldFinish = Instant.now().plus(workingTime, unit.toChronoUnit());
        while (Instant.now().isBefore(shouldFinish)) {
            agents.forEach(agentOperations::updateLight);
            UpdateUtils updateUtils = new UpdateUtils();
            for (int i = 0; i < agents.size(); i++) {
                List<Agent> closeNeighbors = agentOperations.getCloseNeighbors(agents.get(i), agents);
                if (!closeNeighbors.isEmpty()) {
                    Agent neighborToGo = agentOperations.choseNeighborToGo(agents.get(i), closeNeighbors);
                    List<Double> nextPos = agentOperations.getNextPos(agents.get(i), neighborToGo);
                    double nextRange = agentOperations.getNextRange(agents.get(i), closeNeighbors.size());
                    updateUtils.addNextPos(nextPos);
                    updateUtils.addNextRange(nextRange);
                } else {
                    List<Double> nextPos = agentOperations.getRandomNextPos(agents.get(i));
                    double functionValueNewPos = optimizationProblem.getValue(nextPos);
                    double functionValueOldPos = agents.get(i).getLastPositionValue();
                    if (functionValueNewPos > functionValueOldPos) {
                        updateUtils.addNextPos(nextPos);
                    } else {
                        updateUtils.addNextPos(agents.get(i).getPosition());
                    }
                    updateUtils.addNextRange(agents.get(i).getRange());
                }
            }
            agentOperations.updateAllRangesAndPositions(agents, updateUtils);
        }
        updateFinalPositions();
    }

    @Override
    public void setOptimizationProblem(OptimizationProblem problem) {
        this.optimizationProblem = problem;
    }

    @Override
    public void setModelCostants(ModelConstants constants) {
        this.modelConstants = constants;
    }

    private void updateFinalPositions() {
        this.finalPositions = agents.stream().map(Agent::getPosition).collect(Collectors.toList());
    }

    private void initAgents() {
        agents = new ArrayList<>();
        initialPositions.forEach(position -> agents.add(
                new Agent(position, modelConstants.getInitialSensorRange(), modelConstants.getInitialLuciferinValue())
        ));
    }
}
