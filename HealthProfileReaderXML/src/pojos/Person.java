// classes are grouped together in 'packages'
// Classes in the same pakage already see each other. 
// If a class is in another package, in other to see it, you need to import it
package pojos;
import java.util.Random;

// This is a typical Java Class. 
public class Person {

  // As with any other object oriente language, classes have attributes (i.e. the properties of the class). 
  // Each attribute is in turn of another class
  private long personId;
  private String firstname; // this is an attribute of the class String, and it is 'private'
                // private attributes are only accesible inside the object
  private String lastname;  // this is an attribute of the class String
  private String birthdate;
  private HealthProfile hProfile; // this is an attribute of the class HealthProfile 

  
  // constructors in java are used to create an object of the class 
  // (a java program basically plays with objects of different classes)
  // this constructor creates a Person object with a particular firstname, lastname and health profile
  public Person(Long personId, String fname, String lname, String birthdate, HealthProfile hp) {
    this.setPersonId(personId);
    this.setFirstname(fname);
    this.setLastname(lname);
    this.setBirthdate(birthdate);
    this.sethProfile(hp);
  }
  
  public Person(String fname, String lname, HealthProfile hp) {
    this.setPersonId(randomPersonId());
    this.setFirstname(fname);
    this.setLastname(lname);
    this.setBirthdate("1990-01-01");
    this.sethProfile(hp);
  }

  public Person(Long personId, String fname, String lname, String birthdate) {
    this.setPersonId(personId);
    this.setFirstname(fname);
    this.setLastname(lname);
    this.setBirthdate(birthdate);
    this.hProfile=new HealthProfile();
  }

  public Person(Long personId, String fname, String lname) {
    this.setPersonId(personId);
    this.setFirstname(fname);
    this.setLastname(lname);
    this.setBirthdate("1990-01-01");
    this.hProfile=new HealthProfile();
  }

  public Person(String fname, String lname) {
    this.setPersonId(randomPersonId());
    this.setFirstname(fname);
    this.setLastname(lname);
    this.setBirthdate("1990-01-01");
    this.hProfile=new HealthProfile();
  }

  public Person() {
    this.setPersonId(randomPersonId());
    this.setFirstname("Ana");
    this.setLastname("Daniel");
    this.setBirthdate("1990-01-01");
    this.hProfile=new HealthProfile();
  }

  // classes have methods, which are basically pieces of programs that can be executed on objects of the class
  // this dummy class, has only 'accesor' methods (i.e. methods to access its properties, which are all private)

  public long getPersonId() {
    return personId;
  }
  public void setPersonId(long personId) {
    this.personId = personId;
  }
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getBirthdate() {
    return birthdate;
  }
  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }
  public HealthProfile gethProfile() {
    return hProfile;
  }
  public void sethProfile(HealthProfile hProfile) {
    this.hProfile = hProfile;
  }

  static long randomPersonId(){
    Random rand = new Random();
    int randomNum = rand.nextInt((9999 - 1) + 1) + 1;

    return (long)randomNum;
  }
}
