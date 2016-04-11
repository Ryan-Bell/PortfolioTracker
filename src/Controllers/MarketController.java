package Controllers;

import Models.Market.CustomIterator;
import Models.Market.MarketEquity;
import Models.Market.MatchType;
import Models.Portfolio.CashAccount;
import Models.Transaction.BuyTransaction;
import Models.Transaction.BuyWithCashAccount;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
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

        //set how many search results should be shown per page
        resultsPerPage = 100;
    }

    /** Action listener for the next button being pressed
     * displays the next set of search results
     */
    @FXML
    void handleNext(){
        //check that there are more search results
        if(!searchIterator.hasNext()){
            //notify to the user that there are no more results
            marketErrorLabel.setText("There are no more results");
            return;
        }

        //check that there are more or less results than the results per page var
        if(!searchIterator.hasNext(resultsPerPage)){
            //display the remaining results if there aren't more than the results per page
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.toEnd()));
        } else {
            //show the next set of results
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.next(resultsPerPage)));
        }
    }

    /** Action listener for the search button
     * runs a search and shows the first set of items
     */
    @FXML
    void handleSearch() {
        //set the search iterator to a new instance based on the search criteria
        searchIterator = main.getMarket().getIterator().setSearchParams(
                tickerSearchField.getText(), tickerChoiceBox.getValue(),
                nameSearchField.getText(), nameChoiceBox.getValue(),
                indexSearchField.getText(), indexChoiceBox.getValue());

        //show either the first set or the entire result set if there are less than the results per page
        if(!searchIterator.hasNext(resultsPerPage)) {
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.toEnd()));
        } else {
            searchResultsField.setItems(FXCollections.observableArrayList(searchIterator.next(resultsPerPage)));
        }
    }

    /** Action listener for the buy button
     * handles purchasing the number of equities and with the specified account (optional)
     */
    @FXML
    void handleBuy() {
        //the selected equity
        MarketEquity target;

        //notify the user if they haven't selected an equity to buy
        if ((target = searchResultsField.getSelectionModel().getSelectedItem()) == null){
            marketErrorLabel.setText("Please Enter Share to Buy");
            return;
        }

        //notify the user that they haven't specified the number of shares
        if (numberSharesField.getText().equals("")){
            marketErrorLabel.setText("Please Select The Number Of Shares");
            return;
        }

        //int to hold the number of shares to be purchased
        int shares;

        //attempt to parse the number of shares into an int
        try {
            shares = Integer.parseInt(numberSharesField.getText());

            //check that the number of shares is positive
            if (shares < 0) {
                throw new Exception();
            }

        //notify the user that they need to enter a valid positive number
        } catch (Exception e){
            marketErrorLabel.setText("Please Enter A Positive Integer Number of Shares");
            return;
        }

        //check that the cash account field is not blank
        if (!(cashAccountField.getText().equals(""))){
            //check that the account name entered exists
            if (main.getPortfolio().getCashAccNameExists(cashAccountField.getText()) != -1){

                //save the position of the cash account
                int index = main.getPortfolio().getCashAccNameExists(cashAccountField.getText());

                //retrieve the specified account
                CashAccount cashAccount = main.getPortfolio().getCashAccounts().get(index);

                //create a decorated buy command that withdraws from the account
                BuyWithCashAccount newBuy = new BuyWithCashAccount(new BuyTransaction(target,shares,main.getPortfolio()),cashAccount);

                //add it to the portfolio transaction list and execute it
                main.getPortfolio().addTransaction(newBuy);
                UndoRedoFunctions.getInstance().execute(newBuy);
            }
            else {
                //notify the user that they did not enter a valid account
                marketErrorLabel.setText("Please Enter Valid Cash Account");
            }
        }
        else{
            //create a new simple buy command
            BuyTransaction newBuy = new BuyTransaction(target,shares,main.getPortfolio());

            //add the command to the portfolio transaction list and execute it
            main.getPortfolio().addTransaction(newBuy);
            UndoRedoFunctions.getInstance().execute(newBuy);
        }
    }

    /**
     * Action listener that attempts to add the selected equity to the watchlist
     */
    @FXML
    void handleAddToWatchlist() {
        if(searchResultsField.getSelectionModel().getSelectedItem() != null){

            //add the selected equity to the portfolio's watchlist
            main.getPortfolio().getWatchEquities().add( searchResultsField.getSelectionModel().getSelectedItem());
        }
        else{
            //notify the user that they must select an equity
            marketErrorLabel.setText("Nothing is selected");
        }

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
    }
}

