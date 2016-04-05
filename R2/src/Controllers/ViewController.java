package Controllers;

import Models.FileIO.PortfolioIO;
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

    public void setMain(Main main){
        this.main = main;
    }

    @Override
    public void update(Observable o, Object arg) {}

    @FXML void handlePortfolioTab(Event event){
        System.out.println("show port");
        if(main != null)
            main.showPortfolio();
    }

    @FXML void handleMarketTab(Event event) {
        System.out.println("show Market");
        if(main != null)
            main.showMarket();
    }

    @FXML void handleImportTab(Event event) {
        System.out.println("show import");
        if(main != null)
            main.showImport();
    }

    @FXML void handleSimulationTab(Event event) {
        System.out.println("show sim");
        if(main != null)
            main.showSimulation();
    }

    @FXML void handleTransactionTab(Event event) {
        System.out.println("show trans");
        if(main != null)
            main.showTransaction();
    }

    @FXML void handleWatchlistTab(Event event) {
        System.out.println("show watch");
        if(main != null)
            main.showWatchlist();
    }

    @FXML void handleRefresh(ActionEvent actionEvent) {

    }

    @FXML void handleSave(ActionEvent actionEvent) {
        try {
            PortfolioIO.serialize(main.getPortfolio(), "./portfolios/" + main.getPortfolio().getId() + ".port");
        } catch (IOException e){
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML void handleLogout(ActionEvent actionEvent) {

    }
}
