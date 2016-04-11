package Controllers;

import Models.MarketSimulation.MarketSimulator;
import Models.MarketSimulation.SimulationType;
import Models.MarketSimulation.StepType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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

    //instance of the market simulation object
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

        //hide the next and previous buttons until a simulation is run
        previousStepButton.setVisible(false);
        nextStepButton.setVisible(false);

        //set the choice box options and the default selection
        simulationChoiceBox.setItems(FXCollections.observableArrayList(SimulationType.BULL,SimulationType.NO_GROWTH,SimulationType.BEAR));
        simulationChoiceBox.setValue(SimulationType.BULL);
        stepChoiceBox.setItems(FXCollections.observableArrayList(StepType.YEAR,StepType.MONTH,StepType.DAY));
        stepChoiceBox.setValue(StepType.YEAR);
    }

    /**
     *
     */
    @FXML
    void handleRunSim() {
        //clears the error label for this view
        resetErrorLabel();

        //reset the buttons
        previousStepButton.setVisible(false);
        nextStepButton.setVisible(false);

        marketSimulator = new MarketSimulator(main.getPortfolio().getPortfolioValue());

        try {
            //attempt to parse the steps and the percentagge fields
            float percent = Float.parseFloat((percentChangeField.getCharacters().toString()));
            int steps = Integer.parseInt(numStepsField.getCharacters().toString());

            //get the enum values from the choice boxes
            SimulationType simulationType = simulationChoiceBox.getValue();
            StepType stepType = stepChoiceBox.getValue();

            //set the simulation output as the output returned from running the simulation
            ObservableList values = FXCollections.observableArrayList(marketSimulator.runSimulation(percent, steps, stepType, simulationType));
            simResultsList.setItems(values);

            //show the step buttons
            nextStepButton.setVisible(true);
            previousStepButton.setVisible(true);

        } catch (Exception e) {
            //notify the user that there was an issue
            showErrorLabel("Please fill in all fields");
        }
    }

    /**
     * Action Listener for the previous step button
     */
    @FXML
    void handlePreviousStep() {
        //clears the error label for this view
        resetErrorLabel();
        float value = marketSimulator.popMemento();

        //capture the simulation output list
        ObservableList values = FXCollections.observableArrayList(value);

        //save the length of the list
        int length = simResultsList.getItems().size() - 1;
        int i;

        //remove the last step from the list
        for (i = length; i > 0; i--) {
            Float removeAmnt = (Float)simResultsList.getItems().get(i);
            if (value == removeAmnt) {
                break;
            }
            simResultsList.getItems().remove(simResultsList.getItems().get(i));
        }

        //hide the previous button if there is no previous step
        if (value == main.getPortfolio().getPortfolioValue()) {
            previousStepButton.setVisible(false);
        }
    }

    /**
     * Action Listener for the next step button
     */
    @FXML
    void handleNextStep() {
        //clears the error label for this view
        resetErrorLabel();

        try {
            //attempt to parse the percent and step fields into nnumbers
            float percent = Float.parseFloat((percentChangeField.getCharacters().toString()));
            int steps = Integer.parseInt(numStepsField.getCharacters().toString());

            //save teh enum selections from the choice boxes
            SimulationType simulationType = simulationChoiceBox.getValue();
            StepType stepType = stepChoiceBox.getValue();

            //set the list to the output of running the simulation
            ObservableList values = FXCollections.observableArrayList(marketSimulator.runSimulation(percent, steps, stepType, simulationType));
            simResultsList.getItems().addAll(values);

            //allow the user to press the previous button
            previousStepButton.setVisible(true);
        } catch (Exception e) {
            //notify the user that there was an error
            showErrorLabel("Please fill in all fields");
        }
    }

    @FXML
    void handleme(){
        portfolioTab.setDisable(false);
        importTab.setDisable(false);
        marketTab.setDisable(false);
    }

    /** sets the text in the error label for this view
     * @param text the text to set as the error
     */
    void showErrorLabel(String text) {
        simulationErrorLabel.setText(text);
    }

    /**
     * empties the error lable for this view
     */
    void resetErrorLabel() {
        simulationErrorLabel.setText("");
    }
}

