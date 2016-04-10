package Models.Market;

import Models.Portfolio.HoldingEquity;
import java.util.HashMap;

public class EquityUpdateVisitor implements EquityVisitor {

    //holds the new values pulled from yahoo
    private HashMap<String, Float> newValues;


    /** Constructor
     * @param newValues the updated values from yahoo
     */
    public EquityUpdateVisitor(HashMap<String, Float> newValues){
        this.newValues = newValues;
    }

    /** updates the value of the visited equity
     * @param equity the equity that was visited
     */
    @Override
    public void visit(Equity equity) {
        if(newValues.containsKey(equity.getTickerSymbol())){
            if(newValues.get(equity.getTickerSymbol()) != -1) {
                equity.setValue(newValues.get(equity.getTickerSymbol()));
            }
        }
    }

    /** updated the value of the visited market average
     * @param marketAverage the market average that was visited
     */
    @Override
    public void visit(MarketAverage marketAverage) {
        for(MarketEquity child:marketAverage.getChildren()) {
            if(newValues.containsKey(child.getTickerSymbol())){
                if(newValues.get(child.getTickerSymbol()) != -1) {
                    child.setValue(newValues.get(child.getTickerSymbol()));
                }
            }
        }
        marketAverage.calculateValue();

    }

    /** This visitor doesn't  do anything to holding equities
     * @param equity the holding equity that was visited
     */
    @Override
    public void visit(HoldingEquity equity) {}
}
