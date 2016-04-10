package Models.Market;

import Models.Portfolio.HoldingEquity;

/**
 * interface that requires visitor methods for all types of equities
 */
public interface EquityVisitor {

    /** visit method that
     * @param equity
     */
    void visit(Equity equity);

    /**
     * @param marketAverage
     */
    void visit(MarketAverage marketAverage);

    /**
     * @param equity
     */
    void visit(HoldingEquity equity);
}
