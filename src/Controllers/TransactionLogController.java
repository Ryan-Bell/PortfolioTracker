package Controllers;

import Models.Transaction.Transaction;
import Models.UndoRedo.UndoRedo;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TransactionLogController extends ViewController implements Initializable, Observer{
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private DatePicker endDatePicker;
    @FXML private DatePicker startDatePicker;
    @FXML private ListView<?> transactionList;
    @FXML private Button filterButton;
    @FXML private Label transactionLogErrorLabel;
    @FXML private Button undoButton;
    @FXML private Button redoButton;
    //endregion
    UndoRedoFunctions undoRedoFunctions;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert endDatePicker != null : "fx:id=\"endDatePicker\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert startDatePicker != null : "fx:id=\"startDatePicker\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert transactionList != null : "fx:id=\"transactionList\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert filterButton != null : "fx:id=\"filterButton\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert transactionLogErrorLabel != null : "fx:id=\"transactionLogErrorLabel\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert redoButton != null : "fx:id=\"redoButton\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert undoButton != null : "fx:id=\"redoButton\" was not injected: check your FXML file 'transactionlog.fxml'.";
        //endregion

        //set the instance of the undoredo functions object
        undoRedoFunctions = UndoRedoFunctions.getInstance();

        //update the undo and redo buttons based on the stacks
        if (undoRedoFunctions.isUndoEmpty()) {
            undoButton.setVisible(false);
        }
        if (undoRedoFunctions.isRedoEmpty()) {
            redoButton.setVisible(false);
        }
    }
    @Override
    protected void setup() {
        ObservableList transactions =FXCollections.observableArrayList(main.getPortfolio().getTransactionLog());
        transactionList.setItems(transactions);

        main.getPortfolio().addObserver(this);
    }


    /**
     * Action Listener for the undo button
     * pops a command off of the stack and calls its unexecute
     */
    @FXML
    void handleUndo() {
        //reset the error label for this view
        resetErrorLabel();

        try {
            //undo the last executed command and remove its associated log item
            UndoRedo undoneObj = undoRedoFunctions.undo();
            main.getPortfolio().removeTransaction(undoneObj);

            //update the undo and redo buttons based on if there are anymore commands to undo/redo
            if (!undoRedoFunctions.isRedoEmpty()) {
                redoButton.setVisible(true);
            }

            if (undoRedoFunctions.isUndoEmpty()) {
                undoButton.setVisible(false);
            }

        } catch (NoSuchElementException e) {
            //notify the user that they can't undo anything else
            showErrorLabel("No more commands to undo");
        }
    }

    /**
     * Action Listener for the redo button
     * pops a command off of the stack and calls its execute
     */
    @FXML
    void handleRedo() {
        //reset the error label for this view
        resetErrorLabel();

        try {
            //call redo on the last undone object and add it to the transaction log
            UndoRedo redoneObj = undoRedoFunctions.redo();
            main.getPortfolio().addTransaction(redoneObj);

            //update the undo and redo buttons based on the undo / redo items in the stacks
            if (!undoRedoFunctions.isUndoEmpty()) {
                undoButton.setVisible(true);
            }

            if (undoRedoFunctions.isRedoEmpty()) {
                redoButton.setVisible(false);
            }

        } catch (NoSuchElementException e) {
            //notify the user that there aren't any more actions to redo
            showErrorLabel("No more commands to redo");
        }
    }

    /**
     * Action listener for the filtering of log items based on date
     */
    @FXML
    void handleFilter() {
        //reset the error label for this view
        resetErrorLabel();

        //how to acquire the date from the date picker
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate == null || endDate == null) {
            showErrorLabel("Please pick a date");
            return;
        }


        //list to store all applicable log items
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();

        //loop through all log items and  add them to the list if they are within the date range
        for (Transaction transaction:main.getPortfolio().getTransactionLog()) {
            LocalDate transactionDate = LocalDate.of(transaction.getDate().getYear(), transaction.getDate().getMonth(), transaction.getDate().getDayOfMonth());
            if((transactionDate.isAfter(startDate) || transactionDate.isEqual(startDate)) && (transactionDate.isBefore(endDate) || transactionDate.isEqual(endDate))){
                filteredTransactions.add(transaction);
            }
        }

        //set the transaction list as the filtered list of transactions
        ObservableList transactions = FXCollections.observableArrayList(filteredTransactions);
        transactionList.setItems(transactions);

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
    }

    /** Sets the error label for this view to specified text
     * @param text text to set the error label to
     */
    void showErrorLabel(String text) {
        transactionLogErrorLabel.setText(text);
    }

    /**
     * clears the error label for this view
     */
    void resetErrorLabel() {
        transactionLogErrorLabel.setText("");
    }

    @Override
    public void update(Observable o, Object arg){
        ObservableList transactions = FXCollections.observableArrayList(main.getPortfolio().getTransactionLog());
        transactionList.setItems(transactions);

    }
}

