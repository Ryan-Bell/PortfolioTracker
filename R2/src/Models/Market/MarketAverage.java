package Models.Market;

import java.util.ArrayList;

/**
 * The composite of MarketEquity.
 * extends MarketEquity to allow both Indexes
 * and Equities to be treated the same.
 */
public class MarketAverage extends MarketEquity {
    private ArrayList<MarketEquity> children;


    /** Constructor
     * @param name the name of the average
     */
    public MarketAverage(String name){
        super(name, 0, name.toUpperCase());
        this.children = new ArrayList<>();
    }

    /**
     * Adds a child MarketEquity to children
     * @param  child  a MarketEquity object to add
     */
    public void addChildren(MarketEquity child) {
        //check that child is not already in children
        if (!children.contains(child)) {
            //add it to the array
            children.add(child);

            calculateValue();
        }
    }

    /**
     * Calculates the price of this average based on price of children
     */
    public void calculateValue(){
        int total = 0;
        for (MarketEquity child:children) {
            total += child.getValue();
        }
        if(children.size() > 0)value = total/children.size();
    }

    //region GettersSetters
    /**
     * Gets the children field
     * @return  children    the array of MarketEquities
     */
    public ArrayList<MarketEquity> getChildren() {
        return children;
    }

    /**
     * Gets the added value of all the children
     * @return  value the value of all children
     */
    @Override
    public float getValue() { return value; }
    //endregion

    @Override
    public void accept(EquityVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public String toString(){return "Name: " + name + " value:  " + value;}
}
