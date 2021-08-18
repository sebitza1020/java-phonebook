package com.ausy.sebastian.phonebook;

import com.ausy.sebastian.phonebook.repository.PhoneBook;
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
                case 1:
                    phoneBook.addEditPhoneNumber();
                    break;
                case 2:
                    phoneBook.getAllContacts();
                    break;
                case 3:
                    phoneBook.findContactByPhoneNumber();
                    break;
                case 4:
                    phoneBook.findContactByName();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
