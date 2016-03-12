package View;

import MarketSimulation.Simulation;
import MarketSimulation.SimulationType;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The view for the simulations.
 */
public class SimulationView extends View{

    @Override
    public void display(Context context) {
        super.display(context);

        primaryStage.setTitle("Simulation");

//        Text scenetitle = new Text("Simulation");
//        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//        grid.add(scenetitle, 0, 0, 2, 1);
        ChoiceBox strategyCB =  new ChoiceBox();
        strategyCB.setValue("Select Simulation Type");
        strategyCB.setItems(FXCollections.observableArrayList(
                "Select Simulation Type",
                new Separator(), SimulationType.BEAR, SimulationType.BULL, SimulationType.NO_GROWTH
        ));
        grid.add(strategyCB, 0, 0); //TODO: add to correct place

        ChoiceBox stepCB =  new ChoiceBox();
        stepCB.setValue("Select Step Type");
        stepCB.setItems(FXCollections.observableArrayList(
                "Select Step Type",
                new Separator(), Simulation.StepTypes.DAY, Simulation.StepTypes.MONTH, Simulation.StepTypes.YEAR
        ));
        grid.add(stepCB, 0, 1); //TODO: add to correct place

        TextField numberOfStepsTF =  new TextField();
        numberOfStepsTF.setPromptText("Enter Number of Steps");
        grid.add(numberOfStepsTF, 0, 2); //TODO: add to correct place

        Button runSimBtn = new Button("Run Simulation");
        runSimBtn.setOnAction((event -> {
            SimulationType strategy = (SimulationType)strategyCB.getValue();
            Simulation.StepTypes stepType = (Simulation.StepTypes)stepCB.getValue();
            int steps = Integer.parseInt(numberOfStepsTF.getText());

            System.out.println(strategy.toString() + " " + stepType.toString() + " " + steps);

        }));
        grid.add(runSimBtn, 0, 3); //TODO


        Scene newScene = new Scene(borderPane, 500, 475);
        primaryStage.setScene(newScene);
    }
}
