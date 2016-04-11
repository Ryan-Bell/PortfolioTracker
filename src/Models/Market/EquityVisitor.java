package Models.Market;

import Models.Portfolio.HoldingEquity;

/**
 * interface that requires visitor methods for all types of equities
 */
public interface EquityVisitor {

    /** visit method that handles equities
     * @param equity the equity being handled
     */
    void visit(Equity equity);

    /** visit method that handles averages
     * @param marketAverage the average being handled
     */
    void visit(MarketAverage marketAverage);

    /** visit method that handles holdings
     * @param equity the holding being handled
     */
    void visit(HoldingEquity equity);
}
