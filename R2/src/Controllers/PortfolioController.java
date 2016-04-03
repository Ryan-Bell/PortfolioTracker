/**
 * Sample Skeleton for 'portfolio.fxml' Controller Class
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class PortfolioController extends ViewController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="newBalanceField"
    private TextField newBalanceField; // Value injected by FXMLLoader

    @FXML // fx:id="cashAccountDate"
    private TableColumn<?, ?> cashAccountDate; // Value injected by FXMLLoader

    @FXML // fx:id="withdrawField"
    private TextField withdrawField; // Value injected by FXMLLoader

    @FXML // fx:id="cashAccountBalance"
    private TableColumn<?, ?> cashAccountBalance; // Value injected by FXMLLoader

    @FXML // fx:id="newAccountField"
    private TextField newAccountField; // Value injected by FXMLLoader

    @FXML // fx:id="transferTargetField"
    private TextField transferTargetField; // Value injected by FXMLLoader

    @FXML // fx:id="removeAccountField"
    private TextField removeAccountField; // Value injected by FXMLLoader

    @FXML // fx:id="sellSharesField"
    private TextField sellSharesField; // Value injected by FXMLLoader

    @FXML // fx:id="depositButton"
    private Button depositButton; // Value injected by FXMLLoader

    @FXML // fx:id="sellEquityButton"
    private Button sellEquityButton; // Value injected by FXMLLoader

    @FXML // fx:id="transferButton"
    private Button transferButton; // Value injected by FXMLLoader

    @FXML // fx:id="equityDate"
    private TableColumn<?, ?> equityDate; // Value injected by FXMLLoader

    @FXML // fx:id="equityValue"
    private TableColumn<?, ?> equityValue; // Value injected by FXMLLoader

    @FXML // fx:id="cashAccountName"
    private TableColumn<?, ?> cashAccountName; // Value injected by FXMLLoader

    @FXML // fx:id="equityPrice"
    private TableColumn<?, ?> equityPrice; // Value injected by FXMLLoader

    @FXML // fx:id="equityName"
    private TableColumn<?, ?> equityName; // Value injected by FXMLLoader

    @FXML // fx:id="equityShares"
    private TableColumn<?, ?> equityShares; // Value injected by FXMLLoader

    @FXML // fx:id="withdrawButton"
    private Button withdrawButton; // Value injected by FXMLLoader

    @FXML // fx:id="depositField"
    private TextField depositField; // Value injected by FXMLLoader

    @FXML // fx:id="equityTicker"
    private TableColumn<?, ?> equityTicker; // Value injected by FXMLLoader

    @FXML // fx:id="sellNameField"
    private TextField sellNameField; // Value injected by FXMLLoader

    @FXML // fx:id="removeAccountButton"
    private Button removeAccountButton; // Value injected by FXMLLoader

    @FXML // fx:id="transferAmountField"
    private TextField transferAmountField; // Value injected by FXMLLoader

    @FXML // fx:id="addAccountButton"
    private Button addAccountButton; // Value injected by FXMLLoader

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

    }

    @FXML
    void handleRemoveCashAccount(ActionEvent event) {

    }

    @FXML
    void handleSellEquity(ActionEvent event) {

    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert newBalanceField != null : "fx:id=\"newBalanceField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountDate != null : "fx:id=\"cashAccountDate\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert withdrawField != null : "fx:id=\"withdrawField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountBalance != null : "fx:id=\"cashAccountBalance\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert newAccountField != null : "fx:id=\"newAccountField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferTargetField != null : "fx:id=\"transferTargetField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert removeAccountField != null : "fx:id=\"removeAccountField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert sellSharesField != null : "fx:id=\"sellSharesField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert depositButton != null : "fx:id=\"depositButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert sellEquityButton != null : "fx:id=\"sellEquityButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferButton != null : "fx:id=\"transferButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityDate != null : "fx:id=\"equityDate\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityValue != null : "fx:id=\"equityValue\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert cashAccountName != null : "fx:id=\"cashAccountName\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityPrice != null : "fx:id=\"equityPrice\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityName != null : "fx:id=\"equityName\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityShares != null : "fx:id=\"equityShares\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert withdrawButton != null : "fx:id=\"withdrawButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert depositField != null : "fx:id=\"depositField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert equityTicker != null : "fx:id=\"equityTicker\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert sellNameField != null : "fx:id=\"sellNameField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert removeAccountButton != null : "fx:id=\"removeAccountButton\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert transferAmountField != null : "fx:id=\"transferAmountField\" was not injected: check your FXML file 'portfolio.fxml'.";
        assert addAccountButton != null : "fx:id=\"addAccountButton\" was not injected: check your FXML file 'portfolio.fxml'.";

    }
}

