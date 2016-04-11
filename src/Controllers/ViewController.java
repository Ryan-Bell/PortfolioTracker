package Controllers;

import Models.FileIO.PortfolioIO;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller that other controllers will extend
 * It sets reference to main and has common functionality
 * for switching tabs and the logout, refresh and save buttons
 */
public class ViewController implements Observer{

    //region FXMLFields
    @FXML Tab portfolioTab;
    @FXML Tab importTab;
    @FXML Tab marketTab;
    @FXML Tab simulationTab;
    @FXML Tab transactionTab;
    @FXML Tab watchlistTab;
    //endregion

    //reference to the main controller
    protected Main main;

    //controller for the logout feature
    private LogoutConfirmationController logoutBox;

    /** Sets the reference to main passed in by the extended fxml loader
     * @param main instance of main to set reference to
     */
    public void setMain(Main main){
        this.main = main;

        //call setup after main has been set
        setup();
    }

    /**
     * Will hold logic dependant of a reference to main
     */
    protected void setup(){}

    /** Called by the observable when it changes
     * @param o the object being observed
     * @param arg the identifying arg for what is being updated
     */
    @Override
    public void update(Observable o, Object arg) {}

    //region TabLogic
    /**
     * Action listener for selecting the portfolio tab
     */
    @FXML void handlePortfolioTab(){
        //tell main to set the active scene to portfolio
        if(main != null)
            main.showPortfolio();
    }

    /**
     * Action listener for selecting the market tab
     */
    @FXML void handleMarketTab() {
        //tell main to set the active scene to market
        if(main != null)
            main.showMarket();
    }

    /**
     * Action listener for selecting the import tab
     */
    @FXML void handleImportTab() {
        //tell main to set the active scene to import
        if(main != null)
            main.showImport();
    }

    /**
     * Action listener for selecting the simulation tab
     */
    @FXML void handleSimulationTab() {
        //tell main to set the active scene to simulation
        if(main != null)
            main.showSimulation();
    }

    /**
     * Action listener for selecting the transaction tab
     */
    @FXML void handleTransactionTab() {
        //tell main to set the active scene to transaction
        if(main != null)
            main.showTransaction();
    }

    /**
     * Action listener for selecting the watchlist tab
     */
    @FXML void handleWatchlistTab() {
        //tell main to set the active scene to watchlist
        if(main != null)
            main.showWatchlist();
    }
    //endregion

    /**
     * Action listener to handle the refresh button being pressed
     * restarts the update service
     */
    @FXML void handleRefresh() {
        main.getUpdateService().restart();
    }

    /**
     * Action listener to handle the save button being pressed
     */
    @FXML void handleSave() {
        //attempt to serialize the current portfolio
        try {
            PortfolioIO.serialize(main.getPortfolio(), "./portfolios/" + main.getPortfolio().getId() + ".port");
        } catch (IOException e){
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Action listener to handle the logout button being pressed
     */
    @FXML void handleLogout() {
        //ask the user if they want to save
        logoutBox = new LogoutConfirmationController();

        //attempt show the logout box view
        try {
            UndoRedoFunctions.createNewUndoRedoFunctions();
            logoutBox.showMessageBox(main.getPrimaryStage(), main);
        } catch (Exception e) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
