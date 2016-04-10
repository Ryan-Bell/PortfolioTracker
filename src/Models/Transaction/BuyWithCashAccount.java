package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A command decorator. Decorates BuyTransaction to
 * purchase Equity with money in a cash account
 */
public class BuyWithCashAccount extends ExtendedTransactionDecorator implements Serializable{

    //region Fields
    //the cash account to withdraw the funds from
    private CashAccount cashAccount;

    //the cost of the shares purchased
    private float cost;

    private LocalDateTime date;

    public LocalDateTime getDate(){return date;}
    //endregion

    /** Constructor
     * @param decoratorTarget The base buy transaction to decorate
     * @param cashAccount the cash account to withdraw from
     */
    public BuyWithCashAccount(UndoRedo decoratorTarget, CashAccount cashAccount) {
        //call the parent constuctor with the base buy transaction
        super(decoratorTarget);
        this.cashAccount = cashAccount;
        this.date = decoratorTarget.getDate();
    }

    /**
     * Computes and withdraws the cost of the purchase from the cash account if it has enough money
     */
    @Override
    public void execute(){
        //compute the cost (numShares * price per share)
        cost = ((BuyTransaction)transactionToBoDecorated).getAmount() * ((BuyTransaction)transactionToBoDecorated).getTarget().getValue();

        //check that the cash account has enough money or set failed status to true
        if (cashAccount.sufficientFunds(cost)) {
            //withdraw the cost
            cashAccount.withdraw(cost);
            //call the execute of the undecorated transaction
            transactionToBoDecorated.execute();
        }
        else{
            failed = true;
        }
    }

    /**
     * Reverts the action of the execute method
     */
    @Override
    public void unExecute(){
        //Don't unexecute if execute failed
        if(!failed) {
            //deposit the money that was withdrawn in execute
            cashAccount.deposit(cost);
        }

        //call the unexecute of the undecorated transaction
        transactionToBoDecorated.unExecute();
    }

    @Override
    public String toString() {
        if(failed) return transactionToBoDecorated.toString() + " with account " + cashAccount;
        return transactionToBoDecorated.toString()+ " with account " + cashAccount;
    }
}
