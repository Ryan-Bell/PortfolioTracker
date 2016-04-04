/**
 * Sample Skeleton for 'container.fxml' Controller Class
 */

package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ContainerController extends ViewController implements Initializable{
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button refreshButton;
    @FXML private Button logoutButton;
    @FXML private Button saveButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'container.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'container.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'container.fxml'.";
        //endregion
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        
    }

    @FXML
    void handleSave(ActionEvent event) {

    }

    @FXML
    void handleLogout(ActionEvent event) {

    }

}

