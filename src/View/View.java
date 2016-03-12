package View;

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

public class View{
    protected Context context;


    public void startUp(Stage primaryStage){
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 500, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void display(Context context){
        this.context = context;
        if(context.getStage().getScene() == null){
            startUp(context.getStage());
        }
    }

    public void updateDisplay(Context context){

    }
}
