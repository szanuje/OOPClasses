import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestAgentOperations {

    ModelConstants modelConstants = new ModelConstants() {
        @Override
        public double getInitialLuciferinValue() {
            return 0;
        }

        @Override
        public double getLuciferinEnhancementConstant() {
            return 0;
        }

        @Override
        public double getLuciferinDecayConstant() {
            return 0;
        }

        @Override
        public double getStepSize() {
            return 0;
        }

        @Override
        public double getMaximalSensorRange() {
            return 0;
        }

        @Override
        public double getBeta() {
            return 0;
        }

        @Override
        public double getInitialSensorRange() {
            return 0;
        }

        @Override
        public double getDesiredNumberOfNeighbors() {
            return 0;
        }
    };

    OptimizationProblem optimizationProblem = new OptimizationProblem() {
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
    };

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
        System.out.println(agent.getLight());
    }
}
