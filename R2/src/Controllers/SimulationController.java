package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Models.MarketSimulation.SimulationType;
import Models.MarketSimulation.StepType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class SimulationController extends ViewController implements Initializable{
    //region FXMLFields
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField numStepsField;
    @FXML private Button runSimButton;
    @FXML private TextField percentChangeField;
    @FXML private ListView<?> simResultsList;
    @FXML private ChoiceBox<StepType> stepChoiceBox;
    @FXML private ChoiceBox<SimulationType> simulationChoiceBox;
    @FXML private Button nextStepButton;
    @FXML private Button previousStepButton;
    @FXML private Label simulationErrorLabel;
    //endregion

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //region Asserts
        assert numStepsField != null : "fx:id=\"numStepsField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert stepChoiceBox != null : "fx:id=\"stepType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert runSimButton != null : "fx:id=\"runSimButton\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simulationChoiceBox != null : "fx:id=\"simulationType\" was not injected: check your FXML file 'simulation.fxml'.";
        assert percentChangeField != null : "fx:id=\"percentChangeField\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simResultsList != null : "fx:id=\"simResultsList\" was not injected: check your FXML file 'simulation.fxml'.";
        assert nextStepButton != null : "fx:id=\"nextStepButton\" was not injected: check your FXML file 'simulation.fxml'.";
        assert previousStepButton != null : "fx:id=\"nextStepButton\" was not injected: check your FXML file 'simulation.fxml'.";
        assert simulationErrorLabel != null : "fx:id=\"simulationErrorLabel\" was not injected: check your FXML file 'simulation.fxml'.";
        //endregion

        simulationChoiceBox.setItems(FXCollections.observableArrayList(SimulationType.BULL,SimulationType.NO_GROWTH,SimulationType.BEAR));
        simulationChoiceBox.setValue(SimulationType.BEAR);
        stepChoiceBox.setItems(FXCollections.observableArrayList(StepType.YEAR,StepType.MONTH,StepType.DAY));
        stepChoiceBox.setValue(StepType.MONTH);

    }

    @FXML
    void handleRunSim(ActionEvent event) {

    }

    @FXML
    void handlePreviousStep(ActionEvent event) {

    }

    @FXML
    void handleNextStep(ActionEvent event) {

    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
    }
}

