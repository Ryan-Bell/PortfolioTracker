package Models.MarketSimulation;

/**
 * An overall increase in the value of a portfolio.
 * Used to provide calculations for a MarketSimulator.
 */
public class BullSimulation extends Simulation {

    /**
     * Constructor.
     * @param percentage    the percentage of growth
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public BullSimulation(float percentage, int steps, float equitiesValue, StepType stepType) {
        super(percentage, steps, equitiesValue, stepType);
    }
}
