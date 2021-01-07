package controller;

import blogic.filetyps.executable.Executable;
import blogic.filetyps.executable.ExecutorFactory;
import blogic.fileutils.FileUtils;
import controller.dialog.Dialog;
import controller.helper.Helper;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static blogic.fileutils.Constants.*;

public class AppController {
    private final ExecutorFactory executorFactory;
    private final Scanner scanner;
    private final Helper helper;
    private final Dialog dialog;
    private final FileUtils fileUtils;

    public AppController() {
        executorFactory = new ExecutorFactory();
        helper = new Helper();
        scanner = new Scanner(System.in);
        fileUtils = new FileUtils();
        dialog = new Dialog();
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
                helper.exit();

            } else if (command.equalsIgnoreCase(READ)) {
                dialog.dialogFileReader(fileName, fileUtils, executor);

            } else if (command.equalsIgnoreCase(SWITCH)) {
                fileName = fileUtils.fileNameCreator();
                executor = executorFactory.getInstance();
                personList.clear();
            } else if (command.equalsIgnoreCase(SAVE)) {
                helper.save(personList, fileName, executor);

            } else if (command.equalsIgnoreCase(UPDATE)) {
                personList = dialog.updateDialog(fileName, fileUtils, personList, executor);

            } else if (command.equalsIgnoreCase(SORT)) {
                dialog.sortDialog(fileName, fileUtils, personList, executor);

            } else if (command.equalsIgnoreCase(DELETE)) {
                personList = dialog.deleteDialog(fileName, fileUtils, personList, executor);

            }
        }
    }
}
