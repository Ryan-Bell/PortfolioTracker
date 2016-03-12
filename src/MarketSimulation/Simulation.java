package MarketSimulation;

import java.util.ArrayList;

/**
 *  The Strategy class for the strategy design pattern
 */
public class Simulation {
    public enum StepTypes{ DAY, MONTH, YEAR }

    protected float percentage;
    protected int steps;
    protected float equitiesValue;
    protected int compoundDay;
    protected ArrayList<Float> equityValuesAtStep;

    /**
     * Constructor.
     * @param percentage    the percentage of growth or decline
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     * @param stepType      the type of step to run through (day, month, year)
     */
    public Simulation(float percentage, int steps, float equitiesValue, StepTypes stepType) {
        this.percentage = percentage;
        this.steps = steps;
        this.equitiesValue = equitiesValue;

        switch (stepType) {
            case DAY:
                this.compoundDay = 365;
                break;

            case MONTH:
                this.compoundDay = 12;
                break;

            default:
                this.compoundDay = 1;
                break;
        }

        this.equityValuesAtStep = new ArrayList<>();
    }

    /**
     * Evaluates the new values for each step based on the value and percentage.
     * Compounded annually.
     * @return  equityValuesAtStep   the array of values at each step interval
     */
    public ArrayList<Float> evaluate() {

        float interest = 0;
        for(int i = 1; i <= this.steps; i++) {

            if (i % this.compoundDay == 0) {
                this.equitiesValue += interest;
                interest = 0;
            }

            interest += (this.equitiesValue * this.percentage);
            this.equityValuesAtStep.add(this.equitiesValue + interest);
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
