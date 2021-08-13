package com.ausy.sebastian.phonebook;

import com.ausy.sebastian.phonebook.controller.PhoneBook;
import com.ausy.sebastian.phonebook.controller.PhoneBookImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        while (true) {
            PhoneBook phoneBook = new PhoneBookImpl();
            int select;
            phoneBook.printMenu();
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1: // Completed
                    phoneBook.addEditPhoneNumber();
                    break;
                case 2: //Completed
                    phoneBook.getAllContacts();
                    break;
                case 3: //Completed
                    phoneBook.findContactByPhoneNumber();
                    break;
                case 4: //Completed
                    phoneBook.findContactByName();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
