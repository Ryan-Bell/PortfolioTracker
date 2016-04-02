package Market;

import Portfolio.HoldingEquity;

import java.util.ArrayList;

/**
 * Created by user on 4/2/2016.
 */
public class EquityUpdateVisitor implements EquityVisitor {
    private ArrayList<MarketEquity> newValues;

    public EquityUpdateVisitor(ArrayList<MarketEquity> newValues){
        this.newValues = newValues;
    }

    @Override
    public void visit(Equity equity) {
        for(MarketEquity newEquity:newValues){
            //If the ticker symbol matches, update the price
            if(newEquity.getTickerSymbol().equals(equity.getTickerSymbol())){
                equity.setValue(newEquity.getValue());
            }
        }
    }

    @Override
    public void visit(MarketAverage marketAverage) {
        for(MarketEquity child:marketAverage.getChildren()) {
            for (MarketEquity newEquity : newValues) {
                //If the ticker symbol matches, update the price of the children
                if (newEquity.getTickerSymbol().equals(child.getTickerSymbol())) {
                    child.setValue(newEquity.getValue());
                }
            }
        }
        marketAverage.calculateValue();
    }

    @Override
    public void visit(HoldingEquity equity) {

    }
}
