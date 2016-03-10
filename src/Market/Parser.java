package Market;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Reads in the market.csv file and
 * uses the data to create MarketEquity
 * objects to store them in Market.
 */
public class Parser {

    //the path to the market csv file
    String marketPath;

    //reference to the market object to use the creation methods
    Market market;

    //Really bad but seemingly necessary evil hard-coded array of the only two indexes in the csv file
    ArrayList<String> indicies;

    /**
     * Constructor
     * @param market reference to the market object
     * @param marketPath path to the market equities file relative to the location of the jar file
     */
    public Parser(Market market, String marketPath){
        this.market = market;
        this.marketPath = marketPath;
        indicies = new ArrayList<>();
        indicies.add("NASDAAQ100");
        indicies.add("DOW");
    }

    /**
     * Responsible for reading in the market file line by line and handle exception
     */
    public void parseFile(){
        File newFile = new File(marketPath);

        //check that the file exists
        if(newFile.isFile()) {
            //read in the file and pass each line to the parseLine function
            try (Stream<String> lines = Files.lines(Paths.get(marketPath), Charset.defaultCharset())) {
                lines.forEach(s -> this.parseLine(s));
            } catch(IOException e){
                System.out.println(e.getMessage());
            } catch (SecurityException e){
                //access to the file is denied
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Splits the line, strips the double quotes, and handles logic for column variable assignment
     * It calls the appropriate methods to parse each field
     * @param line the line to be parsed
     */
    private void parseLine(String line){
        //initialize the equity fields to non-accepted values
        String tickerSymbol = null;
        String name = null;
        float value = -1;
        String sector = null;
        String index = null;

        //keep track of the current field being parsed
        int fieldCount = 0;

        //Regex pattern to capture between double quotes
        String pattern = "(\"(.*?)\")";

        // Create a Pattern object
        Pattern compiledPattern = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher matcher = compiledPattern.matcher(line);

        //loop while there are still fields (matches)
        while (matcher.find()) {

            //update current field count
            fieldCount++;

            //save the captured field
            String s = matcher.group(1);

            //strip the double quotes that were also captured
            s = s.replace("\"", "");

            //switch on the current field for regex logic
            switch (fieldCount){
                case 1:
                    tickerSymbol = s;
                    System.out.println("Ticker:\t" + tickerSymbol);
                    break;
                case 2:
                    name = processName(s);
                    System.out.println("Name:\t" + name);
                    break;
                case 3:
                    value = Float.parseFloat(s);
                    System.out.println("Value:\t" + value);
                    break;
                case 4:
                    //first check if there is another match after this one (5th column
                    String col4 = s;
                    if (matcher.find()){
                        //save the captured field
                        String col5 = matcher.group(1);

                        //strip the double quotes that were also captured
                        col5 = col5.replace("\"", "");

                        sector = col4;
                        index = col5;
                    } else {
                        //check whether  the fourth column is a sector or an index by checking the hard-coded arraylist
                        if(indicies.contains(col4)){
                            //the column is an index
                            index = col4;

                        } else {
                            sector = col4;
                        }
                    }
                    break;
            }

        }
        market.addMarketEquity(tickerSymbol, name, value, sector, index);
    }

    /**
     * handles whitespace and carrot symbols found in tthe ticker column
     * @param raw the raw field string to be parsed
     * @return
     */
    private String processTicker(String raw){
        //regex pattern to capture anything before a carrot
        String pattern = "(.+?)[\\^].*";

        //create the pattern object
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(raw);

        //determine if there was a carrot or not (otherwise group(1) will throw an error)
        if(matcher.find()) {
            //return the captured characters after stripping whitespace
            return matcher.group(1).replaceAll("\\s+",  "");
        } else{
            //return the full field after removing whitespace
            return raw.replaceAll("\\s+","");
        }
    }

    /**
     * Handles parsing the company name and escapes the unicode
     * @param raw the string to be parsed
     * @return
     */
    private String processName(String raw){
        Pattern compiledPattern = Pattern.compile("(&#39;)");
        Matcher matcher = compiledPattern.matcher(raw);
        raw = matcher.replaceAll("'");
        return raw;
    }

    /**
     * Handles parsing the price and restricts the number of decimals to two
     * @param raw the raw field string to be parsed
     * @return
     */
    private String processPrice(String raw){
        //regex pattern to capture numbers on either side off a period (with only two after the period)
        String pattern = "([0-9]+[.]?[0-9]{2})";

        //create the pattern object
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(raw);

        //determine if there was a carrot or not (otherwise group(1) will throw an error)
        if(matcher.find()) {
            //return the captured characters after stripping whitespace
            return matcher.group(1).replaceAll("\\s+",  "");
        } else{
            //note ---- this should never be reached -----
            //return the full field after removing whitespace
            return raw.replaceAll("\\s+","");
        }
    }
}
