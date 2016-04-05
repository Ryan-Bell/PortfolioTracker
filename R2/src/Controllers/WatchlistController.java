package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Meg on 4/3/2016.
 */
public class WatchlistController extends ViewController implements Initializable {
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
        simulationTab.setDisable(false);
        transactionTab.setDisable(false);
    }
}
