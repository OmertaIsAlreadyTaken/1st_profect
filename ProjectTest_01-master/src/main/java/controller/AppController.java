package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import blogic.filetyps.string.executable.Executable;
import blogic.filetyps.string.executable.ExecutorFactory;
import blogic.fileutils.Constants;
import blogic.fileutils.FileUtils;
import helper.Helper;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static blogic.fileutils.Constants.*;

public class AppController {
    private final ExecutorFactory executorFactory;
    private final Scanner scanner;
    private final Helper helper;
    private final FileUtils fileUtils;

    public AppController() {
        executorFactory = new ExecutorFactory();
        helper = new Helper();
        scanner = new Scanner(System.in);
        fileUtils = new FileUtils();
    }

    public void run() {
        List<Person> personList = new ArrayList<>();
        String fileName = fileUtils.fileNameCreator();
        Executable executor = executorFactory.getInstance();
        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(CREATE)) {
                helper.personCreator(fileName, executor, personList);

            } else if (command.equalsIgnoreCase(HELP)) {
                helper.help();
            } else if (command.equalsIgnoreCase(EXIT)) {
                System.exit(0);

            } else if (command.equalsIgnoreCase(READ)) {

                if (fileName.equalsIgnoreCase(SWITCH)) {
                    fileName = fileUtils.fileNameCreator();
                    executor = executorFactory.getInstance();
                    personList.clear();
                } else if (!fileUtils.isFileEmpty(fileName)) {
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

            } else if (command.equalsIgnoreCase(SWITCH)) {
                fileName = fileUtils.fileNameCreator();
                executor = executorFactory.getInstance();
                personList.clear();
            } else if (command.equalsIgnoreCase(SAVE)) {
                helper.save(personList, fileName, executor);

            } else if (command.equalsIgnoreCase(UPDATE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        personList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(Constants.ENTER_ID);
                int id = scanner.nextInt();

                executor.update(personList, id);

                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(SORT)) {

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

            } else if (command.equalsIgnoreCase(DELETE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        personList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    executor.delete(personList);

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(ENTER_COMMAND);
            }
        }
    }
}
