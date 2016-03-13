package View;

import MarketSimulation.MarketSimulator;
import MarketSimulation.Simulation;
import MarketSimulation.SimulationType;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sun.font.TextLabel;

import java.util.ArrayList;

/**
 * The view for the simulations.
 */
public class SimulationView extends View{

    @Override
    public void display(Context context) {
        super.display(context);

        primaryStage.setTitle("Simulation");
        primaryStage.setWidth(500);
        primaryStage.setHeight(475);

//        Text scenetitle = new Text("Simulation");
//        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//        grid.add(scenetitle, 0, 0, 2, 1);
        ChoiceBox strategyCB =  new ChoiceBox();
        strategyCB.setValue("Select Simulation Type");
        strategyCB.setItems(FXCollections.observableArrayList(
                "Select Simulation Type",
                new Separator(), SimulationType.BEAR, SimulationType.BULL, SimulationType.NO_GROWTH
        ));
        grid.add(strategyCB, 0, 0);

        ChoiceBox stepCB =  new ChoiceBox();
        stepCB.setValue("Select Step Type");
        stepCB.setItems(FXCollections.observableArrayList(
                "Select Step Type",
                new Separator(), Simulation.StepTypes.DAY, Simulation.StepTypes.MONTH, Simulation.StepTypes.YEAR
        ));
        grid.add(stepCB, 0, 1);

        TextField percentageTF =  new TextField();
        percentageTF.setPromptText("Enter Percentage");
        grid.add(percentageTF, 0, 3);

        TextField numberOfStepsTF =  new TextField();
        numberOfStepsTF.setPromptText("Enter Number of Steps");
        grid.add(numberOfStepsTF, 0, 4);

        Label resultLable = new Label("Results:");
        resultLable.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        ScrollPane resultSP = new ScrollPane();

        VBox resultSideVB = new VBox();
        resultSideVB.getChildren().add(resultLable);
        resultSideVB.getChildren().add(resultSP);
        resultSideVB.setVgrow(resultSP, Priority.ALWAYS);

        borderPane.setRight(resultSideVB);

        Button runSimBtn = new Button("Run New Simulation");
        runSimBtn.setOnAction((event -> {
            SimulationType strategy = (SimulationType)strategyCB.getValue();
            Simulation.StepTypes stepType = (Simulation.StepTypes)stepCB.getValue();
            float percent = Float.parseFloat(percentageTF.getText()) / 100;
            int steps = Integer.parseInt(numberOfStepsTF.getText());

            //create a marketSimulator
            MarketSimulator marketSimulator = new MarketSimulator(context.getPortfolio().getPortfolioValue());
            ArrayList<Float> values = marketSimulator.runSimulation(percent, steps, stepType, strategy);

            //add each value to the valueVB VBox
            VBox valueVB = new VBox();
            for (int i = 0; i < values.size(); i++) {
                Text value = new Text("Value at step " + (i + 1) + " = $" + values.get(i));
                valueVB.getChildren().add(value);
            }
            resultSP.setContent(valueVB);

            //create a next simulation button
            Button nextSimButton = new Button("Next Simulation");
            nextSimButton.setOnAction((nextEvent -> {
                SimulationType nextStrategy = (SimulationType)strategyCB.getValue();
                Simulation.StepTypes nextStepType = (Simulation.StepTypes)stepCB.getValue();
                float nextPercent = Float.parseFloat(percentageTF.getText()) / 100;
                int nextSteps = Integer.parseInt(numberOfStepsTF.getText());


                ArrayList<Float> nextValues = marketSimulator.runSimulation(nextPercent, nextSteps, nextStepType, nextStrategy);
                valueVB.getChildren().add(new Text("--- New " + nextStrategy + " Simulation ---"));
                for (int i = 0; i < nextValues.size(); i++) {
                    Text value = new Text("Value at step " + (i + 1) + " = $" + nextValues.get(i));
                    valueVB.getChildren().add(value);
                }

                //create a previous simulation button
                Button previousSimButton = new Button("Previous Simulation");
                previousSimButton.setOnAction((prevEvent -> {
                    float value = marketSimulator.popMemento();
                    valueVB.getChildren().add(new Text("--- Back to Previous Simulation ---"));
                    valueVB.getChildren().add(new Text("Value = " + value));
                }));
                grid.add(previousSimButton, 0, 7);
            }));
            grid.add(nextSimButton, 0, 6);

        }));
        grid.add(runSimBtn, 0, 5);

        primaryStage.setScene(scene);
    }
}
