package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class SimulationController extends ViewController implements Initializable{
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField numStepsField;
    @FXML private MenuButton stepType;
    @FXML private Button runSimButton;
    @FXML private MenuButton simulationType;
    @FXML private TextField percentChangeField;
    @FXML private ListView<?> simResultsList;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert numStepsField != null : "fx:id=\"numStepsField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert stepType != null : "fx:id=\"stepType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert runSimButton != null : "fx:id=\"runSimButton\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simulationType != null : "fx:id=\"simulationType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert percentChangeField != null : "fx:id=\"percentChangeField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simResultsList != null : "fx:id=\"simResultsList\" was not injected: check your FXML file 'simulation.fxml'.";
        //endregion
    }

    @FXML
    void handleRunSim(ActionEvent event) {

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
    }
}

