package View;

import Market.Market;
import Market.MarketEquity;
import Market.MatchType;
import Market.QueryType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * View for Searching and Buying MarketEquities
 */
public class MarketView extends View {

    ArrayList<MarketEquity> searchResults;

    Text scenetitle;
    Label tickerSearch;
    Label nameSearch;
    Label indexSectorSearch;
    TextField tickerField;
    TextField nameField;
    TextField indexSectorField;
    Label resultsLabel;
    VBox resultList;
    ScrollPane resultView;

    /**
     * displays search fields and results
     * @param context the current state of the application
     */
    //TODO only pass setResults a list that meets all search conditions
    @Override
    public void display(Context context) {
        super.display(context);
        searchResults = new ArrayList<>();

        primaryStage.setTitle("Market");

        scenetitle = new Text("Market Search");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 1, 0, 3, 1);

        tickerSearch = new Label("Ticker");
        grid.add(tickerSearch, 0, 1);

        ChoiceBox tickerCB =  new ChoiceBox();
        tickerCB.setValue("Select Query Type");
        tickerCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT,MatchType.BEGIN,MatchType.CONTAINED));
        grid.add(tickerCB, 1, 1);

        tickerField = new TextField();
        grid.add(tickerField, 2, 1);

        nameSearch = new Label("Name");
        grid.add(nameSearch, 0, 2);


        ChoiceBox nameCB =  new ChoiceBox();
        nameCB.setValue("Select Query Type");
        nameCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT,MatchType.BEGIN,MatchType.CONTAINED));
        grid.add(nameCB, 1, 2);


        nameField = new TextField();
        grid.add(nameField, 2, 2);

        indexSectorSearch = new Label("Index or Sector");
        grid.add(indexSectorSearch, 0, 3);


        ChoiceBox indexSectorCB =  new ChoiceBox();
        indexSectorCB.setValue("Select Query Type");
        indexSectorCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT,MatchType.BEGIN,MatchType.CONTAINED));
        grid.add(indexSectorCB, 1, 3);


        indexSectorField = new TextField();
        grid.add(indexSectorField, 2, 3);



        resultsLabel = new Label("Results:");
        grid.add(resultsLabel, 0, 4);

        resultView = new ScrollPane();
        grid.add(resultView,0,5,3,1);


        resultList = new VBox();


        //TODO Implement listener for each of the fields
        tickerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                setResults(context.getMarket().search(QueryType.TICKER , newValue, (MatchType)tickerCB.getValue()));
            }
        });

        Scene newScene = new Scene(borderPane, 500, 475);
        primaryStage.setScene(newScene);
    }

    /**
     * Displays a list of MarketEquity objects that meet current search conditions
     * @param results list of MarketEquity objects that meet the current search conditions
     */
    private void setResults(ArrayList<MarketEquity> results){
        searchResults = results;
        resultList = new VBox();
        for (MarketEquity e : results) {
            Label equity = new Label(e.getName());
            Button buy = new Button("Buy"); //TODO Link button to buy transaction
            BorderPane equityDisplay = new BorderPane();
            equityDisplay.setLeft(equity);
            equityDisplay.setRight(buy);

            resultList.getChildren().add(equityDisplay);
        }
        resultView.setContent(resultList);
    }
}
