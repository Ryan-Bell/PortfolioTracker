package Models.Market;

import Models.Portfolio.HoldingEquity;

public interface EquityVisitor {
    public void visit(Equity equity);
    public void visit(MarketAverage marketAverage);
    public void visit(HoldingEquity equity);
}
