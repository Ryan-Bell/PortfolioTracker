package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;

/**
 * A command decorator. Decorates SellTransaction to
 * sell Equity and deposit value in a cash account
 */
public class SellWithCashAccount extends ExtendedTransactionDecorator implements Serializable{

    //region Fields
    //The cash account to deposit the value into
    private CashAccount cashAccount;

    //the number of shares being sold
    private int amount;

    //the meoney being deposited in the cash account
    private float depositAmount;
    //endregion

    /** Constructor
     * @param decoratorTarget the base sell transaction being decorated
     * @param cashAccount the cash account being deposited into
     */
    public SellWithCashAccount(UndoRedo decoratorTarget, CashAccount cashAccount) {
        super(decoratorTarget);
        this.cashAccount = cashAccount;
    }

    /**
     *
     */
    @Override
    public void execute(){
        //get the equity target, number of shares, and portfolio from the base transaction
        HoldingEquity target = ((SellTransaction)transactionToBoDecorated).getTarget();
        amount = ((SellTransaction)transactionToBoDecorated).getAmount();
        Portfolio portfolio = ((SellTransaction)transactionToBoDecorated).getPortfolio();

        //check that the number of shares being sold isn't more than the number purchased
        if(target.getNumShares() - amount >=0) {
            //deposit the cost of the equities sold into the cash account
            depositAmount = amount * target.getPricePerShare();
            cashAccount.deposit(depositAmount);
        }

        //update the failure status
        else failed = true;

        //call the execute of the base transaction
        transactionToBoDecorated.execute();
    }

    /**
     * Reverts the action done by execute
     */
    @Override
    public void unExecute(){
        //Don't do anything if the eexecute failed
        if(!failed) {
            //call the unexecute of the base transaction
            transactionToBoDecorated.unExecute();

            //withdraw the money deposited in execute
            cashAccount.withdraw(depositAmount);
        }

    }
}
