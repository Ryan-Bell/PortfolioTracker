package Controllers;



//region imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import Models.UserAuthentication;
import Models.Portfolio;
import Models.PortfolioIO;
//endregion

public class LoginController implements Initializable{
    //region FXMLFields
    //declare fxml elements for the login pane
    @FXML public TextField loginUsernameField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Button loginButton;
    @FXML private Label loginStatusLabel;

    //declare fxml elements for the registration pane
    @FXML private TextField registerUsernameField;
    @FXML private PasswordField registerPasswordField;
    @FXML private PasswordField registerConfirmField;
    @FXML private Button registerButton;
    @FXML private Label registerStatusLabel;
    //endregion

    private UserAuthentication authentication;
    private PortfolioIO portfolioIO;

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

        setupAuthentication();

    }

    private void setupAuthentication(){
        authentication = new UserAuthentication();
        portfolioIO = new PortfolioIO();
    }


    @FXML
    public void handleRegister() {
        Portfolio portfolio = portfolioIO.getPOFromId(registerUsernameField.getText());

        //check to make sure that the user id doesn't already exist
        if (portfolio == null){
            if (registerPasswordField.getText().equals(registerConfirmField.getText())){
                //TODO create new portfolio
                //TODO send to new portfolio

            } else {
                registerStatusLabel.setText("Password does not match");
                registerPasswordField.setText("");
                registerConfirmField.setText("");
            }
        }
        else{
            registerStatusLabel.setText("User ID already exists");
            registerUsernameField.setText("");
            registerPasswordField.setText("");
            registerConfirmField.setText("");
        }

    }

    @FXML
    public void handleLogin() {
        Portfolio portfolio = portfolioIO.getPOFromId(loginUsernameField.getText());
        //if user id locates file
        if (portfolio != null){
            //if entered password and portfolio password match
            if (authentication.checkPassword(loginPasswordField.getText(),portfolio.getPassword())){
                //TODO send to portfolio
            }
            else{
                loginStatusLabel.setText("Incorrect Password");
                loginPasswordField.setText("");
            }
        }
        else{
            loginStatusLabel.setText("User ID does not exist");
            loginUsernameField.setText("");
            loginPasswordField.setText("");
        }
    }
}
