package Models.Transaction;

public abstract class SimpleTransactionDecorator implements Transaction {

    private Transaction transactionToBeDecorated;
    protected boolean failed;

    public SimpleTransactionDecorator(Transaction decoratorTarget){
        transactionToBeDecorated = decoratorTarget;
        failed = false;;
    }

    @Override
    public void execute() {
        transactionToBeDecorated.execute();
    }
}
