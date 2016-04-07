package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WatchlistController extends ViewController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> nameTableColumn;

    @FXML
    private Tab portfolioTab;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<?> watchlistTable;

    @FXML
    private TextField updateIntervalField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button clearTriggerButton;

    @FXML
    private Button resetTrippedButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button setIntervalButton;

    @FXML
    private Tab importTab;

    @FXML
    private Button setLowTriggerButton;

    @FXML
    private TableColumn<?, ?> trippedTableColumn;

    @FXML
    private Tab watchlistTab;

    @FXML
    private Button setHighTriggerButton;

    @FXML
    private Tab simulationTab;

    @FXML
    private TextField highTriggerField;

    @FXML
    private TableColumn<?, ?> tickerTableColumn;

    @FXML
    private Tab transactionTab;

    @FXML
    private Tab marketTab;

    @FXML
    private Button removeItemButton;

    @FXML
    private TableColumn<?, ?> priceTableColumn;

    @FXML
    private TableColumn<?, ?> highTriggerTableColumn;

    @FXML
    private TableColumn<?, ?> lowTriggerTableColumn;

    @FXML
    private TextField lowTriggerField;

    @FXML
    private MenuButton selectTriggerMenuButton;
    
    @FXML
    private Label watchlistErrorLabel;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert nameTableColumn != null : "fx:id=\"nameTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert portfolioTab != null : "fx:id=\"portfolioTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert watchlistTable != null : "fx:id=\"watchlistTable\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert updateIntervalField != null : "fx:id=\"updateIntervalField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert clearTriggerButton != null : "fx:id=\"clearTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert resetTrippedButton != null : "fx:id=\"resetTrippedButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setIntervalButton != null : "fx:id=\"setIntervalButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert importTab != null : "fx:id=\"importTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setLowTriggerButton != null : "fx:id=\"setLowTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert trippedTableColumn != null : "fx:id=\"trippedTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert watchlistTab != null : "fx:id=\"watchlistTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setHighTriggerButton != null : "fx:id=\"setHighTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert simulationTab != null : "fx:id=\"simulationTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert highTriggerField != null : "fx:id=\"highTriggerField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert tickerTableColumn != null : "fx:id=\"tickerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert transactionTab != null : "fx:id=\"transactionTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert marketTab != null : "fx:id=\"marketTab\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert removeItemButton != null : "fx:id=\"removeItemButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert priceTableColumn != null : "fx:id=\"priceTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert highTriggerTableColumn != null : "fx:id=\"highTriggerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert lowTriggerTableColumn != null : "fx:id=\"lowTriggerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert lowTriggerField != null : "fx:id=\"lowTriggerField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert selectTriggerMenuButton != null : "fx:id=\"selectTriggerMenuButton\" was not injected: check your FXML file 'watchlist.fxml'.";

    }


    @FXML
    void handleSetHighTrigger(ActionEvent event) {

    }

    @FXML
    void handleSetLowTrigger(ActionEvent event) {

    }

    @FXML
    void handleClearTrigger(ActionEvent event) {

    }

    @FXML
    void handleResetTripped(ActionEvent event) {

    }

    @FXML
    void handleRemoveItem(ActionEvent event) {

    }

    @FXML
    void handleUpdateInterval(ActionEvent event) {

    }

    @FXML
    void handleSetInterval(ActionEvent event) {

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
        transactionTab.setDisable(false);
    }
}
