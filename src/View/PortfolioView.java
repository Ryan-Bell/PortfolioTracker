package View;

import Portfolio.UserAuthentication;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observable;

/**
 * Created by user on 3/10/2016.
 */
public class PortfolioView extends View {

    @Override
    public void display(Context context){
        //Automatically calls the logic for checking if a preliminary scene has been created
        super.display(context);

        primaryStage.setTitle("Portfolio");

        Text scenetitle = new Text("Portfolio. Display test: "+context.getPortfolio().getTest());
        scenetitle.setId("scenetitle");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        dynamicContent.add(scenetitle);

        Button simView = new Button("Simulation View");
        grid.add(simView, 0, 4);

        simView.setOnAction((event -> {
            context.setView(context.getSimulationView());

        }));


        Scene newScene = new Scene(borderPane, 500, 475);
        Button test = new Button("Change Test Variable");
        grid.add(test, 2, 4);

        test.setOnAction((event -> {
            context.getPortfolio().setTest(100);

        }));


        primaryStage.setScene(newScene);

        context.getPortfolio().addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg){
        System.out.println("Checking update");
        for(int i = 0; i< dynamicContent.size(); i++){
            System.out.println("ID: "+dynamicContent.get(i).getId());
            switch(dynamicContent.get(i).getId()){
                case "scenetitle":
                    Text sceneTitle = (Text)dynamicContent.get(i);
                    sceneTitle.setText("Portfolio. Display test: "+context.getPortfolio().getTest());
            }
        }

    }

}
