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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the import view
 */
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
    @FXML private Label importErrorLabel;
    //endregion

    //a portfolio IO  object to read in the other portfolio
    PortfolioIO portfolioIO;

    //List that will contain the conflicting cash accounts
    ArrayList<CashAccount> conflicts;

    //List that will store the result outputs
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
        assert importErrorLabel != null : "fx:id=\"importErrorLabel\" was not injected: check your FXML file 'import.fxml'.";
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

    /**
     * Action listener for the merge button
     */
    @FXML
    void handleMerge(){
        importErrorLabel.setText("");
        //capture the currently selected cash account from the conflict list
        if (importConflictsList.getSelectionModel().getSelectedItem() != null){
            CashAccount mergeAccount = importConflictsList.getSelectionModel().getSelectedItem();

            //execute a new deposit transaction to move the funds from one account into the other
            UndoRedoFunctions.getInstance().execute(new DepositTransaction(main.getPortfolio().getCashAccounts().get(main.getPortfolio().getCashAccNameExists(mergeAccount.getName())), mergeAccount.getBalance()));

            //display to the user that the merge took place
            results.add("Merged Cash Accounts '"+ mergeAccount.getName() + "'");

            //remove the imported account from the conflict list
            conflicts.remove(mergeAccount);

            //update the conflict list
            displayConflicts();
        }
        else{
            importErrorLabel.setText("Please Select Cash Account to Merge");
        }
    }

    /**
     * Action listener for the replace button
     */
    @FXML
    void handleReplace(){
        importErrorLabel.setText("");
        //capture the currently selected account in the conflict list
        CashAccount replacementAccount = importConflictsList.getSelectionModel().getSelectedItem();

        //remove the currently selected cash account from the portfolio
        main.getPortfolio().removeCashAccount(main.getPortfolio().getCashAccounts().get(main.getPortfolio().getCashAccNameExists(replacementAccount.getName())));

        //execute a new add cash account action to add the account to the portfolio
        UndoRedoFunctions.getInstance().execute(new AddCashAccTransaction(replacementAccount.getName(), replacementAccount.getBalance(), main.getPortfolio()));

        //display to the user that the replacement took place
        results.add("Replaced Cash Account '" + replacementAccount.getName() + "'");

        //remove the account from the conflict list
        conflicts.remove(replacementAccount);

        //update the conflict list
        displayConflicts();
    }

    /**
     * Action listener for the ignore button
     */
    @FXML
    void handleIgnore() {
        importErrorLabel.setText("");
        //capture the currently selected account from the conflicts list
        CashAccount ignoredAccount = importConflictsList.getSelectionModel().getSelectedItem();

        //display to the user that the account was ignored
        results.add("Ignored Cash Account '" + ignoredAccount.getName() + "'");

        //remove the account from the conflict list
        conflicts.remove(ignoredAccount);

        //update the conflict list
        displayConflicts();
    }

    /**
     * Action listener for the Import button
     */
    @FXML
    void handleImport() {
        importErrorLabel.setText("");
        //attempt to read in the portfolio
        Portfolio importPortfolio = portfolioIO.getPOFromId(fileNameField.getText());

        //set error text and return if the portfolio couldn't be found
        if(importPortfolio == null) {
            //TODO - set the text of the error label once the label is added
            return;
        }

        //call methods for importing the equities, transactions, and cash accounts
        importEquities(importPortfolio);
        importTransactions(importPortfolio);
        importCashAccounts(importPortfolio);

        //show the cash account conflict list
        displayConflicts();
    }

    /**
     * Updates the two lists
     */
    private void displayConflicts(){
        importConflictsList.setItems(FXCollections.observableList(conflicts));
        importResultsList.setItems(FXCollections.observableList(results));
    }

    /** Handles the importation of the equities only
     * @param importPortfolio the portfolio being read in
     */
    private void importEquities(Portfolio importPortfolio){
        //add all of the imported equities to the existing portfolio
        for(HoldingEquity equity : importPortfolio.getHoldingEquities()){
            //keeps track of the position of the currently equity in the portfolio or -1 if it doesn't exist
            int currentIndex;

            //holds the portfolio's  version of the current equity
            HoldingEquity currentEquity;

            //check that the imported equity is already in the portfolio
            if((currentIndex = main.getPortfolio().getEquityNameExists(equity.getName())) != -1){
                //save the portfolio's version of the equity
                currentEquity = main.getPortfolio().getHoldingEquities().get(currentIndex);

                //add the imported number of shares to the portfolio's version
                currentEquity.setNumShares(currentEquity.getNumShares() + equity.getNumShares());

                //show the user that the shares were added
                results.add("Added " + equity.getNumShares() + " shares to " + equity.getName());
            } else {
                //if the portfolio doesn't already have its own version, execute a new buy transaction
                UndoRedoFunctions.getInstance().execute(new BuyTransaction(equity, equity.getNumShares(), main.getPortfolio()));

                //show the user that the shares were added
                results.add("Added " + equity.getNumShares() +  " shares to " + equity.getName());
            }
        }
    }

    /** Handles importing the transactions only
     * @param importPortfolio the portfolio being read in
     */
    private void importTransactions(Portfolio importPortfolio){
        //append all of the imported transactions to the existing list
        main.getPortfolio().getTransactionLog().addAll(importPortfolio.getTransactionLog());
    }

    /** Handles importing the cash accounts only
     * @param importPortfolio
     */
    private void importCashAccounts(Portfolio importPortfolio){
        //go through all of the cash accounts in the imported portfolio
        for(CashAccount account : importPortfolio.getCashAccounts()){
            //check if the main portfolio has an account with the same name or not
            if(main.getPortfolio().getCashAccNameExists(account.getName()) != -1){
                conflicts.add(account);
            } else {
                //execute a new add cash account command if there is no name conflict
                UndoRedoFunctions.getInstance().execute(new AddCashAccTransaction(account.getName(), account.getBalance(), main.getPortfolio()));

                //show the user that the account was added
                results.add("Added Cash Account '" + account.getName() + "' with balance of " + account.getBalance());
            }
        }
    }
}

