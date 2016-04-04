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
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField newBalanceField;
    @FXML private TableColumn<?, ?> cashAccountDate;
    @FXML private TextField withdrawField;
    @FXML private TableColumn<?, ?> cashAccountBalance;
    @FXML private TextField newAccountField;
    @FXML private TextField transferTargetField;
    @FXML private TextField removeAccountField;
    @FXML private TextField sellSharesField;
    @FXML private Button depositButton;
    @FXML private Button sellEquityButton;
    @FXML private Button transferButton;
    @FXML private TableColumn<?, ?> equityDate;
    @FXML private TableColumn<?, ?> equityValue;
    @FXML private TableColumn<?, ?> cashAccountName;
    @FXML private TableColumn<?, ?> equityPrice;
    @FXML private TableColumn<?, ?> equityName;
    @FXML private TableColumn<?, ?> equityShares;
    @FXML private Button withdrawButton;
    @FXML private TextField depositField;
    @FXML private TableColumn<?, ?> equityTicker;
    @FXML private TextField sellNameField;
    @FXML private Button removeAccountButton;
    @FXML private TextField transferAmountField;
    @FXML private Button addAccountButton;
    //endregion

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
}

