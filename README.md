
# Assignment #1 from IntroSDE Lab

### About the code
The code in this repository contains various excercises that cover the use of XML, XPATH, XML Schemas, XML to and from Java objects. The code is compiled and excecuted by an ant build.xml file.

The code in this assignment was based of the lab sessions of the introSDE laboratory.

### About the tasks

The tasks that run in this assignment are the following:

1. Runs instruction 2 based on Lab 3. This prints all persons in the people.xml file.
2. Runs instruction 3 based on Lab 3 with id = 0005. This prints the person with id "0005" in the people.xml file.
3. Runs instruction 4 based on Lab 3 with weight > 90. This prints all persons with a weight greater than 90.
4. Runs instruction 2 based on Lab 4. This takes the PeopleSchema.xml and generates the necessary classes to marshall and unmarshall objects to/from XML. It then creates 3 person objects from the generated classes and creates an XML file called `marshalling_to_xml.xml`
5. Runs instruction 2 based on Lab 4 (un-marshaling from XML). Using the output xml created in task #4, this task takes the 3 persons in the file, creates the objects and prints them on screen.
6. ~~Runs instruction 3 based on Lab 4~~ (Not implemented)

### About how to execute

```
  git clone git@github.com:anadaniel/introsde-A1.git
  cd introsde-A1/HealthProfileReaderXML
  ant execute.evaluation
```
