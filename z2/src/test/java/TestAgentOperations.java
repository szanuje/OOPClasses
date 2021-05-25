import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class TestAgentOperations {

    ModelConstants modelConstants = new ModelConstants() {
        @Override
        public double getInitialLuciferinValue() {
            return 1.0;
        }

        @Override
        public double getLuciferinEnhancementConstant() {
            return 0.6;
        }

        @Override
        public double getLuciferinDecayConstant() {
            return 0.4;
        }

        @Override
        public double getStepSize() {
            return 0.01;
        }

        @Override
        public double getMaximalSensorRange() {
            return 1.0;
        }

        @Override
        public double getBeta() {
            return 0.01;
        }

        @Override
        public double getInitialSensorRange() {
            return 0.2;
        }

        @Override
        public double getDesiredNumberOfNeighbors() {
            return 5;
        }
    };

    OptimizationProblem optimizationProblem = new OptimizationProblem() {
        @Override
        public int getNumberOfDimensions() {
            return 2;
        }

        @Override
        public List<Interval> getSearchArea() {
            return List.of(interval, interval);
        }

        @Override
        public double getValue(List<Double> position) {
            double x = position.get(0);
            double y = position.get(1);
            double p1 = x * x + y - 11.0;
            double p2 = x + y * y - 7.0;
            return -p1 * p1 - p2 * p2;
        }
    };

    OptimizationProblem.Interval interval = new OptimizationProblem.Interval() {
        @Override
        public double getMin() {
            return -5.0;
        }

        @Override
        public double getMax() {
            return 5.0;
        }
    };

    @Test
    void testGetCloseNeighbors() {
        Agent agent1 = new Agent(List.of(1., 1., 1.), 1.1, 5);
        Agent agent2 = new Agent(List.of(1., 1., 2.), 2, 11);
        Agent agent3 = new Agent(List.of(0., 0., 0.), 2, 11);
        Agent agent4 = new Agent(List.of(1., 2., 1.), 2, 109);
        Agent agent5 = new Agent(List.of(3., 3., 3.), 2, 110);
        List<Agent> neighbors = List.of(agent1, agent2, agent3, agent4, agent5);
        AgentOperations agentOperations = new AgentOperations(optimizationProblem, modelConstants);
        List<Agent> agents = agentOperations.getCloseNeighbors(agent1, neighbors);
        System.out.println(agents);
    }

    @Test
    void testChoseAgentToGo() {
        Agent agent1 = new Agent(List.of(1., 1., 1.), 10, 5);
        Agent agent2 = new Agent(List.of(1., 1., 1.), 10, 11);
        Agent agent3 = new Agent(List.of(1., 1., 1.), 10, 11);
        Agent agent4 = new Agent(List.of(1., 1., 1.), 10, 109);
        Agent agent5 = new Agent(List.of(1., 1., 1.), 10, 110);
        List<Agent> neighbors = List.of(agent2, agent3, agent4, agent5);
        AgentOperations agentOperations = new AgentOperations(optimizationProblem, modelConstants);
        Agent agent = agentOperations.choseNeighborToGo(agent1, neighbors);
        System.out.println(agent);
    }

    @Test
    void testGetNextPos() {
        Agent agent1 = new Agent(List.of(-1., -1.), 1.1, 5);
        Agent agent2 = new Agent(List.of(1., 1.), 2, 11);
        Agent agent3 = new Agent(List.of(0., 0.), 2, 11);
        AgentOperations agentOperations = new AgentOperations(optimizationProblem, modelConstants);
        List<Double> positions = agentOperations.getNextPos(agent1, agent2);
        System.out.println(positions);
        positions = agentOperations.getNextPos(agent1, agent3);
        System.out.println(positions);
        positions = agentOperations.getNextPos(agent3, agent2);
        System.out.println(positions);
    }

    @Test
    void testGetRandomNextPos() {
        Agent agent1 = new Agent(List.of(1., 1., 1.), 1.1, 5);
        AgentOperations agentOperations = new AgentOperations(optimizationProblem, modelConstants);
        List<Double> positions = agentOperations.getRandomNextPos(agent1);
        System.out.println(positions);
        positions = agentOperations.getRandomNextPos(agent1);
        System.out.println(positions);
        positions = agentOperations.getRandomNextPos(agent1);
        System.out.println(positions);

    }

    @Test
    void testSwarm() {
        Swarm swarm = new Swarm();
        swarm.setModelCostants(modelConstants);
        swarm.setOptimizationProblem(optimizationProblem);
        swarm.setInitialPositions(getPositions());
        swarm.optimize(10, TimeUnit.SECONDS);
        for (List<Double> pos : swarm.getFinalPositions()) {
            System.out.println(pos);
        }
    }

    private List<List<Double>> getPositions() {
        List<List<Double>> positions = new ArrayList<>();
        for (double i = -5.; i <= 5.; i += 0.5) {
            for (double j = -5.; j <= 5.; j += 0.5) {
                positions.add(List.of(i, j));
            }
        }
        return positions;
    }
}
