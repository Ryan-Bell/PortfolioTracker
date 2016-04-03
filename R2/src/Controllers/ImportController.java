/**
 * Sample Skeleton for 'import.fxml' Controller Class
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ImportController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="importConflictsList"
    private ListView<?> importConflictsList; // Value injected by FXMLLoader

    @FXML // fx:id="importResultsList"
    private ListView<?> importResultsList; // Value injected by FXMLLoader

    @FXML // fx:id="fileNameField"
    private TextField fileNameField; // Value injected by FXMLLoader

    @FXML // fx:id="importButton"
    private Button importButton; // Value injected by FXMLLoader

    @FXML
    void handleImport(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert importConflictsList != null : "fx:id=\"importConflictsList\" was not injected: check your FXML file 'import.fxml'.";
        assert importResultsList != null : "fx:id=\"importResultsList\" was not injected: check your FXML file 'import.fxml'.";
        assert fileNameField != null : "fx:id=\"fileNameField\" was not injected: check your FXML file 'import.fxml'.";
        assert importButton != null : "fx:id=\"importButton\" was not injected: check your FXML file 'import.fxml'.";

    }
}

