package src.main.java;

import java.util.Scanner;

public class Client {
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        UserListener userListener = new UserListener();
        userListener.listener(command);
    }
}
