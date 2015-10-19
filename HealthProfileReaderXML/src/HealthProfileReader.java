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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
  * Print a list of all people in the people.xml file that fullfill the given weight condition
  *
  * @param condition  The weight condition for the people to print (i.e. '>80', '=75Kg')
  */
  public void printPeople(String condition) throws XPathExpressionException {

    char operator = condition.charAt(0);
    String weight = condition.substring(1);

    XPathExpression expr = xpath.compile("//person[healthprofile/weight" + operator + "'" + weight + "']");
    NodeList person_nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

    Integer number_of_people = person_nodes.getLength();

    for (int i = 0; i < number_of_people; i++) {

      Node person = person_nodes.item(i);
      printPerson(person);
      printHealthProfile(person);
    }
  }

  /**
  * Print a list of all people in the people.xml file
  */
  public void printPeople() throws XPathExpressionException {

    XPathExpression expr = xpath.compile("//person");
    NodeList person_nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

    Integer number_of_people = person_nodes.getLength();

    for (int i = 0; i < number_of_people; i++) {

      Node person = person_nodes.item(i);
      printPerson(person);
      printHealthProfile(person);
    }
  }

  /**
  * Prints the personal details of a person.
  *
  * @param person   A Node representing a person from the xml file.
  */
  public void printPerson( Node person ) {
    Element person_element = (Element) person;

    System.out.println( "Id: " + person_element.getAttribute("id") );
    System.out.println( "Firstname: " + person_element.getElementsByTagName("firstname").item(0).getTextContent() );
    System.out.println( "Lastname: " + person_element.getElementsByTagName("lastname").item(0).getTextContent() );
    System.out.println( "Birthdate: " + person_element.getElementsByTagName("birthdate").item(0).getTextContent() );
  }

  /**
  * Finds a person from a given ID
  *
  * @param id   The id of a person from the people XML
  */
  public Node findPersonById(String id) throws XPathExpressionException {
    XPathExpression expr = xpath.compile("(//person[@id=" + id + "])[1]");
    Node person = (Node) expr.evaluate(doc, XPathConstants.NODE);
    return person;
  }

  /**
  * Prints the Health Profile of a person.
  *
  * @param person   A Node representing a person from the xml file.
  */
  public void printHealthProfile( Node person ) {
    Element person_element = (Element) person;
    Element health_profile = (Element) person_element.getElementsByTagName("healthprofile").item(0);

    System.out.println( "Weight: " + health_profile.getElementsByTagName("weight").item(0).getTextContent() );
    System.out.println( "Height: " + health_profile.getElementsByTagName("height").item(0).getTextContent() );
    System.out.println( "BMI: " + health_profile.getElementsByTagName("bmi").item(0).getTextContent() );
  }

  /**
  * Finds a person from a given ID and then it prints its Health Profile
  *
  * @param id   The id of a person from the people XML
  */
  public void printHealthProfileFromId( String id ) throws XPathExpressionException {
    Node person = findPersonById(id);

    if ( person != null ){
      printHealthProfile(person);
    }
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

    if (argCount >= 1) {

      HealthProfileReader profileReader = new HealthProfileReader();
      profileReader.loadXML();

      // Process getWeight and getHeight functions
      if ( args[0].equals("getWeight") || args[0].equals("getHeight") ) {
        if ( argCount >= 3 ) {
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
      else {
        if ( args[0].equals("printPeople") ) {
          if ( argCount >= 2 ){
            System.out.println(args[1]);
            profileReader.printPeople(args[1]);
          }
          else{
            profileReader.printPeople();
          }
        }
        else {
          if ( args[0].equals("printHealthProfileFromId") ){
            profileReader.printHealthProfileFromId( args[1] );
          }
        }
      }
    }
  }
}