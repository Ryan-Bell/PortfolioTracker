package MarketSimulation;

/**
 * Created by ms8565 on 3/8/2016.
 */


public class Simulation {
    private float percentage;
    private int steps;
    private float equitiesValue;

    private Step stepType;
    private float[] equityValuesAtStep;

    public enum Step{
        Day, Month, Year
    }
    public float[] evaluate(){
        return equityValuesAtStep;
    }
}
