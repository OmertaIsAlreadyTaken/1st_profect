package controller;

import blogic.filetyps.executable.Executable;
import blogic.filetyps.executable.ExecutorFactory;
import blogic.fileutils.FileUtils;
import controller.commands.CommandsController;
import controller.dialog.Dialog;
import controller.helper.Helper;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static blogic.fileutils.Constants.SWITCH;

public class AppController {
    private final ExecutorFactory executorFactory;
    private final Scanner scanner;
    private final Helper helper;
    private final Dialog dialog;
    private final CommandsController commandsController;
    private final FileUtils fileUtils;

    public AppController() {
        executorFactory = new ExecutorFactory();
        helper = new Helper();
        commandsController = new CommandsController();
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
            commandsController.create(command, helper, fileName, executor, personList);
            commandsController.help(command, helper);
            commandsController.exit(command, helper);
            commandsController.read(command, dialog, fileName, fileUtils, executor);
            commandsController.save(command, helper, personList, fileName, executor);
            personList = commandsController.update(command, personList, dialog, fileName, fileUtils, executor);
            commandsController.sort(command, dialog, fileName, fileUtils, personList, executor);
            personList = commandsController.delete(command, personList, dialog, fileName, fileUtils, executor);
            if (command.equalsIgnoreCase(SWITCH)) {
                fileName = fileUtils.fileNameCreator();
                executor = executorFactory.getInstance();
                personList.clear();
            }
        }
    }
}
