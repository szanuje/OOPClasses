import interfaces.ModelConstants;
import interfaces.OptimizationProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AgentOperations {

    private final OptimizationProblem optimizationProblem;
    private final ModelConstants modelConstants;

    public AgentOperations(OptimizationProblem optimizationProblem, ModelConstants modelConstants) {
        this.optimizationProblem = optimizationProblem;
        this.modelConstants = modelConstants;
    }

    public List<Agent> getCloseNeighbors(Agent agent, List<Agent> agents) {
        List<Agent> closeNeighbors = new ArrayList<>();
        for (Agent neighbor : agents) {
            List<Double> eachDimensionDistance = getVectorBetweenAgents(agent.getPosition(), neighbor.getPosition());
            double distanceOfNeighbor = getVectorLength(eachDimensionDistance);
            double lightOfNeighbor = neighbor.getLight();
            if (distanceOfNeighbor < agent.getRange() && lightOfNeighbor > agent.getLight()) {
                closeNeighbors.add(neighbor);
            }
        }
        return closeNeighbors;
    }

    public Agent choseNeighborToGo(Agent agent, List<Agent> neighbors) {
        double randomUniform = new Random().nextDouble();
        List<Double> substractedLuciferins = neighbors.stream()
                .map(neighbor -> (neighbor.getLight() - agent.getLight()))
                .collect(Collectors.toList());
        Double sumOfLights = substractedLuciferins.stream()
                .reduce(0.0, Double::sum);
        List<Double> normalizedLuciferins = substractedLuciferins.stream()
                .map(luciferin -> luciferin / sumOfLights)
                .collect(Collectors.toList());
        double helperVal = 0.0;
        for (int i = 0; i < normalizedLuciferins.size(); i++) {
            helperVal += normalizedLuciferins.get(i);
            if (helperVal > randomUniform) {
                return neighbors.get(i);
            }
        }
        return null;
    }

    public List<Double> getNextPos(Agent myAgent, Agent neighborAgent) {
        List<Double> suggestedNextPos = calculateNextPos(myAgent.getPosition(), neighborAgent.getPosition());
        for (int i = 0; i < suggestedNextPos.size(); i++) {
            OptimizationProblem.Interval bounds = optimizationProblem.getSearchArea().get(i);
            double calibratedPos = getCalibratedPosition(suggestedNextPos.get(i), bounds);
            suggestedNextPos.set(i, calibratedPos);
        }
        return suggestedNextPos;
    }

    public List<Double> getRandomNextPos(Agent agent) {
        List<Double> positions = agent.getPosition();
        int dimensionIdxToModify = new Random().nextInt(agent.getPosition().size());
        Integer direction = new Random().nextDouble() - 0.5 > 0 ?
                1 : -1;
        positions.set(dimensionIdxToModify, positions.get(dimensionIdxToModify) + direction);
        OptimizationProblem.Interval bounds = optimizationProblem.getSearchArea().get(dimensionIdxToModify);
        double calibratedPos = getCalibratedPosition(positions.get(dimensionIdxToModify), bounds);
        positions.set(dimensionIdxToModify, calibratedPos);
        return positions;
    }

    public Double getNextRange(Agent agent, int numOfNeighbors) {
        return agent.getRange() +
                modelConstants.getBeta() * (modelConstants.getDesiredNumberOfNeighbors() - numOfNeighbors);
    }

    public void updateAllRangesAndPositions(List<Agent> agents, UpdateUtils updateUtils) {
        if (agents.size() != updateUtils.getUpdatedRanges().size() ||
                agents.size() != updateUtils.getUpdatedPositions().size()) {
            throw new RuntimeException("Agents collection size is different than update utils collections sizes");
        }
        for (int i = 0; i < agents.size(); i++) {
            updatePosition(agents.get(i), updateUtils.getUpdatedPositions().get(i));
            updateRange(agents.get(i), updateUtils.getUpdatedRanges().get(i));
        }
    }

    public void updateLight(Agent agent) {
        double functionValue = optimizationProblem.getValue(agent.getPosition());
        double lightValue = (1 - modelConstants.getLuciferinDecayConstant()) * agent.getLight() +
                modelConstants.getLuciferinEnhancementConstant() * functionValue;
        agent.setLight(lightValue);
        updateLastFunctionValue(agent, functionValue);
    }

    private void updateRange(Agent agent, double range) {
        agent.setRange(Math.min(
                modelConstants.getMaximalSensorRange(),
                Math.max(0., range)));
    }

    private double getCalibratedPosition(double positon, OptimizationProblem.Interval bounds) {
        if (positon < bounds.getMin()) {
            positon = bounds.getMin();
        }
        if (positon > bounds.getMax()) {
            positon = bounds.getMax();
        }
        return positon;
    }

    private void updatePosition(Agent agent, List<Double> positions) {
        agent.setPosition(positions);
    }

    private void updateLastFunctionValue(Agent agent, double value) {
        agent.setLastPositionValue(value);
    }

    private List<Double> calculateNextPos(List<Double> myPos, List<Double> neighborPos) {
        List<Double> nextPos = new ArrayList<>(myPos);
        List<Double> vector = getVectorBetweenAgents(myPos, neighborPos);
        double vectorLength = getVectorLength(vector);
        double actualStep = modelConstants.getStepSize() / vectorLength;
        for (int i = 0; i < vector.size(); i++) {
            nextPos.set(i, nextPos.get(i) + actualStep * vector.get(i));
        }
        return nextPos;
    }

    private List<Double> getVectorBetweenAgents(List<Double> agent1, List<Double> agent2) {
        List<Double> eachDimensionDistance = new ArrayList<>();
        for (int k = 0; k < agent1.size(); k++) {
            eachDimensionDistance.add(agent2.get(k) - agent1.get(k));
        }
        return eachDimensionDistance;
    }

    private double getVectorLength(List<Double> vector) {
        double result = 0.0;
        for (Double val : vector) {
            result += Math.pow(val, 2);
        }
        return Math.sqrt(result);
    }
}
