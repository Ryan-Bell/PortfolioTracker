package WebService;

import Market.MarketEquity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;


public class DOMParser {
    public ArrayList<MarketEquity> parseXMLStringToMarketEquityArray(String xmlString) throws Exception {
        ArrayList<MarketEquity> YQLMarketEquities = new ArrayList<>();
//        String xmlRecords = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query xmlns:yahoo=\"http://www.yahooapis.com/v1/base.rng\" yahoo:count=\"2\" yahoo:created=\"2016-04-02T20:43:21Z\" yahoo:lang=\"en-US\"><results><quote symbol=\"AAPL\"><LastTradePriceOnly>109.99</LastTradePriceOnly></quote><quote symbol=\"GOOG\"><LastTradePriceOnly>749.91</LastTradePriceOnly></quote></results></query><!-- total: 22 --><!-- main-be5d7b25-f7ca-11e5-bac7-d4ae52974741 -->";

        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlString));

        Document document = builder.parse(is);

        //Iterating through the nodes and extracting the data.
        NodeList nodeList = document.getDocumentElement().getChildNodes();



        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            if (node instanceof Element) {

                NodeList childNodes = node.getChildNodes();

                System.out.println("Test "+childNodes.getLength());

                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    String tickerSymbol = cNode.getAttributes().getNamedItem("symbol").getNodeValue();

                    if (cNode instanceof Element) {

                        float updatedValue = -1;
                        try {
                            updatedValue = Float.parseFloat(cNode.getLastChild().getTextContent().trim());
                        } catch (Exception e) {
//                            System.out.println("DOMParser " + e.getMessage());
                        }
                        MarketEquity updatedEquity = new MarketEquity("", updatedValue, tickerSymbol);
                        YQLMarketEquities.add(updatedEquity);
                    }
                }
            }
        }

        return YQLMarketEquities;
    }
}