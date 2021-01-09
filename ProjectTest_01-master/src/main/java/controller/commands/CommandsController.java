package controller.commands;

import blogic.filetyps.executable.Executable;
import blogic.fileutils.FileUtils;
import controller.dialog.Dialog;
import controller.helper.Helper;
import person.Person;

import java.util.List;

import static blogic.fileutils.Constants.*;

public class CommandsController {
    public void create(String command, Helper helper, String fileName, Executable executor, List<Person> personList) {
        if (command.equalsIgnoreCase(CREATE)) {
            helper.personCreator(fileName, executor, personList);
        }
    }

    public void help(String command, Helper helper) {
        if (command.equalsIgnoreCase(HELP)) {
            helper.help();
        }
    }

    public void exit(String command, Helper helper) {
        if (command.equalsIgnoreCase(EXIT)) {
            helper.exit();
        }
    }

    public void read(String command, Dialog dialog, String fileName, FileUtils fileUtils, Executable executor) {
        if (command.equalsIgnoreCase(READ)) {
            dialog.dialogFileReader(fileName, fileUtils, executor);
        }
    }

    public void save(String command, Helper helper, List<Person> personList, String fileName, Executable executor) {
        if (command.equalsIgnoreCase(SAVE)) {
            helper.save(personList, fileName, executor);

        }
    }

    public List<Person> update(String command,List<Person> personList, Dialog dialog,String fileName, FileUtils fileUtils, Executable executor){
        if (command.equalsIgnoreCase(UPDATE)) {
            personList = dialog.updateDialog(fileName, fileUtils, personList, executor);

        }
        return personList;
    }

    public void sort(String command, Dialog dialog,String fileName,FileUtils fileUtils, List<Person> personList,Executable executor){
         if (command.equalsIgnoreCase(SORT)) {
            dialog.sortDialog(fileName, fileUtils, personList, executor);

        }
    }
    public List<Person> delete(String command,List<Person> personList, Dialog dialog,String fileName,FileUtils fileUtils,Executable executor){

        if (command.equalsIgnoreCase(DELETE)) {
            personList = dialog.deleteDialog(fileName, fileUtils, personList, executor);

        }
        return personList;
    }
}
