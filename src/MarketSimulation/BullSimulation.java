package MarketSimulation;

/**
 * A concrete strategy for Simulation to use.
 * Simulates market growth.
 */
public class BullSimulation extends Simulation {

    /**
     * Constructor.
     * @param percentage    the percentage of growth
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public BullSimulation(float percentage, int steps, float equitiesValue, StepTypes stepType) {
        super(percentage, steps, equitiesValue, stepType);
    }
}
