/**
 * Sample Skeleton for 'import.fxml' Controller Class
 */

package Controllers;

import Models.FileIO.PortfolioIO;
import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import Models.Transaction.AddCashAccTransaction;
import Models.Transaction.BuyTransaction;
import Models.Transaction.DepositTransaction;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImportController extends ViewController implements Initializable {

    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ListView<CashAccount> importConflictsList;
    @FXML private ListView<String> importResultsList;
    @FXML private TextField fileNameField;
    @FXML private Button importButton;
    @FXML private Button mergeButton;
    @FXML private Button ignoreButton;
    @FXML private Button replaceButton;
    //endregion

    //a portfolio IO  object to read in the other portfolio
    PortfolioIO portfolioIO;
    ArrayList<CashAccount> conflicts;
    ArrayList<String> results;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert importConflictsList != null : "fx:id=\"importConflictsList\" was not injected: check your FXML file 'import.fxml'.";
        assert importResultsList != null : "fx:id=\"importResultsList\" was not injected: check your FXML file 'import.fxml'.";
        assert fileNameField != null : "fx:id=\"fileNameField\" was not injected: check your FXML file 'import.fxml'.";
        assert importButton != null : "fx:id=\"importButton\" was not injected: check your FXML file 'import.fxml'.";
        assert mergeButton != null : "fx:id=\"mergeButton\" was not injected: check your FXML file 'import.fxml'.";
        assert ignoreButton != null : "fx:id=\"ignoreButton\" was not injected: check your FXML file 'import.fxml'.";
        assert replaceButton != null : "fx:id=\"replaceButton\" was not injected: check your FXML file 'import.fxml'.";
        //endregion

        //instantiate the PortfolioIO object
        portfolioIO = new PortfolioIO();

        //instantiate the conflicts
        conflicts = new ArrayList<>();

        //instantiate the results
        results = new ArrayList<>();
    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
    }

    @FXML
    void handleMerge(){
        CashAccount mergeAccount = importConflictsList.getSelectionModel().getSelectedItem();
        UndoRedoFunctions.getInstance().execute(new DepositTransaction(main.getPortfolio().getCashAccounts().get(main.getPortfolio().getCashAccNameExists(mergeAccount.getName())), mergeAccount.getBalance()));
        results.add("Merged Cash Accounts '"+ mergeAccount.getName() + "'");
        conflicts.remove(mergeAccount);
        displayConflicts();
    }

    @FXML
    void handleReplace(){
        main.getPortfolio().removeCashAccount(main.getPortfolio().getCashAccounts().get(main.getPortfolio().getCashAccNameExists(importConflictsList.getSelectionModel().getSelectedItem().getName())));
        CashAccount replacementAccount = importConflictsList.getSelectionModel().getSelectedItem();
        UndoRedoFunctions.getInstance().execute(new AddCashAccTransaction(replacementAccount.getName(), replacementAccount.getBalance(), main.getPortfolio()));
        results.add("Replaced Cash Account '" + replacementAccount.getName() + "'");
        conflicts.remove(replacementAccount);
        displayConflicts();
    }

    @FXML
    void handleIgnore() {
        results.add("Ignored Cash Account '" + importConflictsList.getSelectionModel().getSelectedItem().getName() + "'");
        conflicts.remove(importConflictsList.getSelectionModel().getSelectedItem());
        displayConflicts();
    }

    @FXML
    void handleImport() {

        //attempt to read in the portfolio
        Portfolio importPortfolio = portfolioIO.getPOFromId(fileNameField.getText());

        //set error text and return if the portfolio couldn't be found
        if(importPortfolio == null) {
            //TODO - set the text of the error label once the label is added
            return;
        }

        importEquities(importPortfolio);
        importTransactions(importPortfolio);
        importCashAccounts(importPortfolio);
        displayConflicts();
    }

    private void displayConflicts(){
        importConflictsList.setItems(FXCollections.observableList(conflicts));
        importResultsList.setItems(FXCollections.observableList(results));
    }

    private void importEquities(Portfolio importPortfolio){
        //add all of the imported equities to the existing portfolio
        for(HoldingEquity equity : importPortfolio.getHoldingEquities()){
            int currentIndex;
            HoldingEquity currentEquity;
            if((currentIndex = main.getPortfolio().getEquityNameExists(equity.getName())) != -1){
                currentEquity = main.getPortfolio().getHoldingEquities().get(currentIndex);
                currentEquity.setNumShares(currentEquity.getNumShares() + equity.getNumShares());
                results.add("Added " + equity.getNumShares() + " shares to " + equity.getName());
            } else {
                UndoRedoFunctions.getInstance().execute(new BuyTransaction(equity, equity.getNumShares(), main.getPortfolio()));
                results.add("Added " + equity.getNumShares() +  " shares to " + equity.getName());
            }
        }
    }

    private void importTransactions(Portfolio importPortfolio){
        //append all of the imported transactions to the existing list
        main.getPortfolio().getTransactionLog().addAll(importPortfolio.getTransactionLog());
    }

    private void importCashAccounts(Portfolio importPortfolio){
        for(CashAccount account : importPortfolio.getCashAccounts()){
            if(main.getPortfolio().getCashAccNameExists(account.getName()) != -1){
                conflicts.add(account);
            } else {
                UndoRedoFunctions.getInstance().execute(new AddCashAccTransaction(account.getName(), account.getBalance(), main.getPortfolio()));
                results.add("Added Cash Account '" + account.getName() + "' with balance of " + account.getBalance());
            }
        }
    }
}

