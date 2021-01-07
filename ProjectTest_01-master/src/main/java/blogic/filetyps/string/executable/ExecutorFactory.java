package blogic.filetyps.string.executable;

import blogic.filetyps.binary.BinaryExecutor;
import blogic.filetyps.string.csv.StringFormatExecutorCSV;
import blogic.filetyps.string.StringFormatExecutor;
import blogic.filetyps.string.stringconverter.impl.*;
import blogic.fileutils.Constants;
import java.util.Scanner;

import static blogic.fileutils.Constants.ENTER_COMMAND;

public class ExecutorFactory {
   private final Scanner scanner;
    public ExecutorFactory(){
        scanner = new Scanner(System.in);
    }

    public Executable getInstance() {

        while (true) {
            System.out.println(Constants.CHOOSE_FORMAT);
            String newFormat = scanner.nextLine();
            switch (newFormat) {
                case Constants.BINARY:
                    System.out.println(ENTER_COMMAND);
                    return new BinaryExecutor();
                case Constants.JSON:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutor(new JsonStringConverter());
                case Constants.CSV:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutorCSV();
                case Constants.YAML:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutor(new YamlStringConverter());
                case Constants.XML:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutor(new XmlStringConverter());
                default:
                    System.out.println(Constants.INCORRECT_FORMAT);
            }
        }
    }
}
