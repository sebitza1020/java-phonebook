package com.ausy.sebastian;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static PhoneBook phoneBook = new PhoneBookImpl();

    public static void main(String[] args) {

        while (true) {
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
                case 4: //Almost there
                    phoneBook.findContactByName();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
}
