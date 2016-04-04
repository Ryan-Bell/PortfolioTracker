package Models;

import java.util.HashMap;

public class EquityUpdateVisitor implements EquityVisitor {
    private HashMap<String, Float> newValues;

    public EquityUpdateVisitor(HashMap<String, Float> newValues){
        this.newValues = newValues;
    }

    @Override
    public void visit(Equity equity) {
        /*for(MarketEquity newEquity:newValues){
            //If the ticker symbol matches, update the price
            if(newEquity.getTickerSymbol().equals(equity.getTickerSymbol())){
                equity.setValue(newEquity.getValue());
            }
        }*/
        if(newValues.containsKey(equity.getTickerSymbol())){
            equity.setValue(newValues.get(equity.getTickerSymbol()));
        }
    }

    @Override
    public void visit(MarketAverage marketAverage) {
        for(MarketEquity child:marketAverage.getChildren()) {
            /*for (MarketEquity newEquity : newValues) {
                //If the ticker symbol matches, update the price of the children
                if (newEquity.getTickerSymbol().equals(child.getTickerSymbol())) {
                    child.setValue(newEquity.getValue());
                }
            }*/
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
