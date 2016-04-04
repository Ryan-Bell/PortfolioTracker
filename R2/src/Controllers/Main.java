package Controllers;

import Models.FXMLLoaderExtended;
import Models.Portfolio;
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


    /**
     * Handles displaying of the login scene and only allows for one instance
     */
    public void showLogin(){
        if(loginView == null) {
            ///load up the portfolio page and pass it reference to this class
            try {
                FXMLLoaderExtended loader = new FXMLLoaderExtended(getClass().getResource("../Views/login.fxml"));
                loginView = new Scene(loader.load(this));
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //set and show the scene
        primaryStage.setScene(loginView);
        primaryStage.show();
    }

    /**
     * Handles displaying of the portfolio scene and only allows for one instance
     */
    public void showPortfolio(){
        if(portfolioView == null) {
            ///load up the portfolio page and pass it reference to this class
            try {
                FXMLLoaderExtended loader = new FXMLLoaderExtended(getClass().getResource("../Views/container.fxml"));
                portfolioView = new Scene(loader.load(this));
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //set and show the scene
        primaryStage.setScene(portfolioView);
        primaryStage.show();
    }

    //entry point to launch application
    public static void main(String[] args) {
        launch(args);
    }
}
