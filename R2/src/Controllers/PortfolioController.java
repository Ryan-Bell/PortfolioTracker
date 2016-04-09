package Controllers;

import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.ObserveType;
import Models.Transaction.*;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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

    @FXML private Button removeAccountButton;

    //Equity account actions
    @FXML private TextField sellSharesField;
    @FXML private TextField sellNameField;
    @FXML private Button sellEquityButton;
    @FXML private Button addToWatchlistButton;
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
        assert addToWatchlistButton != null : "fx:id=\"setHighTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
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

    /**
     * Handles setup logic that needs to happen after the reference to main is set
     */
    @Override
    protected void setup(){
        //set the observable list cash account table points to
        cashAccountTable.setItems(FXCollections.observableList(main.getPortfolio().getCashAccounts()));
        cashAccountTable.refresh();

        //set the observable list equity table points to
        equityTable.setItems(FXCollections.observableList(main.getPortfolio().getHoldingEquities()));
        equityTable.refresh();

        //add this controller to the list observers that portfolio will notify on change
        main.getPortfolio().addObserver(this);
    }

    /**
     * Handles the attempted withdraw from a cash account
     */
    @FXML
    void handleWithdrawCashAccount() {
        try{
            //capture the currently selected cash account from the table
            CashAccount target = cashAccountTable.getSelectionModel().getSelectedItem();

            //try to parse the withdraw from the text field
            float amount = Float.parseFloat(withdrawField.getText());

            //check that the selected account has enough funds to support the withdrawal
            if (target.sufficientFunds(amount)) {
                //create and execute a new withdraw transaction
                UndoRedoFunctions.getInstance().execute(new WithdrawTransaction(target, amount));

                //refresh the cash account table
                cashAccountTable.refresh();
            } else {
                //notify the user that the cash account cannot support the withdrawal
                cashAccountError.setText("The withdraw amount exceeds the balance");
            }
        } catch (Exception e) {
            //notify the user that the withdraw amount was not a valid number
            cashAccountError.setText("The withdraw amount is not a valid number");
        }
    }

    /**
     * Handles the attempted deposit from a cash account
     */
    @FXML
    void handleDepositCashAccount() {
        try{
            //capture the cash account that is currently selected in the table
            CashAccount target = cashAccountTable.getSelectionModel().getSelectedItem();

            //attempt to parse the deposit amount from the text field
            float amount = Float.parseFloat(depositField.getText());

            //create and execute a new deposit transaction
            UndoRedoFunctions.getInstance().execute(new DepositTransaction(target, amount));

            //refresh the cash account table
            cashAccountTable.refresh();
        } catch (Exception e){
            //notify the user that the amount they entered was not a valid number
            cashAccountError.setText("The deposit amount is not a valid number");
        }
    }

    /**
     * Handles the attempted transfer between two accounts
     */
    @FXML
    void handleTransferCashAccount() {
        try{
            //capture the currently selected cash account from the table
            CashAccount transferFrom = cashAccountTable.getSelectionModel().getSelectedItem();
            CashAccount transferTo;

            //get the other cash account name from the text field
            String transferName = transferTargetField.getText();

            //attempt to parse the transfer amount
            float amount = Float.parseFloat(transferAmountField.getText());

            //check that the account name entered is a valid account
            if (main.getPortfolio().getCashAccNameExists(transferName) == -1){
                //notify the user that the account is not valid
                cashAccountError.setText("Not a valid cash account name for transfer");
                return;
            }
            //set the cash account being deposited into
            transferTo = main.getPortfolio().getCashAccounts().get(main.getPortfolio().getCashAccNameExists(transferName));

            //create and execute a new transfer transaction
            UndoRedoFunctions.getInstance().execute(new TransferTransaction(transferFrom, transferTo, amount));

            //refresh the account table
            cashAccountTable.refresh();
        } catch (Exception e){
            //notify the user that the transfer amount was invalid
            cashAccountError.setText("The transfer amount is not a valid number");
        }
    }

    /**
     * Handles the attempted addition of a cash account
     */
    @FXML
    void handleAddCashAccount() {
        //get the  proposed name for the new cash account
        String newCashAccountName = newAccountField.getText();

        //check that a cash account with the same name doesn't already exist
        if (main.getPortfolio().getCashAccNameExists(newCashAccountName) != -1) {
            //notify the user that the name already exists
            cashAccountError.setText("An account with that name already exists");
            return;
        }

        try {
            //attempt to parse the initial balance
            float amount = Float.parseFloat(newBalanceField.getText());

            //try to add the new cash account
            UndoRedoFunctions.getInstance().execute(new AddCashAccTransaction(newCashAccountName, amount, main.getPortfolio()));
            cashAccountTable.refresh();
        } catch (Exception e) {
            //display an error if the float entered was not a valid number
            cashAccountError.setText("Balance entered is not a valid float");
        }
    }

    /**
     * Handles the attempted removal of a cash account
     */
    @FXML
    void handleRemoveCashAccount() {
        //attempt to remove the currently selected cash account and display error otherwise
        try {
            //attempt to create and execute a remove transaction on the currently selected account
            UndoRedoFunctions.getInstance().execute(new RemoveCashAccTransaction(cashAccountTable.getSelectionModel().getSelectedItem(), main.getPortfolio()));

            //refresh the account table
            cashAccountTable.refresh();
        } catch (Exception e){
            //notify the user that there was an error in the process
            cashAccountError.setText("Could not remove the selected account");
        }
    }

    /**
     * Handles the attempted selling of an equity
     */
    @FXML
    void handleSellEquity() {
        try{
            //attempt to parse the number of shares
            int numShares = Integer.parseInt(sellSharesField.getText());

            //capture the currently selected equity from the table
            HoldingEquity equity = equityTable.getSelectionModel().getSelectedItem();
            String accountTarget;

            //check if a cash account destination has been specified
            if((accountTarget = sellNameField.getText()) != null){
                //check that the account name specified exists
                int accountIndex = main.getPortfolio().getCashAccNameExists(accountTarget);
                if(accountIndex != -1) {
                    //get the cash account specified by name
                    CashAccount target = main.getPortfolio().getCashAccounts().get(accountIndex);

                    //execute a new decorated sell command
                    UndoRedoFunctions.getInstance().execute(new SellWithCashAccount(new SellTransaction(equity, numShares, main.getPortfolio()), target));
                } else {
                    //notify the user the cash account is invalid
                    equitiesError.setText("Cash account name is not valid");
                }
            } else {
                //execute a new simple sell transaction
                UndoRedoFunctions.getInstance().execute(new SellTransaction(equity, numShares, main.getPortfolio()));
            }
            //refresh the equity table
            equityTable.refresh();
        } catch (Exception e){
            //notify the user that the share count was invalid
            equitiesError.setText("Number of shares is not a valid number");
        }
    }

    @FXML
    void handleAddToWatchlist(ActionEvent event) {

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

