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
    protected StepTypes stepType;
    protected ArrayList<Float> equityValuesAtStep;

    /**
     * Constructor.
     * @param percentage    the percentage of growth or decline
     * @param steps         the number of steps in this simulation
     * @param equitiesValue the value to evaluate at
     * @param stepType      the type of step to run through (day, month, year)
     */
    public Simulation(float percentage, int steps, float equitiesValue, StepTypes stepType) {
        this.percentage = Math.max(percentage, -1);
        this.steps = steps;
        this.equitiesValue = equitiesValue;
        this.stepType = stepType;

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

        //divide percent by compound day to get rate in terms of step type
        float percentPerStep = percentage / compoundDay;

        //variable will keep track of equity value after annual compound
        float updatedEquityValue = equitiesValue;

        //calculate the years to run (Ceiling)
        int numberOfYears = (int)Math.ceil(steps / compoundDay);

        //keeps track of the current step within the current year
        int currentStep = 0;

        //loop through the years
        for(int currentYear = 0; currentYear <= numberOfYears; currentYear++){
            //set the current step within the current year back to one
            currentStep = 1;

            //loop while we are still in the current year and while we haven't gone past the total runtime
            //the last check is necessarry since the total number of years was calced with ceiling
            while(currentStep <= compoundDay && currentStep + currentYear * compoundDay <= steps){
                //add the currently compounded equity value  plus the inter-year growth break down to the array
                this.equityValuesAtStep.add(updatedEquityValue * currentStep * percentPerStep + updatedEquityValue);
                currentStep++;
            }
            //keeps track of the equity value at each year (since it is annual compounding)
            updatedEquityValue = (1 + percentage) * updatedEquityValue;
        }

        //set the equitiesValue to the evaluated value before it returns.
        //used to revert back to this state
//        int lastIndex = equityValuesAtStep.size() - 1;
//        this.equitiesValue = equityValuesAtStep.get(lastIndex);

        //return the full array
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
