package src.main.java;

public class UserListener {

    public void listener(String command) {
        CRUD crud = new CRUD();
        Helpers helpers = new Helpers();
        if (command.equalsIgnoreCase("create" + " \\*\\.JSON")) {
            crud.create();
        } else if (command.equalsIgnoreCase("update" + " \\*....")) {
            crud.update();
        } else if (command.equalsIgnoreCase("delete" + " \\*")) {
            crud.delete();
        } else if (command.equalsIgnoreCase("read" + " \\*")) {
            crud.read();
        } else if (command.equalsIgnoreCase("exit")) {
            helpers.exit();
        } else if (command.equalsIgnoreCase("help")) {
            helpers.help();
        } else if (command.equalsIgnoreCase("switch")) {
            helpers.switchPage();
        } else if (command.equalsIgnoreCase("start")) {
            helpers.start();
        }
    }
}
