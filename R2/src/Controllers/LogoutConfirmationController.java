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
    private static Stage messageBoxStage;
    private static Main main;

    public void showMessageBox(Stage parentStage, Main main) {
        this.myParent = parentStage;
        this.main = main;

        try {
            messageBoxStage = new Stage();
            AnchorPane page = FXMLLoader.load(LogoutConfirmationController.class.getResource("../Views/LogoutConfirmation.fxml"));
            Scene scene = new Scene(page);
            messageBoxStage.setScene(scene);
            messageBoxStage.setTitle("Confirm Logout");
            messageBoxStage.initOwner(this.myParent);
            messageBoxStage.initModality(Modality.WINDOW_MODAL);
            messageBoxStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle arg1) {}

    @FXML void handleYes(ActionEvent event) {
        try {
            PortfolioIO.serialize(main.getPortfolio(), "./portfolios/" + main.getPortfolio().getId() + ".port");
        } catch (IOException e){
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, e);
        }
        main.showLogin();
        messageBoxStage.close();
    }

    @FXML void handleNo(ActionEvent event) {
        main.showLogin();
        messageBoxStage.close();
    }
}