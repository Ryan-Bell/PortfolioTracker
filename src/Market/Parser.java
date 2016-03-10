package Market;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * Constructor
     * @param market reference to the market object
     * @param marketPath path to the market equities file relative to the location of the jar file
     */
    public Parser(Market market, String marketPath){
        this.market = market;
        this.marketPath = marketPath;
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
                    tickerSymbol = processTicker(s);
                    break;
                case 2:
                    name = processName(s);
                    break;
                case 3:
                    value = Float.parseFloat(processPrice(s));
                    break;
                case 4:
                    //first check if there is another match after this one (5th column
                    //otherwise, determine if it is a sector or an index
                    break;
            }

        }
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
     *
     * @param raw
     * @return
     */
    private String processName(String raw){
        //stub - can cause null pointer exception
        return null;
    }

    /**
     *
     * @param raw
     * @return
     */
    private String processPrice(String raw){
        //stub - number so it can be casted
        return "4";
    }
}
