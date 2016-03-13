package View;

import Portfolio.CashAccount;
import Portfolio.HoldingEquity;
import Portfolio.UserAuthentication;
import Transaction.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.DecimalFormat;

import java.util.List;
import java.util.Observable;

/**
 * Created by user on 3/10/2016.
 */
public class PortfolioView extends View {

    @Override
    public void display(Context context){
        //Automatically calls the logic for checking if a preliminary scene has been created
        super.display(context);


        context.getPortfolio().addObserver(this);


        primaryStage.setTitle("Portfolio");

        Text scenetitle = new Text("Portfolio");
        scenetitle.setId("scenetitle");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        dynamicContent.add(scenetitle);

        Button simView = new Button("Simulation View");
        grid.add(simView, 0, 4);

        simView.setOnAction((event -> {
            context.setView(context.getSimulationView());

        }));


        VBox cashAccountsSection = new VBox();
        //Create Cash Accounts Table
        TableView table = new TableView();
        TableColumn accountName = new TableColumn("Cash Account Name");
        accountName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        TableColumn accountBalance = new TableColumn("Balance");
        accountBalance.setCellValueFactory(
                new PropertyValueFactory<>("balance")
        );
        TableColumn accountDate = new TableColumn("Date Added");
        accountDate.setCellValueFactory(
                new PropertyValueFactory<>("dateAdded")
        );

        table.setItems(FXCollections.observableList(context.getPortfolio().getCashAccounts()));
        table.getColumns().addAll(accountName, accountBalance, accountDate);
        table.getSelectionModel().clearSelection();
        table.setMaxHeight(100);
        table.setId("cashAccountsTable");
        dynamicContent.add(table);

        //Create Fields for new Cash Account
        TextField newCashName = new TextField();
        newCashName.setPromptText("Name");
        newCashName.setPrefWidth(100);

        TextField newCashBalance = new TextField();
        newCashBalance.setPromptText("Balance");
        newCashBalance.setPrefWidth(100);

        Button addCashAccount = new Button ("Add Cash Account");
        addCashAccount.setOnAction((event -> {
            if(newCashName != null) {
                try {
                    context.getPortfolio().addCashAccount(newCashName.getText(), Float.parseFloat((newCashBalance).getText()));
                } catch (NumberFormatException e) {
                    System.out.println("caught exceptions");
                }
            }
        }));
        addCashAccount.setMinWidth(100);
        HBox labelBox = new HBox();
        labelBox.getChildren().addAll(newCashName, newCashBalance, addCashAccount);

        cashAccountsSection.getChildren().addAll(table, labelBox);

        grid.add(cashAccountsSection, 0, 6);

        //button to go to market view
        Button markView = new Button("Market View");
        grid.add(markView, 0, 5);

        /*markView.setOnAction((event -> {
            context.setView(context.getMarketView());
        }));*/



        Scene newScene = new Scene(borderPane, 500, 475);

        primaryStage.setScene(newScene);





    }

    @Override
    public void update(Observable o, Object arg){
        for(int i = 0; i< dynamicContent.size(); i++){
            switch(dynamicContent.get(i).getId()){
                case "scenetitle":
                    Text sceneTitle = (Text)dynamicContent.get(i);
                   // sceneTitle.setText("Portfolio. Display test: "+context.getPortfolio().getTest());
                    break;
                case "cashAccountsTable":
                    TableView cashAccountsTable = (TableView)dynamicContent.get(i);
                    cashAccountsTable.setItems(FXCollections.observableList(context.getPortfolio().getCashAccounts()));
                    break;
            }
        }

    }

}
