package Controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.ObserveType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PortfolioController extends ViewController implements Initializable, Observer {
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;

    //FXML fields related to the cash account table
    @FXML private TableView<CashAccount> cashAccountTable;
    @FXML private TableColumn<CashAccount, LocalDateTime> cashAccountDate;
    @FXML private TableColumn<CashAccount, String> cashAccountBalance;
    @FXML private TableColumn<CashAccount, String> cashAccountName;
    @FXML private Label cashAccountError;

    //FXML fields related to the Equities table
    @FXML private TableView<HoldingEquity> equityTable;
    @FXML private TableColumn<HoldingEquity, LocalDateTime> equityDate;
    @FXML private TableColumn<HoldingEquity, String> equityValue;
    @FXML private TableColumn<HoldingEquity, String> equityPrice;
    @FXML private TableColumn<HoldingEquity, String> equityName;
    @FXML private TableColumn<HoldingEquity, String> equityShares;
    @FXML private TableColumn<HoldingEquity, String> equityTicker;
    @FXML private Label equitiesError;

    //Cash account actions
    @FXML private TextField withdrawField;
    @FXML private Button withdrawButton;

    @FXML private TextField depositField;
    @FXML private Button depositButton;

    @FXML private TextField transferAmountField;
    @FXML private TextField transferTargetField;
    @FXML private Button transferButton;

    @FXML private TextField newAccountField;
    @FXML private TextField newBalanceField;
    @FXML private Button addAccountButton;

    @FXML private TextField removeAccountField;
    @FXML private Button removeAccountButton;

    //Equity account actions
    @FXML private TextField sellSharesField;
    @FXML private TextField sellNameField;
    @FXML private Button sellEquityButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        //related to cash account table
        assert cashAccountTable !=  null : "fx:id=\"cashAccountTable\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountDate != null : "fx:id=\"cashAccountDate\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountBalance != null : "fx:id=\"cashAccountBalance\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountName != null : "fx:id=\"cashAccountName\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountError != null : "fx:id=\"cashAccountError\" was not injected: check your FXML file 'portfolio.fxml'.";

        //related to equity table
        assert equityTable != null : "fx:id=\"equityTable\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityDate != null : "fx:id=\"equityDate\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityValue != null : "fx:id=\"equityValue\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityPrice != null : "fx:id=\"equityPrice\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityName != null : "fx:id=\"equityName\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityShares != null : "fx:id=\"equityShares\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityTicker != null : "fx:id=\"equityTicker\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equitiesError != null : "fx:id=\"equitiesError\" was not injected: check your FXML file 'portfolio.fxml'.";

        //related to cash account actions
        assert addAccountButton != null : "fx:id=\"addAccountButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert newBalanceField != null : "fx:id=\"newBalanceField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert withdrawField != null : "fx:id=\"withdrawField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert newAccountField != null : "fx:id=\"newAccountField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferTargetField != null : "fx:id=\"transferTargetField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert removeAccountField != null : "fx:id=\"removeAccountField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert depositButton != null : "fx:id=\"depositButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferButton != null : "fx:id=\"transferButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert withdrawButton != null : "fx:id=\"withdrawButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert depositField != null : "fx:id=\"depositField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert removeAccountButton != null : "fx:id=\"removeAccountButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferAmountField != null : "fx:id=\"transferAmountField\" was not injected: check your FXML file 'portfolio.fxml'.";

        //related to equity actions
        assert sellSharesField != null : "fx:id=\"sellSharesField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert sellEquityButton != null : "fx:id=\"sellEquityButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert sellNameField != null : "fx:id=\"sellNameField\" was not injected: check your FXML file 'portfolio.fxml'.";
        //endregion

        //region CellFactories
        //set value factories for the cash account table
        cashAccountDate.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        cashAccountBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        cashAccountName.setCellValueFactory(new PropertyValueFactory<>("name"));

        //set value factories for the equity table
        equityDate.setCellValueFactory(new PropertyValueFactory<>("datePurchased"));
        equityValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        equityPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerShare"));
        equityName.setCellValueFactory(new PropertyValueFactory<>("name"));
        equityShares.setCellValueFactory(new PropertyValueFactory<>("numShares"));
        equityTicker.setCellValueFactory(new PropertyValueFactory<>("tickerSymbol"));
        //endregion

    }

    @Override
    protected void setup(){
        //set the observable list cash account table points to
        cashAccountTable.setItems(FXCollections.observableList(main.getPortfolio().getCashAccounts()));
        cashAccountTable.refresh();

        //set the observable list equity table points to
        equityTable.setItems(FXCollections.observableList(main.getPortfolio().getHoldingEquities()));
        equityTable.refresh();

        main.getPortfolio().addObserver(this);
    }

    @FXML
    void handleWithdrawCashAccount(ActionEvent event) {

    }

    @FXML
    void handleDepositCashAccount(ActionEvent event) {

    }

    @FXML
    void handleTransferCashAccount(ActionEvent event) {

    }

    @FXML
    void handleAddCashAccount(ActionEvent event) {
        //get the  proposed name for the new cash account
        String newCashAccountName = newAccountField.getText();

        //check that a cash account with the same name doesn't already exist
        if (main.getPortfolio().getCashAccNameExists(newCashAccountName)) {
            cashAccountError.setText("An account with that name already exists");
            return;
        }

        try {
            //try to add the new cash account
            main.getPortfolio().addCashAccount(newCashAccountName, Float.parseFloat(newBalanceField.getText()));
        } catch (Exception e) {
            //display an error if the float entered was not a valid number 
            cashAccountError.setText("Balance entered is not a valid float");
        }
    }

    @FXML
    void handleRemoveCashAccount(ActionEvent event) {
    }

    @FXML
    void handleSellEquity(ActionEvent event) {

    }

    @FXML
    void handleme(){}

    /**Updates the data displayed when the observable changes
     * @param o the observable object
     * @param arg an optional argument passed
     */
    @Override
    public void update(Observable o, Object arg){
        //attempt to only update the list that has changed otherwise catch it and update all
        try{
            //attempt to cast the arg object as an enum that denotes which list was changed
            switch ((ObserveType)arg){
                case CASH_ACCOUNT:
                    //update the cash account table
                    cashAccountTable.setItems(FXCollections.observableList(main.getPortfolio().getCashAccounts()));
                    cashAccountTable.refresh();
                    cashAccountTable.requestFocus();
                    cashAccountTable.getSelectionModel().selectFirst();
                    cashAccountTable.getFocusModel().focus(0);
                    break;
                case HOLDING_EQUITY:
                    //update the equity table
                    equityTable.setItems(FXCollections.observableList(main.getPortfolio().getHoldingEquities()));
                    equityTable.refresh();
                    equityTable.getSelectionModel().selectFirst();
                    equityTable.getFocusModel().focus(0);
                    break;
            }
        } catch (Exception e){
            //update both the cash account table and the equity table
            cashAccountTable.setItems(FXCollections.observableList(main.getPortfolio().getCashAccounts()));
            cashAccountTable.refresh();
            cashAccountTable.requestFocus();
            cashAccountTable.getSelectionModel().selectFirst();
            cashAccountTable.getFocusModel().focus(0);

            equityTable.setItems(FXCollections.observableList(main.getPortfolio().getHoldingEquities()));
            equityTable.refresh();
            equityTable.getSelectionModel().selectFirst();
            equityTable.getFocusModel().focus(0);
        }
    }
}

