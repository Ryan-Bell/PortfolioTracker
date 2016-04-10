package Controllers;

import Models.FileIO.PortfolioIO;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewController implements Observer{

    @FXML Tab portfolioTab;
    @FXML Tab importTab;
    @FXML Tab marketTab;
    @FXML Tab simulationTab;
    @FXML Tab transactionTab;
    @FXML Tab watchlistTab;

    //reference to the main controller
    protected Main main;
    private LogoutConfirmationController logoutBox;

    public void setMain(Main main){
        this.main = main;
        setup();
    }

    protected void setup(){}

    @Override
    public void update(Observable o, Object arg) {}

    @FXML void handlePortfolioTab(Event event){
        if(main != null)
            main.showPortfolio();
    }

    @FXML void handleMarketTab(Event event) {
        if(main != null)
            main.showMarket();
    }

    @FXML void handleImportTab(Event event) {
        if(main != null)
            main.showImport();
    }

    @FXML void handleSimulationTab(Event event) {
        if(main != null)
            main.showSimulation();
    }

    @FXML void handleTransactionTab(Event event) {
        if(main != null)
            main.showTransaction();
    }

    @FXML void handleWatchlistTab(Event event) {
        if(main != null)
            main.showWatchlist();
    }

    @FXML void handleRefresh(ActionEvent actionEvent) {
        main.getUpdateService().restart();
    }

    @FXML void handleSave(ActionEvent actionEvent) {
        try {
            PortfolioIO.serialize(main.getPortfolio(), "./portfolios/" + main.getPortfolio().getId() + ".port");
        } catch (IOException e){
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML void handleLogout(ActionEvent actionEvent) {
        //ask the user if they want to save
        logoutBox = new LogoutConfirmationController();

        try {
            UndoRedoFunctions.createNewUndoRedoFunctions();
            logoutBox.showMessageBox(main.getPrimaryStage(), main);
        } catch (Exception e) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
