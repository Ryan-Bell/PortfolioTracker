package Controllers;

import Models.Market.MarketEquity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class WatchlistController extends ViewController implements Initializable, Observer {
    //region FXML Fields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<?, ?> nameTableColumn;
    @FXML private TableView<MarketEquity> watchlistTable;
    @FXML private TextField updateIntervalField;
    @FXML private Button clearTriggerButton;
    @FXML private Button resetTrippedButton;
    @FXML private Button setIntervalButton;
    @FXML private Button setLowTriggerButton;
    @FXML private TableColumn<?, ?> trippedTableColumn;
    @FXML private Button setHighTriggerButton;
    @FXML private TextField highTriggerField;
    @FXML private TableColumn<?, ?> tickerTableColumn;
    @FXML private Button removeItemButton;
    @FXML private TableColumn<?, ?> priceTableColumn;
    @FXML private TableColumn<?, ?> highTriggerTableColumn;
    @FXML private TableColumn<?, ?> lowTriggerTableColumn;
    @FXML private TextField lowTriggerField;
    @FXML private ChoiceBox selectTriggerChoiceBox;
    @FXML private Label watchlistErrorLabel;
    //endregion


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert nameTableColumn != null : "fx:id=\"nameTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert watchlistTable != null : "fx:id=\"watchlistTable\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert updateIntervalField != null : "fx:id=\"updateIntervalField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert clearTriggerButton != null : "fx:id=\"clearTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert resetTrippedButton != null : "fx:id=\"resetTrippedButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setIntervalButton != null : "fx:id=\"setIntervalButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setLowTriggerButton != null : "fx:id=\"setLowTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert trippedTableColumn != null : "fx:id=\"trippedTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert setHighTriggerButton != null : "fx:id=\"setHighTriggerButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert highTriggerField != null : "fx:id=\"highTriggerField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert tickerTableColumn != null : "fx:id=\"tickerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert removeItemButton != null : "fx:id=\"removeItemButton\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert priceTableColumn != null : "fx:id=\"priceTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert highTriggerTableColumn != null : "fx:id=\"highTriggerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert lowTriggerTableColumn != null : "fx:id=\"lowTriggerTableColumn\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert lowTriggerField != null : "fx:id=\"lowTriggerField\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert selectTriggerChoiceBox != null : "fx:id=\"selectTriggerChoiceBox\" was not injected: check your FXML file 'watchlist.fxml'.";
        assert watchlistErrorLabel != null : "fx:id=\"watchlistErrorLabel\" was not injected: check your FXML file 'watchlist.fxml'.";

        //endregion

        //set the values and default for the trigger choice box
        selectTriggerChoiceBox.setItems(FXCollections.observableArrayList("High Trigger","Low Trigger","Both"));
        selectTriggerChoiceBox.setValue("Both");

    }
    @Override
    protected void setup() {
        //set the watchlist as the watch equities in the portfolio
        watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));

        //region CellFactories
        //set the column values for the table
        tickerTableColumn.setCellValueFactory(new PropertyValueFactory<>("tickerSymbol"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        highTriggerTableColumn.setCellValueFactory(new PropertyValueFactory<>("highTrigger"));
        lowTriggerTableColumn.setCellValueFactory(new PropertyValueFactory<>("lowTrigger"));
        trippedTableColumn.setCellValueFactory(new PropertyValueFactory<>("triggerStatus"));
        //endregion

        //set the default for the trigger choice box
        selectTriggerChoiceBox.getSelectionModel().selectFirst();

        //add this as an observer of portfolio
        main.getMarket().addObserver(this);
    }


    /**
     * Action listener for setting the high trigger of an equity
     */
    @FXML
    void handleSetHighTrigger() {
        //capture the currently selected watchlist item
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        try{
            //attempt to parse the high trigger number
            float amount = Float.parseFloat(highTriggerField.getText());

            //set the high  trigger for the selected item
            main.getPortfolio().getWatchEquities().get(currentSelection).setHighTrigger(amount);

            //update the watchlist table
            watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
            watchlistTable.refresh();
            watchlistTable.getSelectionModel().selectFirst();
            watchlistTable.getFocusModel().focus(0);
        } catch (Exception e) {}
    }

    /**
     * Action listener for setting the low trigger of an equity
     */
    @FXML
    void handleSetLowTrigger() {
        //capture the currently selected watchlist item
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();
        try{
            //attempt to parse the low trigger number
            float amount = Float.parseFloat(lowTriggerField.getText());

            //set the low trigger for the selected item
            main.getPortfolio().getWatchEquities().get(currentSelection).setLowTrigger(amount);

            //update the watchlist table
            watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
            watchlistTable.refresh();
            watchlistTable.getSelectionModel().selectFirst();
            watchlistTable.getFocusModel().focus(0);

        } catch (Exception e) {}
    }

    /**
     * Action listener for the clear button
     */
    @FXML
    void handleClearTrigger() {
        //capture the currently selected watchlist item
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();

        //clear the low trigger if it has it
        if(selectTriggerChoiceBox.getValue().equals("Low Trigger")){
            main.getPortfolio().getWatchEquities().get(currentSelection).setLowTrigger(-1);
        }

        //clear the high trigger if it has it
        else if(selectTriggerChoiceBox.getValue().equals("High Trigger")){
            main.getPortfolio().getWatchEquities().get(currentSelection).setHighTrigger(-1);
        }

        //clear both triggers if it has both
        else if(selectTriggerChoiceBox.getValue().equals("Both")){
            main.getPortfolio().getWatchEquities().get(currentSelection).setHighTrigger(-1);
            main.getPortfolio().getWatchEquities().get(currentSelection).setLowTrigger(-1);
        }


        //update the watchlist table
        watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    /**
     * Action listener for the reset button
     */
    @FXML
    void handleResetTripped() {
        //loop through all watchlist equities in portfolio and reset the triggers
        for (MarketEquity e:main.getPortfolio().getWatchEquities()){
            e.setTriggerStatus("");
        }

        //update the watchlist table
        watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    /**
     * Action listener for the remove button
     */
    @FXML
    void handleRemoveItem() {
        //capture the currently selected object
        int currentSelection = watchlistTable.getSelectionModel().getSelectedIndex();

        //remove the item from the portfolio's list of watchlist equities
        main.getPortfolio().getWatchEquities().remove(currentSelection);

        //update the watchlist table
        watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }

    @FXML void handleUpdateInterval() {}

    /**
     * Handles an attempt to set the interval for the update service
     */
    @FXML
    void handleSetInterval() {
        try{
            //attempt to parse the interval
            float amount = Float.parseFloat(updateIntervalField.getText());

            //set the update period and restart the service
            main.getUpdateService().setPeriod(Duration.minutes(amount));
            main.getUpdateService().restart();
        } catch (Exception e) {}
    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
        transactionTab.setDisable(false);
    }

    /** Handles updating the watchlist when portfolio changes
     * @param o   the object being observed
     * @param arg the identifying arg for what is being updated
     */
    @Override
    public void update(Observable o, Object arg){
        //loop through  all watchlist equities
        for (MarketEquity e:main.getPortfolio().getWatchEquities()) {

            //check if the high trigger is currently being hit
            if(e.getValue() > e.getHighTrigger() && e.getHighTrigger()!=-1){
                e.setTriggerStatus("Is above high");
            }

            //check if the low trigger is currently being  hit
            else if(e.getValue() < e.getLowTrigger()){
                e.setTriggerStatus("Is under low");
            }
            else{
                //update the trigger status to show it is no longer being hit
                if(e.getTriggerStatus().equals("Is above high")){
                    e.setTriggerStatus("Was above high");
                }
                else if(e.getTriggerStatus().equals("Is under low")){
                    e.setTriggerStatus("Was under low");
                }
            }
        }

        //update the watchlist table
        watchlistTable.setItems(FXCollections.observableList(main.getPortfolio().getWatchEquities()));
        watchlistTable.refresh();
        watchlistTable.getSelectionModel().selectFirst();
        watchlistTable.getFocusModel().focus(0);
    }
}
