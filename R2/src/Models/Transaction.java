package Models;

/**
 * Command Pattern that handles user input related to equityHoldings and
 * cashAccounts. Used as the controller in MVC design. Implemented by
 * concrete transactions.
 */
public interface Transaction{

    /**
     * default method to run a concrete transaction
     */
    void execute();
}
