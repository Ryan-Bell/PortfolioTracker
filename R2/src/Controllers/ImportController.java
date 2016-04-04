/**
 * Sample Skeleton for 'import.fxml' Controller Class
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ImportController extends ViewController implements Initializable {
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ListView<?> importConflictsList;
    @FXML private ListView<?> importResultsList;
    @FXML private TextField fileNameField;
    @FXML private Button importButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert importConflictsList != null : "fx:id=\"importConflictsList\" was not injected: check your FXML file 'import.fxml'.";
        assert importResultsList != null : "fx:id=\"importResultsList\" was not injected: check your FXML file 'import.fxml'.";
        assert fileNameField != null : "fx:id=\"fileNameField\" was not injected: check your FXML file 'import.fxml'.";
        assert importButton != null : "fx:id=\"importButton\" was not injected: check your FXML file 'import.fxml'.";
        //endregion
    }

    @FXML
    void handleImport(ActionEvent event) {

    }
}

