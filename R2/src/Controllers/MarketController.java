package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Market.MatchType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MarketController extends ViewController implements Initializable {
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button searchButton;
    @FXML private TextField indexSearchField;
    @FXML private TextField nameSearchField;
    @FXML private ListView<?> searchResultsField;
    @FXML private TextField numberSharesField;
    @FXML private TextField cashAccountField;
    @FXML private Button buyButton;
    @FXML private TextField tickerSearchField;
    @FXML private ChoiceBox<MatchType> nameChoiceBox;
    @FXML private ChoiceBox<MatchType> indexChoiceBox;
    @FXML private ChoiceBox<MatchType> tickerChoiceBox;
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
        //endregion

        tickerChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        tickerChoiceBox.setValue(MatchType.CONTAINED);
        indexChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        indexChoiceBox.setValue(MatchType.CONTAINED);
        nameChoiceBox.setItems(FXCollections.observableArrayList(MatchType.EXACT, MatchType.CONTAINED, MatchType.BEGIN));
        nameChoiceBox.setValue(MatchType.CONTAINED);
    }

    @FXML
    void handleSearch(ActionEvent event) {
        System.out.println(tickerChoiceBox.getValue());
    }

    @FXML
    void handleBuy(ActionEvent event) {

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
    }
}

