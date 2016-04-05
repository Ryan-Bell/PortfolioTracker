package Models.Market;

import Models.Portfolio.HoldingEquity;
import java.util.HashMap;

public class EquityUpdateVisitor implements EquityVisitor {
    private HashMap<String, Float> newValues;

    public EquityUpdateVisitor(HashMap<String, Float> newValues){
        this.newValues = newValues;
    }

    @Override
    public void visit(Equity equity) {
        if(newValues.containsKey(equity.getTickerSymbol())){
            equity.setValue(newValues.get(equity.getTickerSymbol()));
        }
    }

    @Override
    public void visit(MarketAverage marketAverage) {
        for(MarketEquity child:marketAverage.getChildren()) {
            if(newValues.containsKey(child.getTickerSymbol())){
                child.setValue(newValues.get(child.getTickerSymbol()));
            }
        }
        marketAverage.calculateValue();

    }

    @Override
    public void visit(HoldingEquity equity) {

    }
}
