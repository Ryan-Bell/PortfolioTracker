package View;

import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Brian on 3/12/2016.
 */
public class SimulationView extends View{

    @Override
    public void display(Context context) {
        super.display(context);

        primaryStage.setTitle("Simulation");

        Text scenetitle = new Text("Simulation");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Scene newScene = new Scene(grid, 500, 475);
        primaryStage.setScene(newScene);
    }
}
