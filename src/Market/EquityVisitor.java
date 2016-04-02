package Market;

import Portfolio.HoldingEquity;

/**
 * Created by user on 4/2/2016.
 */
public interface EquityVisitor {
    public void visit(Equity equity);
    public void visit(MarketAverage marketAverage);
    public void visit(HoldingEquity equity);
}
