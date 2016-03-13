package View;

import Market.Market;
import Market.Parser;
import MarketSimulation.Simulation;
import Portfolio.UserAuthentication;
import javafx.application.Application;
import javafx.stage.Stage;
import Portfolio.Portfolio;

/**
 * Provides entry point to the application, initializing classes,
 * and maintains a reference to the current state.
 */
public class Context extends Application{
    private View view;
    private Stage primaryStage;

    //Collection of States
    private LoginView loginView;
    private PortfolioView portfolioView;
    private SimulationView simulationView;
    private MarketView marketView;

    //Collection of Models
    private UserAuthentication userAuthentication;

    private Portfolio portfolio;
    private Market market;
    private Parser parser;

    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-delete")){
            UserAuthentication.deleteId(args[1]);
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage){
        System.out.println("check2");
        this.primaryStage = primaryStage;

        //Create necessary model objects
        userAuthentication = new UserAuthentication();
        market = new Market();
        parser = new Parser(market, "./market.csv");

        //Create necessary view
        portfolioView = new PortfolioView();
        simulationView = new SimulationView();
        marketView = new MarketView();
        loginView = new LoginView();

        //run the parser on the csv to fill out the market object

        parser.parseFile();

        setView (loginView);
    }

    public void setView(View newView){
        this.view = newView;
        this.view.display(this);
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public View getView(){
        return this.view;
    }

    public Stage getStage(){
        return this.primaryStage;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public PortfolioView getPortfolioView() {
        return portfolioView;
    }

    public SimulationView getSimulationView() {
        return simulationView;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public Market getMarket(){return market; }

    public MarketView getMarketView(){ return marketView;}

    public Portfolio getPortfolio() {
        return portfolio;
    }


}
