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

    ArrayList<MarketEquity> searchResultsTicker;
    ArrayList<MarketEquity> searchResultsName;
    ArrayList<MarketEquity> searchResultsIndex;

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

        primaryStage.setWidth(500);
        primaryStage.setHeight(475);

        //initialize the search result arrays
        searchResultsTicker = new ArrayList<>();
        searchResultsName = new ArrayList<>();
        searchResultsIndex = new ArrayList<>();

        //Set the title for the scene
        primaryStage.setTitle("Market");
        scenetitle = new Text("Market Search");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 1, 0, 3, 1);

        //########## Ticker Section ############

        //Ticker label
        tickerSearch = new Label("Ticker");
        grid.add(tickerSearch, 0, 1);

        //Ticker search type choice box
        ChoiceBox tickerCB =  new ChoiceBox();
        tickerCB.setValue("Select Query Type");
        tickerCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT, MatchType.BEGIN, MatchType.CONTAINED));
        grid.add(tickerCB, 1, 1);

        //Ticker search field
        tickerField = new TextField();
        grid.add(tickerField, 2, 1);

        //########## Name Section ############

        //Name label
        nameSearch = new Label("Name");
        grid.add(nameSearch, 0, 2);

        //Name search type choice box
        ChoiceBox nameCB =  new ChoiceBox();
        nameCB.setValue("Select Query Type");
        nameCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT,MatchType.BEGIN,MatchType.CONTAINED));
        grid.add(nameCB, 1, 2);

        //Name search field
        nameField = new TextField();
        grid.add(nameField, 2, 2);

        //########## Index Section ############

        //Index Label
        indexSectorSearch = new Label("Index or Sector");
        grid.add(indexSectorSearch, 0, 3);

        //Index search type choice box
        ChoiceBox indexSectorCB =  new ChoiceBox();
        indexSectorCB.setValue("Select Query Type");
        indexSectorCB.setItems(FXCollections.observableArrayList(
                "Select Query Type",
                new Separator(), MatchType.EXACT,MatchType.BEGIN,MatchType.CONTAINED));
        grid.add(indexSectorCB, 1, 3);

        //Index search field
        indexSectorField = new TextField();
        grid.add(indexSectorField, 2, 3);



        resultsLabel = new Label("Results:");
        grid.add(resultsLabel, 0, 4);

        resultView = new ScrollPane();
        grid.add(resultView,0,5,3,1);


        resultList = new VBox();


        tickerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                setResults('t', context.getMarket().search(QueryType.TICKER , newValue, (MatchType)tickerCB.getValue()));
            }
        });

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                setResults('n', context.getMarket().search(QueryType.NAME , newValue, (MatchType)nameCB.getValue()));
            }
        });

        indexSectorField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                setResults('i', context.getMarket().search(QueryType.INDEX_OR_SECTOR , newValue, (MatchType)indexSectorCB.getValue()));
            }
        });

        primaryStage.setScene(scene);
    }

    /**
     * Displays a list of MarketEquity objects that meet current search conditions
     * @param results list of MarketEquity objects that meet the current search conditions
     */
    private void setResults(char target, ArrayList<MarketEquity> results){
        switch (target){
            case 't':
                searchResultsTicker = results;
                break;
            case 'n':
                searchResultsName = results;
                break;
            case 'i':
                searchResultsIndex = results;
                break;
        }
        resultList = new VBox();
        ArrayList<MarketEquity> common = innerJoinMarketArrays();
        for (MarketEquity e : common) {
            Label equity = new Label(e.getName());
            /*
            Button addCashAccount = new Button ("Add Cash Account");
        addCashAccount.setOnAction((event -> {
            if(newCashName != null) {
                try {
                    context.getPortfolio().addCashAccount(newCashName.getText(), Float.parseFloat((newCashBalance).getText()));
                } catch (NumberFormatException e) {

                }
            }
        }));
             */

            Button buy = new Button("Buy"); //TODO Link button to buy transaction
            buy.setOnAction(event -> {
                context.getPortfolio().buyEquity(e,10);
                System.out.println("Bought 10 shares of" + e.getName());
            });


            BorderPane equityDisplay = new BorderPane();
            equityDisplay.setLeft(equity);
            equityDisplay.setRight(buy);

            resultList.getChildren().add(equityDisplay);
        }
        resultView.setContent(resultList);
    }

    /**
     * A very very poorly implemented way of doing an inner join on all three result sets
     * TODO - Fix this in the future (Market search should be more dynamic so this doesn't have to happen)
     * @return Arraylist of market equities that are in all result sets
     */
    private ArrayList<MarketEquity> innerJoinMarketArrays(){
        ArrayList<MarketEquity> common = new ArrayList<>();
        for (MarketEquity equityTick: searchResultsTicker) {
            for (MarketEquity equityName: searchResultsName) {
                for (MarketEquity equityIndex: searchResultsIndex) {
                    if(equityTick == equityName && equityName == equityIndex){
                        common.add(equityTick);
                    }
                }
            }
        }
        return common;
    }
}
