package Models.Transaction;

import java.io.Serializable;

/**
 * Abstract class that defines behaviour for decorators of Transaction Objects
 */
public abstract class SimpleTransactionDecorator implements Transaction, Serializable {

    //region Fields
    //The transaction that is to be decorated
    private Transaction transactionToBeDecorated;

    //the failure status specific to this decorator
    protected boolean failed;
    //endregion

    /** Constructer
     * @param decoratorTarget the object being decorated
     */
    public SimpleTransactionDecorator(Transaction decoratorTarget){
        transactionToBeDecorated = decoratorTarget;
        failed = false;;
    }

    /**
     * Base execute just calls execute of the decoratee if not overridden
     */
    @Override
    public void execute() {
        transactionToBeDecorated.execute();
    }
}
