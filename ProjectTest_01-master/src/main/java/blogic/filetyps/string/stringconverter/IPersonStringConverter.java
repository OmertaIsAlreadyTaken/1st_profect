package blogic.filetyps.string.stringconverter;
import person.Person;

import java.util.List;

public interface IPersonStringConverter {
    String personToString(List<Person> person);
    List<Person> stringToPerson(String personsStr) ;
}
