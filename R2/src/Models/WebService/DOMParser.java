package Models.WebService;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;


public class DOMParser {
    public HashMap<String, Float> parseXMLStringToMarketEquityArray(String xmlString) throws Exception {
        HashMap<String, Float> YQLMarketEquities = new HashMap<>();

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
                        YQLMarketEquities.put(tickerSymbol, updatedValue);
                    }
                }
            }
        }

        return YQLMarketEquities;
    }
}