package Market;

import java.util.ArrayList;

/**
 * The composite of MarketEquity.
 * extends MarketEquity to allow both Indexes
 * and Equities to be treated the same.
 */
public class Index extends MarketEquity {
    private ArrayList<MarketEquity> children;

    /**
     * Constructor
     */
    public Index(String name){
        this.value = 0;
        this.name = name;

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

            value += child.value;
        }
    }



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

    @Override
    public String toString(){return "Name: " + name + " value:  " + value;}
}
