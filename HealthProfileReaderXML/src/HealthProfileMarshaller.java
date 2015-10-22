import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileMarshaller {    
  public static PeopleStore people = new PeopleStore();

  public static void initializeDB() {
    Person p1 = new Person(new Long(1), "Ana", "Daniel", "1990-08-01", new HealthProfile(58.0, 1.60));
    Person p2 = new Person(new Long(2), "Mafer", "Chuchuni", "1990-06-02", new HealthProfile(51.0, 1.54));
    Person p3 = new Person(new Long(3), "Karen", "Jaqueline", "1990-06-05", new HealthProfile(68.0, 1.67));

    people.getData().add(p1);
    people.getData().add(p2);
    people.getData().add(p3);
  } 

  public static void main(String[] args) throws Exception {
    
    initializeDB();
    
    JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("marshalling_to_xml.xml")); // marshalling into a file
        m.marshal(people, System.out);      // marshalling into the system default output
    }
}
