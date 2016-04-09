package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import Models.Transaction.Transaction;
import Models.UndoRedo.UndoRedoFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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

        undoRedoFunctions = UndoRedoFunctions.getInstance();

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

    @FXML
    void handleUndo(ActionEvent event) {
        resetErrorLabel();

        try {
            undoRedoFunctions.undo();

            if (!undoRedoFunctions.isRedoEmpty()) {
                redoButton.setVisible(true);
            }

            if (undoRedoFunctions.isUndoEmpty()) {
                undoButton.setVisible(false);
            }

        } catch (NoSuchElementException e) {
            showErrorLabel("No more commands to undo");
        }
    }

    @FXML
    void handleRedo(ActionEvent event) {
        resetErrorLabel();

        try {
            undoRedoFunctions.redo();

            if (!undoRedoFunctions.isUndoEmpty()) {
                undoButton.setVisible(true);
            }

            if (undoRedoFunctions.isRedoEmpty()) {
                redoButton.setVisible(false);
            }

        } catch (NoSuchElementException e) {
            showErrorLabel("No more commands to redo");
        }
    }

    @FXML
    void handleFilter(ActionEvent event) {
        resetErrorLabel();

        //how to acquire the date from the date picker
        LocalDate now = startDatePicker.getValue();
        System.out.println(now);

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
    }

    void showErrorLabel(String text) {
        transactionLogErrorLabel.setText(text);
    }

    void resetErrorLabel() {
        transactionLogErrorLabel.setText("");
    }

    @Override
    public void update(Observable o, Object arg){
        System.out.println("updating this");
        ObservableList transactions =FXCollections.observableArrayList(main.getPortfolio().getTransactionLog());
        transactionList.setItems(transactions);


        System.out.println("Length: "+main.getPortfolio().getTransactionLog().size());
        for (Transaction t:main.getPortfolio().getTransactionLog()
             ) {
            System.out.println(t.toString());
        }
    }
}

