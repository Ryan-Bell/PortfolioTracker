/**
 * Sample Skeleton for 'market.fxml' Controller Class
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class MarketController extends ViewController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="indexQueryType"
    private MenuButton indexQueryType; // Value injected by FXMLLoader

    @FXML // fx:id="nameQueryType"
    private MenuButton nameQueryType; // Value injected by FXMLLoader

    @FXML // fx:id="searchButton"
    private Button searchButton; // Value injected by FXMLLoader

    @FXML // fx:id="tickerQueryType"
    private MenuButton tickerQueryType; // Value injected by FXMLLoader

    @FXML // fx:id="indexSearchField"
    private TextField indexSearchField; // Value injected by FXMLLoader

    @FXML // fx:id="nameSearchField"
    private TextField nameSearchField; // Value injected by FXMLLoader

    @FXML // fx:id="searchResultsField"
    private ListView<?> searchResultsField; // Value injected by FXMLLoader

    @FXML // fx:id="numberSharesField"
    private TextField numberSharesField; // Value injected by FXMLLoader

    @FXML // fx:id="cashAccountField"
    private TextField cashAccountField; // Value injected by FXMLLoader

    @FXML // fx:id="buyButton"
    private Button buyButton; // Value injected by FXMLLoader

    @FXML // fx:id="tickerSearchField"
    private TextField tickerSearchField; // Value injected by FXMLLoader

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    void handleBuy(ActionEvent event) {

    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert indexQueryType != null : "fx:id=\"indexQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert nameQueryType != null : "fx:id=\"nameQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'market.fxml'.";
        assert tickerQueryType != null : "fx:id=\"tickerQueryType\" was not injected: check your FXML file 'market.fxml'.";
        assert indexSearchField != null : "fx:id=\"indexSearchField\" was not injected: check your FXML file 'market.fxml'.";
        assert nameSearchField != null : "fx:id=\"nameSearchField\" was not injected: check your FXML file 'market.fxml'.";
        assert searchResultsField != null : "fx:id=\"searchResultsField\" was not injected: check your FXML file 'market.fxml'.";
        assert numberSharesField != null : "fx:id=\"numberSharesField\" was not injected: check your FXML file 'market.fxml'.";
        assert cashAccountField != null : "fx:id=\"cashAccountField\" was not injected: check your FXML file 'market.fxml'.";
        assert buyButton != null : "fx:id=\"buyButton\" was not injected: check your FXML file 'market.fxml'.";
        assert tickerSearchField != null : "fx:id=\"tickerSearchField\" was not injected: check your FXML file 'market.fxml'.";

    }
}

