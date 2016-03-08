package MarketSimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete strategy for Simulation to use.
 * Simulates market growth.
 */
public class BullSimulation extends Simulation {
    private float percentage;
    private int steps;
    private float equitiesValue;
    List<Float> equityValuesAtStep;

    /**
     * Constructor.
     * @param percentage    the percentage of growth
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public BullSimulation(float percentage, int steps, float equitiesValue) {
        this.percentage = percentage;
        this.steps = steps;
        this.equitiesValue = equitiesValue;

        this.equityValuesAtStep = new ArrayList<>();
    }
}
