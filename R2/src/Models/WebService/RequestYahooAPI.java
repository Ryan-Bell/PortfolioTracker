package Models.WebService;

import Models.Market.MarketEquity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class RequestYahooAPI {
    ArrayList<MarketEquity> marketEquities;
    DOMParser XMLParser;
    float xmlBreakPoint;
    float xmlBreakPointDefault;

    public RequestYahooAPI(ArrayList<MarketEquity> marketEquities) {
        this.marketEquities = marketEquities;
        this.XMLParser = new DOMParser();
        this.xmlBreakPoint = 300;
        this.xmlBreakPointDefault = this.xmlBreakPoint;
    }

    /**
     * Queries YQL and returns with updated Equities.
     * @return a hashMap of Equity names mapped to their values
     */
    public HashMap<String, Float> getUpdatedMarketEquities() {
        HashMap<String, Float> updatedMarketEquities = new HashMap<>();

        try {
            int loops = (int)Math.ceil(this.marketEquities.size() / this.xmlBreakPointDefault);

            for (int i = 0; i < loops; i++){
                String xmlString = getXMLStringFromURL();

                updatedMarketEquities.putAll(this.XMLParser.parseXMLStringToMarketEquityArray(xmlString));
            }
        } catch (Exception e)  {
            System.out.println("!!! RequestYahooAPI: " + e.getMessage());
        }

        return updatedMarketEquities;
    }

    /**
     * Queries YQL to get a XML string
     * @return YQL's resulting XML string
     * @throws IOException
     */
    public String getXMLStringFromURL() throws IOException {
        String url = getURLString();

        // Create a URL and open a connection
        URL YahooURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // MAKE SURE TO CLOSE YOUR CONNECTION!
        in.close();

        // response is the contents of the XML
        return response.toString();
    }

    /**
     * Appends all the extra info to the Equities URL.
     * @return the full YQL URL
     */
    private String getURLString() {
        String beginning = "https://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%2Csymbol%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(";
        String middle = packMarketEquitiesToQueryString();
        String end = ")&env=store://datatables.org/alltableswithkeys";

        return (beginning + middle + end);
    }

    /**
     * Breaks all the equities into smaller strings so YQL can handle the load.
     * @return the string of all market equities
     */
    private String packMarketEquitiesToQueryString() {
        String querySet = "";

        int i = (int)(this.xmlBreakPoint - this.xmlBreakPointDefault);
        while (this.xmlBreakPoint > i++) {
            if (i >= this.marketEquities.size()) {
                break;
            } else {
                MarketEquity marketEquity = this.marketEquities.get(i);
                String ticker = marketEquity.getTickerSymbol();
                querySet += "%27" + ticker.replaceAll("\\s+", "") + "%27,";
            }
        }

        this.xmlBreakPoint += this.xmlBreakPointDefault;
        querySet = querySet.substring(0, querySet.length() - 1);

        return querySet;
    }
}
