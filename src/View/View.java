package View;

import Portfolio.Portfolio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer{
    protected Context context;
    protected ArrayList<Node> dynamicContent;
    protected GridPane grid;
    protected Stage primaryStage;
    protected HBox toolbar;
    protected BorderPane borderPane;
    protected Scene scene;

    public View() {
        this.borderPane = new BorderPane();
        this.grid = new GridPane();
        scene = new Scene(borderPane, 500, 475);
    }

    public void display(Context context){
        dynamicContent = new ArrayList<Node>();

        this.context = context; //TODO: not needed?
        this.primaryStage = context.getStage();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        toolbar = new HBox(10);
        toolbar.setAlignment(Pos.TOP_CENTER);

//        for (int i = 0; i < 10; i++) {
//            toolbar.getChildren().add(new Button("Click me"));
//        }

        Text pageTitle = new Text("Financial Portfolio Tracking System");
        pageTitle.setId("FPTS");
        pageTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Button logout = new Button("Logout");
        logout.setOnAction((event1 -> {
            //automatically save the portfolio
            try {
                Portfolio.serialize(context.getPortfolio(), "./portfolios/" + context.getPortfolio().getId() + ".port");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.exit(0);
        }));

        Button save = new Button("Save");
        save.setOnAction((event -> {
            try {
                Portfolio.serialize(context.getPortfolio(), "./portfolios/" + context.getPortfolio().getId() + ".port");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }));

        Button simView = new Button("Sim View");
        simView.setOnAction((event -> {
            context.setView(context.getSimulationView());

        }));

        Button portView = new Button("Portfolio View");
        portView.setOnAction((event -> {
            context.setView(context.getPortfolioView());

        }));

        Button marketView = new Button("Market View");
        marketView.setOnAction((event -> {
            context.setView(context.getMarketView());

        }));

        grid.add(toolbar, 0, 0);
        borderPane.setCenter(grid);


        borderPane.setTop(pageTitle);
        borderPane.setAlignment(pageTitle, Pos.TOP_CENTER);


        VBox menu = new VBox(save,logout, portView, marketView, simView);
        menu.setPadding(new Insets(10,10,10,10));
        menu.setSpacing(10);


        if (!(context.getView() instanceof LoginView)){
            //borderPane.setAlignment(logout,Pos.TOP_RIGHT);

            borderPane.setLeft(menu);


        }

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
