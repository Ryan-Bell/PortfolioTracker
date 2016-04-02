package WebService;

import Market.MarketEquity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RequestYahooAPI {
    String tickers;
    DOMParser XMLParser;

    public RequestYahooAPI(ArrayList<MarketEquity> marketEquities) {
        this.tickers = packMarketEquitiesToQueryString(marketEquities);
        this.XMLParser = new DOMParser();
    }

    public ArrayList<MarketEquity> getUpdatedMarketEquities() {
        ArrayList<MarketEquity> updatedMarketEquities = new ArrayList<>();

        try {
            String xmlString = getXMLStringFromURL();
            updatedMarketEquities = this.XMLParser.parseXMLStringToMarketEquityArray(xmlString);
        } catch (Exception e)  {
            System.out.println(e.getMessage());
        }

        return updatedMarketEquities;
    }

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
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
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

    private String getURLString() {
        String beginning = "https://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%2Csymbol%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(";
        String middle = this.tickers;
        String end = ")&env=store://datatables.org/alltableswithkeys";

        return beginning + middle + end;
    }

    private String packMarketEquitiesToQueryString(ArrayList<MarketEquity> marketEquities) {
        String querySet = "";
        for (MarketEquity marketEquity : marketEquities) {
            String ticker = marketEquity.getTickerSymbol();
            querySet += "%22" + ticker + "%22,";
        }

        querySet = querySet.substring(0, querySet.length()-1);

        return querySet;
    }
}
