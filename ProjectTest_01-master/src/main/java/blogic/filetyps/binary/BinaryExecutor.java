package blogic.filetyps.binary;

import blogic.filetyps.executable.Executable;
import blogic.fileutils.Constants;
import blogic.fileutils.IdComparator;
import person.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static blogic.fileutils.Constants.*;

public class BinaryExecutor implements Executable {
    private final Scanner scanner;

    public BinaryExecutor() {
        scanner = new Scanner(System.in);
    }

    public boolean write(String fileName, List<Person> personList) {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(personList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Person> read(String fileName) {
        FileInputStream fis;
        ObjectInputStream ois;
        ArrayList<Person> listPerson = new ArrayList<>();
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            listPerson = (ArrayList<Person>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listPerson;
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
        FileOutputStream fos;
        ObjectOutputStream oos;
        IdComparator idComparator = new IdComparator();
        personList.sort(idComparator);
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(personList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(SORTED);
        System.out.println(ENTER_COMMAND);
        return true;
    }
}
