package MarketSimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete strategy for Simulation to use.
 * Simulates no growth in the market.
 */
public class NoGrowthSimulation extends Simulation {
    private final float percentage = 0;
    private int steps;
    private float equitiesValue;
    List<Float> equityValuesAtStep;

    /**
     * Constructor.
     * @param percentage    will always be 0
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     */
    public NoGrowthSimulation(float percentage, int steps, float equitiesValue) {
        this.steps = steps;
        this.equitiesValue = equitiesValue;

        this.equityValuesAtStep = new ArrayList<>();
    }
}
