package Models.Market;

import Models.Portfolio.HoldingEquity;

public interface EquityVisitor {
    void visit(Equity equity);
    void visit(MarketAverage marketAverage);
    void visit(HoldingEquity equity);
}
