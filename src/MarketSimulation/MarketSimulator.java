package MarketSimulation;

import java.util.ArrayList;

/**
 * Manages all the simulations for the market.
 */
public class MarketSimulator {
    private ArrayList<PortfolioMemento> mementos;
    private float basePortfolioValue;

    /**
     * Creates a simulation then creates a memento.
     * @return  memento the memento
     */
    public PortfolioMemento createMemento(float percent, int steps, Simulation.StepTypes stepType, String type){
        Simulation simulation;
        switch (type) {
            case "bear":
                simulation = new BearSimulation(percent, steps, this.basePortfolioValue);
                break;

            case "bull":
                simulation = new BullSimulation(percent, steps, this.basePortfolioValue);
                break;

            case "nogrowth":
                simulation = new NoGrowthSimulation(percent, steps, this.basePortfolioValue);
                break;

            default:
                simulation = new NoGrowthSimulation(percent, steps, this.basePortfolioValue);
                break;
        }

        return new PortfolioMemento(simulation);
    }

    /**
     * Pops the last memento off the stack and runs set the
     * basePortfolioValue to its value. Used to undo a simulation.
     */
    public void popMemento(){
        int lastIndex = mementos.size() - 1;
        PortfolioMemento memory = this.mementos.remove(lastIndex);
        this.basePortfolioValue = memory.getSimulation().getEquitiesValue();
    }

    /**
     * Runs the simulation. It first creates a PortfolioMemento then calls the
     * memento's evaluate method and adds it to mementos.
     * @param percent   the percentage to run the simulation at. In decimal form
     * @param steps     the number of steps to take while running the simulation
     * @param stepType  the type of steps to make. Day, Month, or Year
     * @param type      the type of symulation. Bear, Bull, or No Growth
     * @return  valuesAtSteps   the array of values mapped to each step
     */
    public ArrayList<Float> runSimulation(float percent, int steps, Simulation.StepTypes stepType, String type){
        PortfolioMemento memento = createMemento(percent, steps, stepType, type);

        ArrayList<Float> valuesAtSteps = memento.getSimulation().evaluate();
        this.mementos.add(memento);

        return valuesAtSteps;
    }

}
