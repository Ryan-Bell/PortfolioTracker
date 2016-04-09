package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Models.MarketSimulation.MarketSimulator;
import Models.MarketSimulation.SimulationType;
import Models.MarketSimulation.StepType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private MarketSimulator marketSimulator;

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

        previousStepButton.setVisible(false);
        nextStepButton.setVisible(false);

        simulationChoiceBox.setItems(FXCollections.observableArrayList(SimulationType.BULL,SimulationType.NO_GROWTH,SimulationType.BEAR));
        simulationChoiceBox.setValue(SimulationType.BULL);
        stepChoiceBox.setItems(FXCollections.observableArrayList(StepType.YEAR,StepType.MONTH,StepType.DAY));
        stepChoiceBox.setValue(StepType.YEAR);
    }

    @FXML
    void handleRunSim(ActionEvent event) {
        previousStepButton.setVisible(false);
        nextStepButton.setVisible(false);
        marketSimulator = new MarketSimulator(main.getPortfolio().getPortfolioValue());

        try {
            float percent = Float.parseFloat((percentChangeField.getCharacters().toString()));
            int steps = Integer.parseInt(numStepsField.getCharacters().toString());
            SimulationType simulationType = simulationChoiceBox.getValue();
            StepType stepType = stepChoiceBox.getValue();

            ObservableList values =FXCollections.observableArrayList(marketSimulator.runSimulation(percent, steps, stepType, simulationType));
            simResultsList.setItems(values);

            nextStepButton.setVisible(true);
            previousStepButton.setVisible(true);

        } catch (Exception e) {
            ObservableList values = FXCollections.observableArrayList("Please enter all fields");
            simResultsList.setItems(values);

            System.out.println("!!! SimulationController: " + e.getMessage());
        }
    }

    @FXML
    void handlePreviousStep(ActionEvent event) {
        float value = marketSimulator.popMemento();
        ObservableList values = FXCollections.observableArrayList("--Back To Previous Simulation--", value);
        simResultsList.getItems().addAll(values);

        if (value == main.getPortfolio().getPortfolioValue()) {
            previousStepButton.setVisible(false);
        }
    }

    @FXML
    void handleNextStep(ActionEvent event) {
        try {
            float percent = Float.parseFloat((percentChangeField.getCharacters().toString()));
            int steps = Integer.parseInt(numStepsField.getCharacters().toString());
            SimulationType simulationType = simulationChoiceBox.getValue();
            StepType stepType = stepChoiceBox.getValue();

            ObservableList values = FXCollections.observableArrayList(marketSimulator.runSimulation(percent, steps, stepType, simulationType));
            simResultsList.getItems().addAll(values);

            previousStepButton.setVisible(true);
        } catch (Exception e) {
            System.out.println("!!! SimulationController: " + e.getMessage());
        }
    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
    }
}

