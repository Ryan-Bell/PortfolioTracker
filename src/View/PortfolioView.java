package View;

import Portfolio.CashAccount;
import Portfolio.HoldingEquity;
import Portfolio.UserAuthentication;
import Transaction.DepositTransaction;
import Transaction.SellTransaction;
import Transaction.Transaction.*;
import Transaction.TransferTransaction;
import Transaction.WithdrawTransaction;
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


        grid.add(displayCashAccounts(), 0, 6);
        grid.add(displayEquities(), 0, 7);
        grid.add(displayTransactionLog(), 0, 8);

        //button to go to market view
        Button markView = new Button("Market View");
        grid.add(markView, 0, 5);

        markView.setOnAction((event -> {
            context.setView(context.getMarketView());
        }));



        Scene newScene = new Scene(borderPane, 700, 700);
        primaryStage.setScene(newScene);
    }

    private VBox displayTransactionLog(){
        VBox transactionsSection = new VBox();
        transactionsSection.setSpacing(5);
        transactionsSection.setPadding(new Insets(10, 10, 10, 10));


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("transactionsLog");

        Label tableLabel = new Label("Transaction Log");
        tableLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        VBox transactionList = new VBox();
        for(int i = 0; i < context.getPortfolio().getTransactionLog().size(); i++){
            Text transaction = new Text(context.getPortfolio().getTransactionLog().get(i).toString());
            transactionList.getChildren().add(transaction);
        }
        scrollPane.setContent(transactionList);

        transactionsSection.getChildren().addAll(tableLabel, scrollPane);
        return transactionsSection;
    }

    private HBox displayEquities(){
        HBox equitiesSection = new HBox();
        equitiesSection.setSpacing(5);
        equitiesSection.setPadding(new Insets(10, 10, 10, 10));

        VBox tableContainer = new VBox();

        //Equities Table
        Label tableLabel = new Label("My Equities");
        tableLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        TableView table = new TableView();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(150);
        table.setId("equitiesTable");
        dynamicContent.add(table);

        //Create property columns
        TableColumn equityName = new TableColumn("Equity Name");
        equityName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        TableColumn equityValue = new TableColumn("Value");
        equityValue.setCellValueFactory(
                new PropertyValueFactory<>("value")
        );
        TableColumn equityDate = new TableColumn("Date Purchased");
        equityDate.setCellValueFactory(
                new PropertyValueFactory<>("datePurchased")
        );
        TableColumn numShares = new TableColumn("Num of Shares");
        equityDate.setCellValueFactory(
                new PropertyValueFactory<>("numShares")
        );
        TableColumn priceShare = new TableColumn("Price per Share");
        equityDate.setCellValueFactory(
                new PropertyValueFactory<>("pricePerShare")
        );
        table.getColumns().addAll(equityName, equityValue, equityDate, numShares, priceShare);

        //Fill table using a ObservableList copy of portfolio's equities list
        table.setItems(FXCollections.observableList(context.getPortfolio().getHoldingEquities()));

        //Focus and select first value
        table.requestFocus();
        table.getSelectionModel().selectFirst();
        table.getFocusModel().focus(0);

        tableContainer.getChildren().addAll(tableLabel, table);

        //Create a side bar for completing transactions
        Label sideLabel = new Label("Transactions");
        sideLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        VBox sideBar = new VBox();

        //Sell Equities and give money to cash account
        Button sellBtn = new Button("Sell");
        TextField sellAmt = new TextField();
        sellAmt.setPromptText("# Shares to Sell");
        TextField depositName = new TextField();
        depositName.setPromptText("Deposit Target Name");
        sellBtn.setOnAction((event -> {
            //Get selected row and find row associated with user input for deposit target
            int depositTargetIndex = -1;
            for(int i = 0; i < context.getPortfolio().getCashAccounts().size(); i++){
                if(context.getPortfolio().getCashAccounts().get(i).getName() == depositName.getText()){
                    depositTargetIndex = i;
                }
            }
            int currentSelection = table.getSelectionModel().getSelectedIndex();
            if(currentSelection >=0 && depositTargetIndex >=0) {
                try {
                    int amount = Integer.parseInt(sellAmt.getText());
                    HoldingEquity sellTarget = context.getPortfolio().getHoldingEquities().get(currentSelection);
                    CashAccount depositTarget = context.getPortfolio().getCashAccounts().get(depositTargetIndex);
                    context.getPortfolio().getTransactionLog().add(new SellTransaction(sellTarget,amount,context.getPortfolio(), depositTarget));

                } catch (NumberFormatException e) {
                }
            }
        }));

        sideBar.getChildren().addAll(sideLabel, sellBtn, sellAmt, depositName);

        equitiesSection.getChildren().addAll(tableContainer, sideBar);
        return equitiesSection;
    }

    private HBox displayCashAccounts(){
        HBox cashAccountsSection = new HBox();
        cashAccountsSection.setSpacing(5);
        cashAccountsSection.setPadding(new Insets(10, 10, 10, 10));

        VBox tableContainer = new VBox();

        //Cash Accounts Table
        Label tableLabel = new Label("Cash Accounts Table");
        tableLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        TableView table = new TableView();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(150);
        table.setId("cashAccountsTable");
        dynamicContent.add(table);

        //Create property columns
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
        table.getColumns().addAll(accountName, accountBalance, accountDate);

        //Fill table using a ObservableList copy of portfolio's cash accounts list
        table.setItems(FXCollections.observableList(context.getPortfolio().getCashAccounts()));

        //Focus and select first value
        table.requestFocus();
        table.getSelectionModel().selectFirst();
        table.getFocusModel().focus(0);

        //Create Button/fields for adding a new Cash Account
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

                }
            }
        }));
        addCashAccount.setMinWidth(100);
        HBox labelBox = new HBox();
        labelBox.getChildren().addAll(newCashName, newCashBalance, addCashAccount);

        tableContainer.getChildren().addAll(tableLabel, table, labelBox);

        //Create a side bar for completing transactions
        Label sideLabel = new Label("Transactions");
        sideLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));

        VBox sideBar = new VBox();

        //Withdraw out of a cash account
        Button withdrawBtn = new Button("Withdraw");
        TextField withdrawAmt = new TextField();
        withdrawAmt.setPromptText("Withdraw Amt");
        withdrawBtn.setOnAction((event -> {
            //Get selected row
            int currentSelection = table.getSelectionModel().getSelectedIndex();
            if(currentSelection >=0) {
                try {
                    float amount = Float.parseFloat(withdrawAmt.getText());
                    CashAccount currentAccount = context.getPortfolio().getCashAccounts().get(currentSelection);
                    context.getPortfolio().getTransactionLog().add(new Transaction.WithdrawTransaction(currentAccount, amount));

                } catch (NumberFormatException e) {
                }
            }
        }));

        //Deposit into a cash account
        Button depositBtn = new Button("Deposit");
        TextField depositAmt = new TextField();
        depositAmt.setPromptText("Deposit Amt");
        depositBtn.setOnAction((event -> {
            //Get selected row
            int currentSelection = table.getSelectionModel().getSelectedIndex();
            if(currentSelection >=0) {
                try {
                    float amount = Float.parseFloat(depositAmt.getText());
                    CashAccount currentAccount = context.getPortfolio().getCashAccounts().get(currentSelection);
                    context.getPortfolio().getTransactionLog().add(new DepositTransaction(currentAccount, amount));

                } catch (NumberFormatException e) {
                }
            }
        }));

        //Transfer between cash accounts
        Button transferBtn = new Button("Transfer");
        TextField transferAmt = new TextField();
        transferAmt.setPromptText("Transfer Amt");
        TextField depositName = new TextField();
        depositName.setPromptText("Deposit Target Name");
        transferBtn.setOnAction((event -> {
            //Get selected row and find row associated with user input for deposit target
            int depositTargetIndex = -1;
            for(int i = 0; i < context.getPortfolio().getCashAccounts().size(); i++){
                if(context.getPortfolio().getCashAccounts().get(i).getName() == depositName.getText()){
                    depositTargetIndex = i;
                }
            }
            int currentSelection = table.getSelectionModel().getSelectedIndex();
            if(currentSelection >=0 && depositTargetIndex >=0) {
                try {
                    float amount = Float.parseFloat(transferAmt.getText());
                    CashAccount withdrawTarget = context.getPortfolio().getCashAccounts().get(currentSelection);
                    context.getPortfolio().getTransactionLog().add(new TransferTransaction(withdrawTarget, context.getPortfolio().getCashAccounts().get(depositTargetIndex), amount));

                } catch (NumberFormatException e) {
                }
            }
        }));

        sideBar.getChildren().addAll(sideLabel, withdrawBtn, withdrawAmt, depositBtn, depositAmt, transferBtn, transferAmt, depositName);

        cashAccountsSection.getChildren().addAll(tableContainer, sideBar);
        return cashAccountsSection;
    }

    @Override
    public void update(Observable o, Object arg){
        for(int i = 0; i< dynamicContent.size(); i++){
            switch(dynamicContent.get(i).getId()){
                case "cashAccountsTable":
                    TableView cashAccountsTable = (TableView)dynamicContent.get(i);
                    cashAccountsTable.setItems(FXCollections.observableList(context.getPortfolio().getCashAccounts()));

                    cashAccountsTable.requestFocus();
                    cashAccountsTable.getSelectionModel().selectFirst();
                    cashAccountsTable.getFocusModel().focus(0);
                    break;
                case "holdingEquitiesTable":
                    TableView holdingEquitiesTable = (TableView)dynamicContent.get(i);
                    holdingEquitiesTable.setItems(FXCollections.observableList(context.getPortfolio().getHoldingEquities()));
                    holdingEquitiesTable.getSelectionModel().selectFirst();
                    holdingEquitiesTable.getFocusModel().focus(0);
                    break;
                case "transactionsTable":
                    ScrollPane scrollPane = (ScrollPane)dynamicContent.get(i);
                    VBox transactionList = new VBox();
                    for(int j = 0; j < context.getPortfolio().getTransactionLog().size(); j++){
                        Text transaction = new Text(context.getPortfolio().getTransactionLog().get(j).toString());
                        transactionList.getChildren().add(transaction);
                    }
                    scrollPane.setContent(transactionList);
                    break;
            }
        }

    }

}
