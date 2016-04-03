/**
 * Sample Skeleton for 'simulation.fxml' Controller Class
 */


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

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="numStepsField"
    private TextField numStepsField; // Value injected by FXMLLoader

    @FXML // fx:id="stepType"
    private MenuButton stepType; // Value injected by FXMLLoader

    @FXML // fx:id="runSimButton"
    private Button runSimButton; // Value injected by FXMLLoader

    @FXML // fx:id="simulationType"
    private MenuButton simulationType; // Value injected by FXMLLoader

    @FXML // fx:id="percentChangeField"
    private TextField percentChangeField; // Value injected by FXMLLoader

    @FXML // fx:id="simResultsList"
    private ListView<?> simResultsList; // Value injected by FXMLLoader

    @FXML
    void handleRunSim(ActionEvent event) {

    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert numStepsField != null : "fx:id=\"numStepsField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert stepType != null : "fx:id=\"stepType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert runSimButton != null : "fx:id=\"runSimButton\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simulationType != null : "fx:id=\"simulationType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert percentChangeField != null : "fx:id=\"percentChangeField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simResultsList != null : "fx:id=\"simResultsList\" was not injected: check your FXML file 'simulation.fxml'.";

    }
}

