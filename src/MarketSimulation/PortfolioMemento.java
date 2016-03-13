package MarketSimulation;

/**
 * A memento pattern to save the state of a
 * simulation in order to return to it at any time.
 */
public class PortfolioMemento {
    private Simulation simulation;

    /**
     * Constructor
     * @param   simulation    the simulation to restore to
     */
    public PortfolioMemento(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Returns the value of the simulated market after the simulation was ran.
     * @return  value   the value after the simulation is ran
     */
    public Simulation getSimulation() {
        return simulation;
    }
}
