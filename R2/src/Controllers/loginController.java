package Controllers;

//region imports

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
//endregion

public class loginController implements Initializable{
    //region FXMLFields
    //declare fxml elements for the login pane
    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginButton;

    //declare fxml elements for the registration pane
    @FXML
    private TextField registerUsernameField;

    @FXML
    private PasswordField registerPasswordField;

    @FXML
    private PasswordField registerConfirmField;

    @FXML
    private Button registerButton;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        //assert that all elements of the login pane have been properly initialized
        assert loginUsernameField != null : "fx:id=\"loginUsernameField\" was not injected: check the associated fxml file";
        assert loginPasswordField != null : "fx:id=\"loginPasswordField\" was not injected: check the associated fxml file";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check the associated fxml file";

        //assert that all elements of the registration pane have been properly initialized
        assert registerUsernameField != null : "fx:id=\"registerUsernameField\" was not injected: check the associated fxml file";
        assert registerPasswordField != null : "fx:id=\"registerPasswordField\" was not injected: check the associated fxml file";
        assert registerConfirmField != null : "fx:id=\"registerConfirmField\" was not injected: check the associated fxml file";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check the associated fxml file";

        // all @FXML variables will have been injected
        System.out.println("The FXML has been initialized");
    }
}
