package blogic.filetyps.string;

import blogic.filetyps.executable.Executable;
import blogic.filetyps.string.stringconverter.IPersonStringConverter;
import blogic.fileutils.Constants;
import blogic.fileutils.FileUtils;
import blogic.fileutils.IdComparator;
import person.Person;

import java.util.*;

import static blogic.fileutils.Constants.*;

public class StringFormatExecutor implements Executable {
    private final FileUtils fileUtils;
    private final Scanner scanner;
    private final IPersonStringConverter personStringConverter;


    public StringFormatExecutor(IPersonStringConverter personStringConverter) {
        this.personStringConverter = personStringConverter;
        fileUtils = new FileUtils();
        scanner = new Scanner(System.in);
    }

    public boolean write(String fileName, List<Person> personList) {
        String content;
        content = personStringConverter.personToString(personList);
        personList.clear();
        return fileUtils.saveToFile(fileName, content);
    }

    public List<Person> read(String fileName) {
        List<Person> personList;
        String output = fileUtils.readFromFile(fileName);
        personList = personStringConverter.stringToPerson(output);
        return personList;
    }

    public List<Person> update(List<Person> personList) {
        Scanner scannerId = new Scanner(System.in);
        System.out.println(Constants.ENTER_ID);
        int id = scannerId.nextInt();

        Iterator<Person> iterator = personList.iterator();

        System.out.println(ENTER_PERSON_DATA_UPDATE);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        while (iterator.hasNext()) {

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id) {
                iterPerson.setFirstName(array[0]);
                iterPerson.setLastName(array[1]);
                iterPerson.setAge(array[2]);
                iterPerson.setCity(array[3]);
                break;
            }
        }
        System.out.println(FILE_WAS_UPD);
        System.out.println(ENTER_COMMAND);
        return personList;
    }

    public List<Person> delete(List<Person> personList) {
        System.out.println(Constants.ENTER_ID);
        int id = scanner.nextInt();

        Iterator<Person> iterator = personList.iterator();

        while (iterator.hasNext()) {

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id) {
                iterator.remove();
                break;
            }
        }
        System.out.println(Constants.FILE_ELEMENT_WAS_DEL);
        System.out.println(ENTER_COMMAND);
        return personList;
    }

    public boolean sort(List<Person> personList, String fileName) {
        IdComparator idComparator = new IdComparator();
        personList.sort(idComparator);
        String content = personStringConverter.personToString(personList);
        personList.clear();
        System.out.println(SORTED);
        System.out.println(ENTER_COMMAND);
        return fileUtils.saveToFile(fileName, content);
    }
}