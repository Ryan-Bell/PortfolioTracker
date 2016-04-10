package Controllers;

import Models.Market.CustomIterator;
import Models.Market.MarketEquity;
import Models.Market.MatchType;
import Models.Portfolio.CashAccount;
import Models.Transaction.BuyTransaction;
import Models.Transaction.BuyWithCashAccount;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML private Button nextButton;
    private CustomIterator searchIterator;
    private int resultsPerPage;
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
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'market.fxml'.";
        //endregion

        //region SelectBoxes
        tickerChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        tickerChoiceBox.setValue(MatchType.CONTAINED);
        indexChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        indexChoiceBox.setValue(MatchType.CONTAINED);
        nameChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        nameChoiceBox.setValue(MatchType.CONTAINED);
        //endregion

        resultsPerPage = 100;
    }

    @FXML
    void handleNext(ActionEvent event){
        if(!searchIterator.hasNext()){
            marketErrorLabel.setText("There are no more results");
            return;
        }
        if(!searchIterator.hasNext(resultsPerPage)){
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.toEnd()));
        } else {
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.next(resultsPerPage)));
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        searchIterator = main.getMarket().getIterator().setSearchParams(
                tickerSearchField.getText(), tickerChoiceBox.getValue(),
                nameSearchField.getText(), nameChoiceBox.getValue(),
                indexSearchField.getText(), indexChoiceBox.getValue());
        if(!searchIterator.hasNext(resultsPerPage)) {
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.toEnd()));
        } else {
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.next(resultsPerPage)));
        }
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
            marketErrorLabel.setText("Please Select The Number Of Shares");
            return;
        }

        int shares;
        try {
            shares = Integer.parseInt(numberSharesField.getText());

            if (shares < 0) {
                throw new Exception();
            }

        } catch (Exception e){
            marketErrorLabel.setText("Please Enter A Positive Integer Number of Shares");
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

