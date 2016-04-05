package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

public class TransactionLogController extends ViewController implements Initializable{
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private DatePicker endDatePicker;
    @FXML private DatePicker startDatePicker;
    @FXML private ListView<?> transactionList;
    @FXML private Button filterButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert endDatePicker != null : "fx:id=\"endDatePicker\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert startDatePicker != null : "fx:id=\"startDatePicker\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert transactionList != null : "fx:id=\"transactionList\" was not injected: check your FXML file 'transactionlog.fxml'.";
        assert filterButton != null : "fx:id=\"filterButton\" was not injected: check your FXML file 'transactionlog.fxml'.";
        //endregion
    }

    @FXML
    void handleFilter(ActionEvent event) {

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
    }
}

