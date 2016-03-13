package MarketSimulation;

/**
 * Stagnation in the market. Simulates no growth.
 * Used to provide calculations for a MarketSimulator
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
