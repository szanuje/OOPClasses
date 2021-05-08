import interfaces.ModelConstants;

public class DefaultModelConstants implements ModelConstants {

    private final double initialLuciferinValue;
    private final double luciferinEnchancementConstant;
    private final double luciferinDecayConstant;
    private final double stepSize;
    private final double maximalSensorRange;
    private final double beta;
    private final double initialSensorRange;
    private final double desiredNumberOfNeighbors;

    private DefaultModelConstants(
            double initialLuciferinValue,
            double luciferinEnchancementConstant,
            double luciferinDecayConstant,
            double stepSize,
            double maximalSensorRange,
            double beta,
            double initialSensorRange,
            double desiredNumberOfNeighbors
    ) {
        this.initialLuciferinValue = initialLuciferinValue;
        this.luciferinEnchancementConstant = luciferinEnchancementConstant;
        this.luciferinDecayConstant = luciferinDecayConstant;
        this.stepSize = stepSize;
        this.maximalSensorRange = maximalSensorRange;
        this.beta = beta;
        this.initialSensorRange = initialSensorRange;
        this.desiredNumberOfNeighbors = desiredNumberOfNeighbors;
    }

    @Override
    public double getInitialLuciferinValue() {
        return initialLuciferinValue;
    }

    @Override
    public double getLuciferinEnhancementConstant() {
        return luciferinEnchancementConstant;
    }

    @Override
    public double getLuciferinDecayConstant() {
        return luciferinDecayConstant;
    }

    @Override
    public double getStepSize() {
        return stepSize;
    }

    @Override
    public double getMaximalSensorRange() {
        return maximalSensorRange;
    }

    @Override
    public double getBeta() {
        return beta;
    }

    @Override
    public double getInitialSensorRange() {
        return initialSensorRange;
    }

    @Override
    public double getDesiredNumberOfNeighbors() {
        return desiredNumberOfNeighbors;
    }

    public static ModelConstantsBuilder builder() {
        return new ModelConstantsBuilder();
    }

    public static class ModelConstantsBuilder {

        private double initialLuciferinValue;
        private double luciferinEnchancementConstant;
        private double luciferinDecayConstant;
        private double stepSize;
        private double maximalSensorRange;
        private double beta;
        private double initialSensorRange;
        private double desiredNumberOfNeighbors;

        ModelConstantsBuilder() {
            //
        }

        public ModelConstantsBuilder withInitialLuciferinValue(double initialLuciferinValue) {
            this.initialLuciferinValue = initialLuciferinValue;
            return this;
        }

        public ModelConstantsBuilder withLuciferinEnchancementConstant(double luciferinEnchancementConstant) {
            this.luciferinEnchancementConstant = luciferinEnchancementConstant;
            return this;
        }

        public ModelConstantsBuilder withLuciferinDecayConstant(double luciferinDecayConstant) {
            this.luciferinDecayConstant = luciferinDecayConstant;
            return this;
        }


        public ModelConstantsBuilder withStepSize(double stepSize) {
            this.stepSize = stepSize;
            return this;
        }

        public ModelConstantsBuilder withMaximalSensorRange(double maximalSensorRange) {
            this.maximalSensorRange = maximalSensorRange;
            return this;
        }

        public ModelConstantsBuilder withBeta(double beta) {
            this.beta = beta;
            return this;
        }

        public ModelConstantsBuilder withInitialSensorRange(double initialSensorRange) {
            this.initialSensorRange = initialSensorRange;
            return this;
        }

        public ModelConstantsBuilder withDesiredNumberOfNeighbors(double desiredNumberOfNeighbors) {
            this.desiredNumberOfNeighbors = desiredNumberOfNeighbors;
            return this;
        }

        public ModelConstants build() {
            return new DefaultModelConstants(
                    this.initialLuciferinValue,
                    this.luciferinEnchancementConstant,
                    this.luciferinDecayConstant,
                    this.stepSize,
                    this.maximalSensorRange,
                    this.beta,
                    this.initialSensorRange,
                    this.desiredNumberOfNeighbors
            );
        }
    }
}
