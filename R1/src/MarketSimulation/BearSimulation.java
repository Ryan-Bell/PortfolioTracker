package MarketSimulation;

/**
 * An overall decrease in the value of a portfolio.
 * Used to provide calculations for a MarketSimulator.
 */
public class BearSimulation extends Simulation {

    /**
     * Constructor.
     * @param percentage    the percentage of decline
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public BearSimulation(float percentage, int steps, float equitiesValue, StepTypes stepType) {
        super(-percentage, steps, equitiesValue, stepType);
    }
}
