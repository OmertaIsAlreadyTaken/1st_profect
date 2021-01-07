package controller.dialog;

import blogic.filetyps.executable.Executable;
import blogic.filetyps.executable.ExecutorFactory;
import blogic.fileutils.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import person.Person;

import java.util.ArrayList;
import java.util.List;

import static blogic.fileutils.Constants.*;

public class Dialog {

    public void dialogFileReader(String fileName, FileUtils fileUtils, Executable executor) {
        if (!fileUtils.isFileEmpty(fileName)) {
            List<Person> tempList = new ArrayList<>();
            try {
                tempList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            for (Person p : tempList) {
                System.out.println(p);
            }
        } else {
            System.out.println(FILE_IS_EMPTY);
        }
        System.out.println(ENTER_COMMAND);
    }

    public void sortDialog(String fileName, FileUtils fileUtils, List<Person> personList, Executable executor) {

        if (!fileUtils.isFileEmpty(fileName)) {
            try {
                personList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(FILE_IS_EMPTY);

        }

        executor.sort(personList, fileName);
    }

    public List<Person> updateDialog(String fileName, FileUtils fileUtils, List<Person> personList, Executable executor) {
        if (!fileUtils.isFileEmpty(fileName)) {
            try {
                personList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(FILE_IS_EMPTY);

        }

        return executor.update(personList);
    }

    public List<Person> deleteDialog(String fileName, FileUtils fileUtils, List<Person> personList, Executable executor) {

        if (!fileUtils.isFileEmpty(fileName)) {
            try {
                personList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return executor.delete(personList);

        } else {
            System.out.println(FILE_IS_EMPTY);

        }
        return personList;
    }
}
