package Controllers;

import Models.FileIO.FXMLLoaderExtended;
import Models.FileIO.Parser;
import Models.FileIO.PortfolioIO;
import Models.Market.Market;
import Models.Portfolio.Portfolio;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    //Temporary variables for all the models
    private Parser parser;
    private Market market;
    public ScheduledService<Void> updateService;

    //region GetterSetter
    /** Getter for portfolio
     * @return returns the instance of portfolio stored
     */
    public Portfolio getPortfolio(){return portfolio;}

    /** Getter for the FXML stage to attach scenes to
     * @return the currently used stage instance
     */
    public Stage getPrimaryStage(){return primaryStage;}

    /** Setter for the portfolio instance
     * @param portfolio the instance to set the soted vversion to
     */
    public void setPortfolio(Portfolio portfolio){this.portfolio = portfolio;}

    /** Getter for the market instance
     * @return the market object stored
     */
    public Market getMarket(){return market;}

    /** Setter for the market instance
     * @param market the instance to set the stored value to
     */
    public void setMarket(Market market){this.market = market;}

    /** Getter for the update serrvice
     * @return the currently used update service instance
     */
    public ScheduledService<Void> getUpdateService(){return updateService;}

    /** Setter for update service instance
     * @param updateService the instance to set the stored value to
     */
    public void setUpdateService(ScheduledService<Void> updateService){this.updateService = updateService;}
    //endregion


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

        market = new Market();

        //create a new parser with the csv market file and parse it into market
        parser = new Parser(market, "./market.csv");
        parser.parseFile();

        //create the update service that times when the market equities get updated from yahoo
        updateService = new ScheduledService<Void>() {
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    protected Void call(){
                        getMarket().updateEquities();
                        return null;
                    }
                };
            }
        };

        //set the update period to 1 minute
        updateService.setPeriod(Duration.seconds(60));
    }

    //region ShowViews
    /**
     * Handles displaying of the login scene and only allows for one instance
     */
    public void showLogin(){
        //check if a new login scene needs to be created
        if(loginView == null) {
            loginView = createScene("login");
        }

        //show the login screen
        setAndShow(loginView);
    }

    /**
     * Handles displaying of the portfolio scene and only allows for one instance
     */
    public void showPortfolio(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(portfolioView == null) {
            portfolioView = createScene("portfolio");
        //}

        //show the protfolio view
        setAndShow(portfolioView);
    }

    /**
     * Handles displaying of the import scene and only allows for one instance
     */
    public void showImport(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(importView == null) {
            importView = createScene("import");
        //}

        //show the import view
        setAndShow(importView);

    }

    /**
     * Handles displaying of the market scene and only allows for one instance
     */
    public void showMarket(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(marketView == null) {
            marketView = createScene("market");
        //}

        //show the market view
        setAndShow(marketView);
    }

    /**
     * Handles displaying of the simulation scene and only allows for one instance
     */
    public void showSimulation(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(simulationView == null) {
            simulationView = createScene("simulation");
        //}

        //show the simulation view
        setAndShow(simulationView);
    }

    /**
     * Handles displaying of the transaction scene and only allows for one instance
     */
    public void showTransaction(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(transactionView == null) {
            transactionView = createScene("transactionlog");
        //}

        //show  the transaction view
        setAndShow(transactionView);
    }

    /**
     * Handles displaying of the watchlist scene and only allows for one instance
     */
    public void showWatchlist(){
        //new view is created each time due to FXML internal illegal state exception
        //There wasn't enough time to solve the error
        //if(watchlistView == null) {
            watchlistView = createScene("watchlist");
        //}

        //show the watchlist view
        setAndShow(watchlistView);
    }
    //endregion

    /** Handles loading of many fxml files using the extended loader given their file name
     * @param filePrefix the name not including the extension of the fxml file to be loaded in
     * @return the scene created by loading the fxml file
     */
    private Scene createScene(String filePrefix){
        try {
            //load in the file using the extended loader
            FXMLLoaderExtended loader = new FXMLLoaderExtended(getClass().getResource("../Views/" + filePrefix + ".fxml"));

            //return the scene created after passing reference to main into the load method
            return new Scene(loader.load(this));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return null if the loading failed
        return null;
    }


    /** Handles showing the a specific scene
     * @param target the scene that should be shown
     */
    private void setAndShow(Scene target){
        //set and show the scene
        primaryStage.setScene(target);
        primaryStage.show();
    }

    //entry point to launch application
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-delete")){
            PortfolioIO.deleteId(args[1]);
        } else {
            launch(args);
        }
    }
}
