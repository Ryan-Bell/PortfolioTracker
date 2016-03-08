package MarketSimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete strategy for Simulation to use.
 * Simulates market decline.
 */
public class BearSimulation extends Simulation {
    private float percentage;
    private int steps;
    private float equitiesValue;
    List<Float> equityValuesAtStep;

    /**
     * Constructor.
     * @param percentage    the percentage of decline
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public BearSimulation(float percentage, int steps, float equitiesValue) {
        this.percentage = -percentage;
        this.steps = steps;
        this.equitiesValue = equitiesValue;

        this.equityValuesAtStep = new ArrayList<>();
    }
}
