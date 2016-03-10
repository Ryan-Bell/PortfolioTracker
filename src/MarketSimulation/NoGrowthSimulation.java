package MarketSimulation;

/**
 * A concrete strategy for Simulation to use.
 * Simulates no growth in the market.
 */
public class NoGrowthSimulation extends Simulation {

    /**
     * Constructor.
     * @param percentage    will always be 0
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public NoGrowthSimulation(float percentage, int steps, float equitiesValue, StepTypes stepType) {
        super(0, steps, equitiesValue, stepType);
    }
}
