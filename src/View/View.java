package View;

import Portfolio.UserAuthentication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

import java.util.ArrayList;

public class View{
    protected Context context;
    protected ArrayList<Node> dynamicContent;
    protected GridPane grid;
    protected Stage primaryStage;

    public View() {
        this.grid = new GridPane();
    }

    public void startUp(Stage primaryStage){
//        this.primaryStage = primaryStage;
//        GridPane grid = new GridPane();
//
//        Scene scene = new Scene(grid, 500, 475);
//        this.primaryStage.setScene(scene);
//        this.primaryStage.show();
    }

    public void display(Context context){
        dynamicContent = new ArrayList<Node>();
//        this.context = context;
//        if(context.getStage().getScene() == null){
//            startUp(context.getStage());
//        }
        this.context = context; //TODO: not needed?
        this.primaryStage = context.getStage();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    public void updateDisplay(Context context){

    }
}
