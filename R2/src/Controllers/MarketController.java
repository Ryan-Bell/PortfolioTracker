package Controllers;

import Models.Market.MarketEquity;
import Models.Market.MatchType;
import Models.Market.QueryType;
import Models.Portfolio.CashAccount;
import Models.Transaction.BuyTransaction;
import Models.Transaction.BuyWithCashAccount;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class MarketController extends ViewController implements Initializable {
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button searchButton;
    @FXML private TextField indexSearchField;
    @FXML private TextField nameSearchField;
    @FXML private ListView<MarketEquity> searchResultsField;
    @FXML private TextField numberSharesField;
    @FXML private TextField cashAccountField;
    @FXML private Button buyButton;
    @FXML private TextField tickerSearchField;
    @FXML private ChoiceBox<MatchType> nameChoiceBox;
    @FXML private ChoiceBox<MatchType> indexChoiceBox;
    @FXML private ChoiceBox<MatchType> tickerChoiceBox;
    @FXML private Label marketErrorLabel;
    @FXML private Button addToWatchlistButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert nameChoiceBox != null : "fx:id=\"indexQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert indexChoiceBox != null : "fx:id=\"nameQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'market.fxml'.";
        assert tickerChoiceBox != null : "fx:id=\"tickerQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert indexSearchField != null : "fx:id=\"indexSearchField\" was not injected: check your FXML file 'market.fxml'.";
        assert nameSearchField != null : "fx:id=\"nameSearchField\" was not injected: check your FXML file 'market.fxml'.";
        assert searchResultsField != null : "fx:id=\"searchResultsField\" was not injected: check your FXML file 'market.fxml'.";
        assert numberSharesField != null : "fx:id=\"numberSharesField\" was not injected: check your FXML file 'market.fxml'.";
        assert cashAccountField != null : "fx:id=\"cashAccountField\" was not injected: check your FXML file 'market.fxml'.";
        assert buyButton != null : "fx:id=\"buyButton\" was not injected: check your FXML file 'market.fxml'.";
        assert tickerSearchField != null : "fx:id=\"tickerSearchField\" was not injected: check your FXML file 'market.fxml'.";
        assert marketErrorLabel != null : "fx:id=\"marketErrorLabel\" was not injected: check your FXML file 'market.fxml'.";
        assert addToWatchlistButton != null : "fx:id=\"buyButton\" was not injected: check your FXML file 'market.fxml'.";
        //endregion

        //region SelectBoxes
        tickerChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        tickerChoiceBox.setValue(MatchType.CONTAINED);
        indexChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        indexChoiceBox.setValue(MatchType.CONTAINED);
        nameChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        nameChoiceBox.setValue(MatchType.CONTAINED);
        //endregion
    }

    @FXML
    void handleSearch(ActionEvent event) {
        /*
<<<<<<< HEAD
        searchResultsField.setItems(FXCollections.observableArrayList(
                main.getMarket().search(
                        tickerSearchField.getText(), tickerChoiceBox.getValue(),
                        nameSearchField.getText(), nameChoiceBox.getValue(),
                        indexSearchField.getText(), indexChoiceBox.getValue())));
=======*/
        ArrayList<MarketEquity> tickerResults;
        ArrayList<MarketEquity> nameResults;
        ArrayList<MarketEquity> indexResults;
        HashSet<MarketEquity> combinedResults = new HashSet<>();
        ArrayList<TextField> searchFields = new ArrayList<>();
        ArrayList<ArrayList<MarketEquity>> searchResults = new ArrayList<>();
        ObservableList<MarketEquity> finalList;

        //ticker symbol
        //if(tickerChoiceBox.getValue() == MatchType.CONTAINED)
        tickerResults = main.getMarket().search(QueryType.TICKER,tickerSearchField.getText(),tickerChoiceBox.getValue());

        //name
        nameResults = main.getMarket().search(QueryType.NAME,nameSearchField.getText(),nameChoiceBox.getValue());

        //sector or index
        indexResults =  main.getMarket().search(QueryType.INDEX_OR_SECTOR,indexSearchField.getText(),indexChoiceBox.getValue());


        searchFields.add(tickerSearchField);
        searchFields.add(nameSearchField);
        searchFields.add(indexSearchField);
        searchResults.add(tickerResults);
        searchResults.add(nameResults);
        searchResults.add(indexResults);
        int i = 0;
        for (TextField searchField : searchFields){
            if (!(searchField.getText().equals(""))){
                combinedResults.addAll(searchResults.get(i));
            }
            i++;
        }
        System.out.println(combinedResults.toString());
        finalList = FXCollections.observableArrayList(combinedResults);
        searchResultsField.setItems(finalList);


    }

    /**
     *
     *
     */
    @FXML
    void handleBuy() {
        MarketEquity target;
        if ((target = searchResultsField.getSelectionModel().getSelectedItem()) == null){
            marketErrorLabel.setText("Please Enter Share to Buy");
            return;
        }

        if (numberSharesField.getText().equals("")){
            marketErrorLabel.setText("Please Select Equity");
            return;
        }

        int shares;
        try {
            shares = Integer.parseInt(numberSharesField.getText());
        } catch (Exception e){
            marketErrorLabel.setText("Please Enter Integer Number of Shares");
            return;
        }

        if (!(cashAccountField.getText().equals(""))){
            if (main.getPortfolio().getCashAccNameExists(cashAccountField.getText()) != -1){
                int index = main.getPortfolio().getCashAccNameExists(cashAccountField.getText());
                CashAccount cashAccount = main.getPortfolio().getCashAccounts().get(index);

                BuyWithCashAccount newBuy = new BuyWithCashAccount(new BuyTransaction(target,shares,main.getPortfolio()),cashAccount);
                main.getPortfolio().addTransaction(newBuy);
                UndoRedoFunctions.getInstance().execute(newBuy);
            }
            else {
                marketErrorLabel.setText("Please Enter Valid Cash Account");
            }
        }
        else{
            BuyTransaction newBuy = new BuyTransaction(target,shares,main.getPortfolio());
            main.getPortfolio().addTransaction(newBuy);
            UndoRedoFunctions.getInstance().execute(newBuy);
        }
    }

    @FXML
    void handleAddToWatchlist(ActionEvent event) {
        if(searchResultsField.getSelectionModel().getSelectedItem() != null){
            main.getPortfolio().getWatchEquities().add( searchResultsField.getSelectionModel().getSelectedItem());
        }
        else{
            marketErrorLabel.setText("Nothing is selected");
        }

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
    }
}

