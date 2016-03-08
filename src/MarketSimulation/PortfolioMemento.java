package MarketSimulation;

/**
 * A memento pattern to save states in a
 * simulation in order to return to previously
 * ran simulation.
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
     *  returns the value of the simulated market after the simulation was ran.
     * @return  value   the value after the simulation is ran
     */
    public Simulation getSimulation() {
        return simulation;
    }
}
