import generated.*;

import javax.xml.bind.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;

public class JAXBMarshaller {
  public void generateXMLDocument(File xmlDocument) {
    try {

      JAXBContext jaxbContext = JAXBContext.newInstance("generated");
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
      generated.ObjectFactory factory = new generated.ObjectFactory();

      // Create people object
      PeopleType people = factory.createPeopleType();
      // Create person #1
      PersonType p1 = factory.createPersonType();
      PersonType p2 = factory.createPersonType();
      PersonType p3 = factory.createPersonType();
      
      // Set the details of the person #1
      p1.setId(BigInteger.valueOf(1));
      p1.setFirstname("Ana");
      p1.setLastname("Daniel");  
      p1.setBirthdate("1990-08-01T00:00:00.000+2:00");
      
      // health profile value is created by HealthProfileType
      HealthProfileType hp1 = factory.createHealthProfileType();
      hp1.setWeight(58F);
      hp1.setHeight(1.60F);
      hp1.setBmi(24F);
      p1.setHealthprofile(hp1);

      // Set the details of the person #2
      p2.setId(BigInteger.valueOf(2));
      p2.setFirstname("Mafer");
      p2.setLastname("Chuchuni");  
      p2.setBirthdate("1990-06-02T00:00:00.000+2:00");
      
      // health profile value is created by HealthProfileType
      HealthProfileType hp2 = factory.createHealthProfileType();
      hp2.setWeight(52F);
      hp2.setHeight(1.54F);
      hp2.setBmi(23F);
      p2.setHealthprofile(hp2);

      // Set the details of the person #3
      p3.setId(BigInteger.valueOf(2));
      p3.setFirstname("Karen");
      p3.setLastname("Jaqueline");  
      p3.setBirthdate("1990-06-05T00:00:00.000+2:00");
      
      // health profile value is created by HealthProfileType
      HealthProfileType hp3 = factory.createHealthProfileType();
      hp3.setWeight(68F);
      hp3.setHeight(1.64F);
      hp3.setBmi(26F);
      p3.setHealthprofile(hp3);

      people.getPerson().add(p1);
      people.getPerson().add(p2);
      people.getPerson().add(p3);


      JAXBElement<PeopleType> peopleElement = factory.createPeople(people);
      System.out.println(peopleElement);
      marshaller.marshal(peopleElement, new FileOutputStream(xmlDocument));

    } catch (IOException e) {
      System.out.println(e.toString());
    } catch (JAXBException e) {
      System.out.println(e.toString());
    }
  }

  public static void main(String[] argv) {
    String xmlDocument = "people_generated.xml";
    JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
    jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
  }
}
