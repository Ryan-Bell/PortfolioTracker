package Controllers;

import Models.FileIO.FXMLLoaderExtended;
import Models.Portfolio.Portfolio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    //keep reference to the stage so scene can be set after startup
    private Stage primaryStage;

    //save the views so only one of each is created
    private Scene loginView;
    private Scene portfolioView;
    private Scene importView;
    private Scene marketView;
    private Scene simulationView;
    private Scene transactionView;
    private Scene watchlistView;

    //Save reference to the portfolio object for all of the other views
    private Portfolio portfolio;

    public Portfolio getPortfolio(){return portfolio;}
    public void setPortfolio(Portfolio portfolio){this.portfolio = portfolio;}

    /** Starts the FXML Application
     * @param primaryStage the stage to build all the scenes on
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        //save reference to the primaryStage
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welcome");

        //show the login page on startup
        showLogin();
    }

    //region ShowViews
    /**
     * Handles displaying of the login scene and only allows for one instance
     */
    public void showLogin(){
        if(loginView == null) {
            loginView = createScene("login");
        }
        setAndShow(loginView);
    }

    /**
     * Handles displaying of the portfolio scene and only allows for one instance
     */
    public void showPortfolio(){
        if(portfolioView == null) {
            portfolioView = createScene("portfolio");
        }
        setAndShow(portfolioView);
    }

    /**
     * Handles displaying of the import scene and only allows for one instance
     */
    public void showImport(){
        if(importView == null) {
            importView = createScene("import");
        }
        setAndShow(importView);
    }

    /**
     * Handles displaying of the market scene and only allows for one instance
     */
    public void showMarket(){
        if(marketView == null) {
            marketView = createScene("market");
        }
        setAndShow(marketView);
    }

    /**
     * Handles displaying of the simulation scene and only allows for one instance
     */
    public void showSimulation(){
        if(simulationView == null) {
            simulationView = createScene("simulation");
        }
        setAndShow(simulationView);
    }

    /**
     * Handles displaying of the transaction scene and only allows for one instance
     */
    public void showTransaction(){
        if(transactionView == null) {
            transactionView = createScene("transactionlog");
        }
        setAndShow(transactionView);
    }

    /**
     * Handles displaying of the watchlist scene and only allows for one instance
     */
    public void showWatchlist(){
        if(watchlistView == null) {
            watchlistView = createScene("watchlist");
        }
        setAndShow(watchlistView);
    }
    //endregion

    private Scene createScene(String filePrefix){
        try {
            FXMLLoaderExtended loader = new FXMLLoaderExtended(getClass().getResource("../Views/" + filePrefix + ".fxml"));
            return new Scene(loader.load(this));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setAndShow(Scene target){
        //set and show the scene
        primaryStage.setScene(target);
        primaryStage.show();
    }

    //entry point to launch application
    public static void main(String[] args) {
        launch(args);
    }
}
