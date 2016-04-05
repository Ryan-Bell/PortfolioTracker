package View;

import Portfolio.CashAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Portfolio.Portfolio;
import Portfolio.HoldingEquity;
import Transaction.Transaction;
import Transaction.BuyTransaction;
import Transaction.AddCashAccTransaction;
import Transaction.DepositTransaction;
import java.util.ArrayList;

/**
 * Displays information about importing a portfolio's holdings
 * and transactions.
 */
public class ImportView extends View{
    ArrayList<String> results;
    ArrayList<CashAccount> conflicts;
    ObservableList<String> resultsDescription;
    ScrollPane conflictsPane;

    @Override
    public void display(Context context) {
        super.display(context);

        primaryStage.setTitle("Import");
        primaryStage.setWidth(730);
        primaryStage.setHeight(730);

        Text scenetitle = new Text("Import Another Portfolio");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle,0, 1);

        VBox importContainer = new VBox();
        grid.add(importContainer, 0, 2);
        importContainer.setPrefWidth(400);

        HBox fieldBox = new HBox();
        TextField enterField = new TextField();
        enterField.setPromptText("Enter .port File Name");
        Button enterButton = new Button("Import");
        enterButton.setOnAction((event -> {
            Portfolio importPortfolio = context.getUserAuthentication().getPOFromId(enterField.getText());
            if(importPortfolio != null) {
                //Go through and add transactions
                boolean equityDoesntExist = true;
                for (HoldingEquity equity : importPortfolio.getHoldingEquities()) {
                    for (HoldingEquity existingEquity:context.getPortfolio().getHoldingEquities()) {
                        if(equity.getName().equals(existingEquity.getName())){
                            existingEquity.setNumShares(existingEquity.getNumShares() + equity.getNumShares());
                            resultsDescription.add("Added "+equity.getNumShares()+" shares to "+equity.getName());
                            equityDoesntExist = false;
                        }
                    }
                    if(equityDoesntExist){
                        BuyTransaction buyTransaction = new BuyTransaction(equity,equity.getNumShares(), context.getPortfolio(), null);
                        buyTransaction.execute();
                        resultsDescription.add("Added "+equity.getNumShares()+" shares of "+equity.getName());
                    }
                }

                for(Transaction newTransaction:importPortfolio.getTransactionLog()){
                    context.getPortfolio().addTransaction(newTransaction);
                }

                ArrayList<CashAccount> conflictAccounts = new ArrayList<CashAccount>();
                ArrayList<CashAccount> existingAccounts = new ArrayList<CashAccount>();
                boolean accountDoesntExist = true;
                for (CashAccount account : importPortfolio.getCashAccounts()) {
                    for (CashAccount existingAccount:context.getPortfolio().getCashAccounts()) {
                        if(account.getName().equals(existingAccount.getName())){
                            conflictAccounts.add(account);
                            existingAccounts.add(existingAccount);
                            accountDoesntExist = false;
                        }
                    }
                    if(accountDoesntExist){
                        AddCashAccTransaction addCashTransaction = new AddCashAccTransaction(account.getName(),account.getBalance(), context.getPortfolio());
                        addCashTransaction.execute();
                        resultsDescription.add("Added Cash Account '"+account.getName()+"' with balance of "+account.getBalance());
                    }
                }
                displayConflicts(conflictAccounts, existingAccounts);
            }
        }));

        fieldBox.getChildren().addAll(enterField,enterButton);

        Label conflictsLabel = new Label("Import Conflicts");

        conflictsPane = new ScrollPane();
        VBox conflictsBox = new VBox();
        conflictsPane.setContent(conflictsBox);

        Label resultsLabel = new Label("Results");
        ListView<String> resultsList = new ListView<>();
        resultsDescription = FXCollections.observableArrayList();
        resultsList.setItems(resultsDescription);


        importContainer.getChildren().addAll(fieldBox, conflictsLabel, conflictsPane, resultsLabel, resultsList);
        primaryStage.setScene(scene);
    }

    public void displayConflicts(ArrayList<CashAccount> conflictAccounts, ArrayList<CashAccount> existingAccounts) {
        VBox conflictsList = new VBox();

        for (int i = 0; i < conflictAccounts.size(); i++) {
            //Created to appease the buttons
            int currentIndex = i;

            Label accountName = new Label(conflictAccounts.get(currentIndex).getName());
            Label balance = new Label("(Balance: "+String.valueOf(conflictAccounts.get(currentIndex).getBalance())+")");

            HBox labelBox = new HBox();
            labelBox.getChildren().addAll(accountName, balance);
            labelBox.setSpacing(20);



            Button merge = new Button("Merge");
            merge.setOnAction((event -> {
                DepositTransaction mergeTransaction = new DepositTransaction(existingAccounts.get(currentIndex), conflictAccounts.get(currentIndex).getBalance());
                mergeTransaction.execute();

                resultsDescription.add("Merged Cash Accounts '"+conflictAccounts.get(currentIndex).getName()+"'");
                conflictAccounts.remove(currentIndex);
                displayConflicts(conflictAccounts, existingAccounts);
            }));
            Button replace = new Button("Replace");
            replace.setOnAction((event -> {
                context.getPortfolio().removeCashAccount(existingAccounts.get(currentIndex));
                AddCashAccTransaction addCashTransaction = new AddCashAccTransaction(conflictAccounts.get(currentIndex).getName(),conflictAccounts.get(currentIndex).getBalance(), context.getPortfolio());
                addCashTransaction.execute();

                resultsDescription.add("Replaced Cash Account '"+conflictAccounts.get(currentIndex).getName()+"'");
                conflictAccounts.remove(currentIndex);
                displayConflicts(conflictAccounts, existingAccounts);
            }));
            Button ignore = new Button("Ignore");
            ignore.setOnAction((event -> {

                resultsDescription.add("Ignored Cash Account '"+conflictAccounts.get(currentIndex).getName()+"'");
                conflictAccounts.remove(currentIndex);
                displayConflicts(conflictAccounts, existingAccounts);
            }));
            HBox buttonBox = new HBox();
            buttonBox.getChildren().addAll(merge, replace, ignore);
            buttonBox.setPadding(new Insets(0,0,20,0));


            BorderPane accountDisplay = new BorderPane();
            accountDisplay.setLeft(labelBox);
            accountDisplay.setRight(buttonBox);

            conflictsList.getChildren().add(accountDisplay);
        }
        conflictsPane.setContent(conflictsList);

    }
}
