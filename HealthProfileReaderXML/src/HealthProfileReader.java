import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class HealthProfileReader {  
  Document doc;
  XPath xpath;
  
  public void loadXML() throws ParserConfigurationException, SAXException, IOException {

    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    domFactory.setNamespaceAware(true);
    DocumentBuilder builder = domFactory.newDocumentBuilder();
    doc = builder.parse("people.xml");

      //creating xpath object
    getXPathObj();
  }
  
  public XPath getXPathObj() {

    XPathFactory factory = XPathFactory.newInstance();
    xpath = factory.newXPath();
    return xpath;
  }
  
  /**
  * Returns a node that contains the weight data of a person's healthprofile
  * 
  * @param firstname  A person's firstname
  * @param lastname   A person's lastname
  * @return node      The found weight node
  */
  public Node getWeight(String firstname, String lastname) throws XPathExpressionException {

    XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/weight");
    Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    return node;
  }

  /**
  * Returns a node that contains the height data of a person's healthprofile
  * 
  * @param firstname  A person's firstname
  * @param lastname   A person's lastname
  * @return node      The found height node
  */
  public Node getHeight(String firstname, String lastname) throws XPathExpressionException {

    XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/height");
    Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    return node;
  }

    /**
     * The health profile reader gets information from the command line about
     * weight and height and calculates the BMI of the person based on this 
     * parameters
     * 
     * @param args
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException,
    IOException, XPathExpressionException {
      int argCount = args.length;

      if (argCount > 1) {

        HealthProfileReader profileReader = new HealthProfileReader();
        profileReader.loadXML();

        if ( args[0].equals("getWeight") || args[0].equals("getHeight") ) {
          if (args[1] != null && args[2] != null ) {
            Node result = null;

            if ( args[0].equals("getWeight") ) {
              result = profileReader.getWeight(args[1], args[2]);
            }
            else {
              result = profileReader.getHeight(args[1], args[2]);
            }

            if (result != null){
              System.out.println( result.getTextContent()  );
            }
            else {
              System.out.println("Person not found!");
            }
            
          } else {
            System.out.println("I need a first and last name to retreive a person's data");
          }
        }
      }
    }
  }