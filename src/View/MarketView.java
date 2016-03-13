package View;

import Market.Market;
import Market.MarketEquity;
import Market.MatchType;
import Market.QueryType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Ryan on 3/12/2016.
 */
public class MarketView extends View {

    ArrayList<MarketEquity> searchResults;

    Text scenetitle;
    Label searchLabel;
    TextField searchField;
    Label resultsLabel;
    TextField resultsField;
    VBox resultList;
    ScrollPane resultView;

    @Override
    public void display(Context context) {
        super.display(context);
        searchResults = new ArrayList<>();

        primaryStage.setTitle("Market");

        scenetitle = new Text("Market");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        searchLabel = new Label("Search");
        grid.add(searchLabel, 0, 1);

        searchField = new TextField();
        grid.add(searchField, 1, 1);

        resultsLabel = new Label("Results");
        grid.add(resultsLabel, 0, 2);

        resultsField = new TextField();
        grid.add(resultsField, 1, 2);

        resultView = new ScrollPane();
        grid.add(resultView,1,3);

        resultList = new VBox();





        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                setResults(context.getMarket().search(QueryType.TICKER , newValue, MatchType.CONTAINED));
            }
        });

        Scene newScene = new Scene(borderPane, 500, 475);
        primaryStage.setScene(newScene);
    }

    private void setResults(ArrayList<MarketEquity> results){
        searchResults = results;
        resultsField.setText("");
        String text = "";
        //resultList.getChildren().removeAll(resultList);
        resultList = new VBox();
        System.out.println("Did the children die?");
        for (MarketEquity e : results) {
            text += e.getName();
            Label equity = new Label(e.getName());
            Button buy = new Button("Buy");
            BorderPane equityDisplay = new BorderPane();
            equityDisplay.setLeft(equity);
            equityDisplay.setRight(buy);

            resultList.getChildren().add(equityDisplay);
        }
        resultsField.setText(text);
        resultView.setContent(resultList);
    }
}
