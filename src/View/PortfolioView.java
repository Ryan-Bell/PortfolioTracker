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

/**
 * Created by user on 3/10/2016.
 */
public class PortfolioView extends View {

    private Text scenetitle;

    @Override
    public void display(Context context){
        //Automatically calls the logic for checking if a preliminary scene has been created
        super.display(context);

//        GridPane grid = new GridPane();
        //UserAuthentication userAuthentication = new UserAuthentication();

        primaryStage.setTitle("Portfolio");

        //GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));

        scenetitle = new Text("PORTFOLIO, HOPEFULLY2");
        scenetitle.setId("scenetitle");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button simView = new Button("Simulation View");
        grid.add(simView, 0, 4);

        simView.setOnAction((event -> {
            context.setView(context.getSimulationView());

        }));

        //button to go to market view
        Button markView = new Button("Market View");
        grid.add(markView, 0, 5);

        markView.setOnAction((event -> {
            context.setView(context.getMarketView());
        }));

        Scene newScene = new Scene(grid, 500, 475);
        primaryStage.setScene(newScene);

        updateDisplay(context);
    }

    @Override
    public void updateDisplay(Context context){
        for(int i = 0; i< dynamicContent.size(); i++){
            switch(dynamicContent.get(i).getId()){
                case "scenetitle":
                    Text sceneTitle = (Text)dynamicContent.get(i);
                    sceneTitle.setText("Testing this portfolio");
                    context.getStage().setTitle("Testing");
            }
        }

    }

}
