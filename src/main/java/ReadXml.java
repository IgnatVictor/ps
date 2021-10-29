import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import timeConverter.TimeConverter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ReadXml {

    List<Company> companiesList = new ArrayList<>();
    ServiceFilter serviceFilter = new ServiceFilter();
    TimeConverter timeConverter = new TimeConverter();

    public List<Company> read(String inputLocation, String name) {

        try {
            Document document = parseDocument(inputLocation, name);
            NodeList order = document.getElementsByTagName("order");

            for (int i = 0; i < order.getLength(); i++) {
                Node secondNode = order.item(i);

                if (secondNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList product = ((Element) secondNode).getElementsByTagName("product");
                    createOrders(order, i, product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceFilter.companyList(companiesList);
    }

    private void createOrders(NodeList order, int i, NodeList product) {
        for (int j = 0; j < product.getLength(); j++) {
            Node node = product.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                LocalDateTime date = timeConverter.convertTime(((Element) order.item(i)).getAttribute("created"));

                Order newOrder = new Order(eElement.getElementsByTagName("description").item(0).getTextContent(),
                        eElement.getElementsByTagName("gtin").item(0).getTextContent(),
                        date,
                        Integer.parseInt(((Element) order.item(i)).getAttribute("ID")),
                        Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()),
                        eElement.getElementsByTagName("supplier").item(0).getTextContent(), "$");

                Company company = new Company(eElement.getElementsByTagName("supplier").item(0).getTextContent());

                company.addOrder(newOrder);
                companiesList.add(company);
            }
        }
    }

    private Document parseDocument(String inputLocation, String name) throws ParserConfigurationException, SAXException, IOException {
        //creating a constructor of file class and parsing an XML file
        File file = new File(inputLocation + name);
        //an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //an instance of builder to parse the specified xml file
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        return document;
    }
}

