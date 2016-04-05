package Controllers;

import Models.FileIO.PortfolioIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogoutConfirmationController implements Initializable {

    //region FXMLFields
    @FXML private Button btnNo;
    @FXML private Button btnYes;
    //endregion

    private Stage myParent;

    //the stage and reference to main are static to account for going
    // out of scope in the containing method call
    private static Stage messageBoxStage;
    private static Main main;


    /**Sets up and displays a modal asking the user if they want to save
     * @param parentStage the stage to attach the modal to
     * @param main reference to main so the portfolio can be saved and the login screen can be returned to
     */
    public void showMessageBox(Stage parentStage, Main main) {
        //save static references
        this.myParent = parentStage;
        this.main = main;

        try {
            //create the scene by loading it in without the extended loader
            messageBoxStage = new Stage();
            AnchorPane page = FXMLLoader.load(LogoutConfirmationController.class.getResource("../Views/LogoutConfirmation.fxml"));
            Scene scene = new Scene(page);
            messageBoxStage.setScene(scene);
            messageBoxStage.setTitle("Confirm Logout");
            messageBoxStage.initOwner(this.myParent);

            //show as a modal so it appears above the other and is in proper focus
            messageBoxStage.initModality(Modality.WINDOW_MODAL);
            messageBoxStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle arg1) {}

    /**Action listener for the yes button
     * @param event the action
     */
    @FXML void handleYes(ActionEvent event) {
        //attempt to save the portfolio
        try {
            PortfolioIO.serialize(main.getPortfolio(), "./portfolios/" + main.getPortfolio().getId() + ".port");
        } catch (IOException e){
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }

        //show the login screen and close this modal
        main.showLogin();
        messageBoxStage.close();
    }

    /**Action listener for the no button
     * @param event the action
     */
    @FXML void handleNo(ActionEvent event) {
        //show the login screen and close this modal
        main.showLogin();
        messageBoxStage.close();
    }
}