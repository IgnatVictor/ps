import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import timeConverter.TimeConverter;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class ReadXml {

    List<Company> companiesList = new ArrayList<>();
    ServiceFilter serviceFilter = new ServiceFilter();
    TimeConverter timeConverter = new TimeConverter();

    public List<Company> read(String inputLocation, String name) {

        try {
//creating a constructor of file class and parsing an XML file  
            File file = new File(inputLocation + name);
//an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            doc.getDocumentElement().normalize();

            NodeList order = doc.getElementsByTagName("order");

            for (int itrFirst = 0; itrFirst < order.getLength(); itrFirst++) {
                Node secondNode = order.item(itrFirst);

                if (secondNode.getNodeType() == Node.ELEMENT_NODE) {

                    NodeList product = ((Element) secondNode).getElementsByTagName("product");

                    for (int itrSecond = 0; itrSecond < product.getLength(); itrSecond++) {
                        Node node = product.item(itrSecond);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) node;

                            LocalDateTime date = timeConverter.convertTime(((Element) order.item(itrFirst)).getAttribute("created"));

                            Order newOrder = new Order(eElement.getElementsByTagName("description").item(0).getTextContent(),
                                    eElement.getElementsByTagName("gtin").item(0).getTextContent(),
                                    date,
                                    Integer.parseInt(((Element) order.item(itrFirst)).getAttribute("ID")),
                                    Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()),
                                    eElement.getElementsByTagName("supplier").item(0).getTextContent(), "$");
                            Company company = new Company(eElement.getElementsByTagName("supplier").item(0).getTextContent());

                            company.addOrder(newOrder);
                            companiesList.add(company);
                        }
                    }
                }
            }
// nodeList is not iterable, so we are using for loop
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceFilter.companyList(companiesList);
    }
}

