package MarketSimulation;

import java.util.ArrayList;

/**
 *  The Strategy interface for the strategy design pattern
 */
public class Simulation {
    enum StepTypes{ Day, Month, Year }

    private float percentage;
    private int steps;
    private float equitiesValue;
    ArrayList<Float> equityValuesAtStep;

    /**
     * Evaluates the new values for each step based on the value and percentage.
     * @return  equityValuesAtStep   the array of values at each step interval
     */
    public ArrayList<Float> evaluate() {
        for(int i = 0; i < this.steps; i++) {
            float value = (this.equitiesValue *= 1 + this.percentage); //TODO: multiply by stepType
            this.equityValuesAtStep.add(value);

            this.equitiesValue = value;
        }

        return this.equityValuesAtStep;
    }

    /**
     * Gets the equitiesValue
     * @return  equitiesValue   the array of values mapped to each step
     */
    public float getEquitiesValue() {
        return this.equitiesValue;
    }
}
