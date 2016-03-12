package View;

/**
 * The first
 */
import MarketSimulation.Simulation;
import Portfolio.UserAuthentication;
import javafx.application.Application;
import javafx.stage.Stage;
import Portfolio.Portfolio;

public class Context extends Application{
    private View view;
    private Stage primaryStage;

    //Collection of States
    private LoginView loginView;
    private PortfolioView portfolioView;
    private SimulationView simulationView;

    //Collection of Models
    private UserAuthentication userAuthentication;
    private Portfolio portfolio;

    public static void main(String[] args) {
        if (args.length > 0){

        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;

        //Create necessary model objects
        userAuthentication = new UserAuthentication();

        //Create necessary view
        portfolioView = new PortfolioView();
        simulationView = new SimulationView();

        setView (new LoginView());
    }

    public void setView(View newView){
        this.view = newView;
        this.view.display(this);
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

    public Portfolio getPortfolio() {
        return portfolio;
    }


}
