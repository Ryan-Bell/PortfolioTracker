package Controllers;

import Models.Market.Market;
import Models.Market.MarketEquity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class WatchlistController extends ViewController implements Initializable, Observer {
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
    private TableView<MarketEquity> watchlistTable;

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
    private ChoiceBox selectTriggerChoiceBox;
    
    @FXML
    private Label watchlistErrorLabel;

    private ArrayList<MarketEquity> watchEquities;

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
        assert selectTriggerChoiceBox != null : "fx:id=\"selectTriggerChoiceBox\" was not injected: check your FXML file 'watchlist.fxml'.";

        watchEquities = new ArrayList<>();

    }
    @Override
    protected void setup() {
        //System.out.println("ay2");
        watchEquities.add(main.getMarket().getMarketEquities().get(22));
        watchEquities.add(main.getMarket().getMarketEquities().get(40));
        watchEquities.add(main.getMarket().getMarketEquities().get(52));
        watchEquities.add(main.getMarket().getMarketEquities().get(42));
        watchEquities.add(main.getMarket().getMarketEquities().get(62));
        watchlistTable.setItems(FXCollections.observableList(watchEquities));

        tickerTableColumn.setCellValueFactory(new PropertyValueFactory<>("tickerSymbol"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        highTriggerTableColumn.setCellValueFactory(new PropertyValueFactory<>("highTrigger"));
        lowTriggerTableColumn.setCellValueFactory(new PropertyValueFactory<>("lowTrigger"));
        trippedTableColumn.setCellValueFactory(new PropertyValueFactory<>("triggerStatus"));

        selectTriggerChoiceBox.getSelectionModel().selectFirst();

        main.getMarket().addObserver(this);
    }


    @FXML
    void handleSetHighTrigger(ActionEvent event) {
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        try{
            float amount = Float.parseFloat(highTriggerField.getText());
            watchEquities.get(currentSelection).setHighTrigger(amount);

            watchlistTable.setItems(FXCollections.observableList(watchEquities));
            watchlistTable.refresh();
            watchlistTable.getSelectionModel().selectFirst();
            watchlistTable.getFocusModel().focus(0);

        } catch (Exception e) {
        }
    }

    @FXML
    void handleSetLowTrigger(ActionEvent event) {
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        try{
            float amount = Float.parseFloat(lowTriggerField.getText());
            watchEquities.get(currentSelection).setLowTrigger(amount);

            watchlistTable.setItems(FXCollections.observableList(watchEquities));
            watchlistTable.refresh();
            watchlistTable.getSelectionModel().selectFirst();
            watchlistTable.getFocusModel().focus(0);

        } catch (Exception e) {
        }
    }

    @FXML
    void handleClearTrigger(ActionEvent event) {
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        if(selectTriggerChoiceBox.getValue().equals("Low Trigger")){
            watchEquities.get(currentSelection).setLowTrigger(-1);
        }
        else if(selectTriggerChoiceBox.getValue().equals("High Trigger")){
            watchEquities.get(currentSelection).setHighTrigger(-1);
        }
        else if(selectTriggerChoiceBox.getValue().equals("Both")){
            watchEquities.get(currentSelection).setHighTrigger(-1);
            watchEquities.get(currentSelection).setLowTrigger(-1);
        }



        watchlistTable.setItems(FXCollections.observableList(watchEquities));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    @FXML
    void handleResetTripped(ActionEvent event) {
        for (MarketEquity e:watchEquities){
            e.setTriggerStatus("");
        }
        watchlistTable.setItems(FXCollections.observableList(watchEquities));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    @FXML
    void handleRemoveItem(ActionEvent event) {
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        watchEquities.remove(currentSelection);

        watchlistTable.setItems(FXCollections.observableList(watchEquities));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    @FXML
    void handleUpdateInterval(ActionEvent event) {

    }

    @FXML
    void handleSetInterval(ActionEvent event) {
        try{
            float amount = Float.parseFloat(updateIntervalField.getText());
            main.getUpdateService().setPeriod(Duration.minutes(amount));
            main.getUpdateService().restart();
        } catch (Exception e) {
        }
    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
        transactionTab.setDisable(false);
    }

    @Override
    public void update(Observable o, Object arg){
        for (MarketEquity e:watchEquities) {
            if(e.getValue() > e.getHighTrigger() && e.getHighTrigger()!=-1){
                e.setTriggerStatus("Is above high");
            }
            else if(e.getValue() < e.getLowTrigger()){
                e.setTriggerStatus("Is under low");
            }
            else{
                if(e.getTriggerStatus().equals("Is above high")){
                    e.setTriggerStatus("Was above high");
                }
                else if(e.getTriggerStatus().equals("Is under low")){
                    e.setTriggerStatus("Was under low");
                }
            }
        }
        watchlistTable.setItems(FXCollections.observableList(watchEquities));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }
}
