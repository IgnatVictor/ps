import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class OutputXml {

    public void writeToXml(List<Company> companies, String name, String outputLocation) {

        for (Company company : companies) {
            String head = outputLocation + company.getName() + name;

            try {

                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                // root element
                Element root = document.createElement("products");
                document.appendChild(root);

                for (int i = 0; i < company.getOrders().size(); i++) {
                    Element product = document.createElement("product");
                    root.appendChild(product);

                    Element description = document.createElement("description");
                    description.appendChild(document.createTextNode(company.getOrders().get(i).getDescription()));
                    product.appendChild(description);

                    Element gtin = document.createElement("gtin");
                    gtin.appendChild(document.createTextNode(company.getOrders().get(i).getGtin()));
                    product.appendChild(gtin);

                    Element price = document.createElement("price");
                    price.appendChild(document.createTextNode(company.getOrders().get(i).getPrice().toString()));
                    product.appendChild(price);

                    Attr attribute = document.createAttribute("price");
                    attribute.setValue("USD");
                    price.setAttributeNode(attribute);

                    Element orderId = document.createElement("orderid");
                    orderId.appendChild(document.createTextNode(String.valueOf(company.getOrders().get(i).getOrderId())));
                    product.appendChild(orderId);
                }

                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(head));

                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging

                transformer.transform(domSource, streamResult);

                System.out.println("Done creating XML File");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException transformerException) {
                transformerException.printStackTrace();
            }
        }
    }
}
