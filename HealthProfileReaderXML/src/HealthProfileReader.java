import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import pojos.HealthProfile;
import pojos.Person;


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
  
  public Node getWeight(String firstname, String lastname) throws XPathExpressionException {
	  XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']/healthprofile/weight");
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
        if ( args[0].equals("getWeight") ) {
          if (args[1] != null && args[2] != null ) {
        	Node weight = profileReader.getWeight(args[1], args[2]);
        	if (weight != null){
        		System.out.println( weight.getTextContent()  );
        	}
        	else {
        		System.out.println("Person not found!");
        	}
          } else {
            System.out.println("I cannot perform get the weight without a first and last name.");
          }
        }
      }
    }
  }