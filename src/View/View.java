package View;

import Market.Market;
import Market.Parser;
import Portfolio.UserAuthentication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application {

    public static void main(String[] args) {

        //redirect to be able to test the parser functions
        System.out.println("program Start");

        //creating a faux market object for the parser
        Market market = new Market();

        //create the parser and start reading in the market csv
        Parser parser = new Parser(market, "./market.csv");
        parser.parseFile();

        //create the login page
        //View.launch(View.class);

    }


    @Override
    public void start(Stage primaryStage) {
        //create a instance to be able to call methods


        GridPane grid = new GridPane();

        LoginView startView = new LoginView(primaryStage,grid);

        Scene scene = new Scene(grid, 500, 475);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
