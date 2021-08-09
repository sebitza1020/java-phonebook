package com.ausy.sebastian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneBookImpl implements PhoneBook {
    Scanner sc = new Scanner(System.in);
    List<Contact> contacts = new ArrayList<>();
    Pattern phonePattern = Pattern.compile("^07[1-9][0-9]\\s\\d{3}\\s\\d{3}$");
    Pattern namePattern = Pattern.compile("^[A-Za-z]$");
    String phoneNumber;

    public void printMenu() {
        System.out.println("Phone Book");
        System.out.println("There are currently " + contacts.size() + " contacts in the phone book.\n");
        System.out.println(" 1. Add or edit a contact.");
        System.out.println(" 2. View all contacts.");
        System.out.println(" 3. Find a contact by phone number.");
        System.out.println(" 4. Find contacts by name.");
        System.out.println(" 0. Exit");
        System.out.print("\nSelect an option: ");
    }

    public void addEditPhoneNumber() {
        System.out.println("Add/edit a contact\n");

        do {
            System.out.print("Enter a phone number: ");
            phoneNumber = sc.nextLine();
        } while (phoneNumber.isEmpty() || !phonePattern.matcher(phoneNumber).matches());

        if (phonePattern.matcher(phoneNumber).matches()) {
            if (contacts.size() == 0) {
                addContact(phoneNumber);
                return;
            }
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    System.out.println("This phone number already exists. Editing an existing entry.");

                    String firstName;
                    do {
                        System.out.print("First Name: ");
                        firstName = sc.nextLine();
                    } while (firstName.isEmpty() || namePattern.matcher(firstName).matches());
                    contact.setFirstName(firstName);

                    String lastName;
                    do {
                        System.out.print("Last Name: ");
                        lastName = sc.nextLine();
                    } while (lastName.isEmpty() || namePattern.matcher(lastName).matches());
                    contact.setLastName(lastName);

                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    contact.setEmail(email);

                    System.out.print("Address: ");
                    String address = sc.nextLine();
                    contact.setAddress(address);

                    System.out.println("\nPhone book was updated successfully.\n");
                    System.out.println("Press ENTER to continue.");
                    sc.nextLine();
                    return;
                }
            }
            addContact(phoneNumber);
        }
    }

    public void addContact(String phoneNumber) {
        System.out.println("This phone number is new. Adding a new entry to the phone book.");

        String firstName;
        do {
            System.out.print("First Name: ");
            firstName = sc.nextLine();
        } while (firstName.isEmpty() || namePattern.matcher(firstName).matches());

        String lastName;
        do {
            System.out.print("Last Name: ");
            lastName = sc.nextLine();
        } while (lastName.isEmpty() || namePattern.matcher(lastName).matches());

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        contacts.add(new Contact(phoneNumber, firstName, lastName, email, address));

        System.out.println("Phone book was updated successfully.\n");
        System.out.println("Press ENTER to continue.");
        sc.nextLine();
    }

    public void findContactByPhoneNumber() {
        System.out.println("Search by phone number\n");

        do {
            System.out.print("Enter a phone number: ");
            phoneNumber = sc.nextLine();
        } while (phoneNumber.isEmpty() || !phonePattern.matcher(phoneNumber).matches());

        if (phonePattern.matcher(phoneNumber).matches()) {
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    System.out.printf("%-20s%-50s %n", "Phone number:", contact.getPhoneNumber());
                    System.out.printf("%-20s%-50s %n", "First name:", contact.getFirstName());
                    System.out.printf("%-20s%-50s %n", "Last name:", contact.getLastName());
                    System.out.printf("%-20s%-50s %n", "E-mail:", contact.getEmail());
                    System.out.printf("%-20s%-50s %n", "Address:", contact.getAddress());
                    System.out.println("Press ENTER to continue.");
                    sc.nextLine();
                    return;
                }
            }
            System.out.println("The phone number could not be found in the address book.\n");
            System.out.println("Press ENTER to continue.");
            sc.nextLine();
        }
    }

    public void getAllContacts() {
//        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com", "Bd. Regelui, nr. 1, Bucuresti"));
//        contacts.add(new Contact("0721 435 764", "John", "Doe", "john.doe@example.com", "Str. Geniului, nr. 77, Cluj-Napoca"));
//        contacts.add(new Contact("0741 826 314", "Jane", "Doe", "jane.doe@example.com", "Str. Moldovei, nr. 21, Sibiu"));

        System.out.println("Contact list\n");

        for (Contact contact : contacts) {
            System.out.printf("%-16s%-5s%-8s%-25s %n", contact.getPhoneNumber(), contact.getFirstName(),
                    contact.getLastName(), contact.getEmail());
        }

        System.out.println("\nPress ENTER to continue.");
        sc.nextLine();
    }

    public void findContactByName() {
        List<Contact> foundContacts = new ArrayList<>();
        System.out.println("Search by name\n");

        String name;
        do {
            System.out.print("Enter a name (first name, last name or both): ");
            name = sc.nextLine();
        } while (name.isEmpty() || namePattern.matcher(name).matches());

        for (Contact contact : contacts) {
            if (contact.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                    contact.getLastName().toLowerCase().contains(name.toLowerCase()) ||
                    (contact.getFirstName().toLowerCase() + " " + contact.getLastName()).toLowerCase().contains(name.toLowerCase()) ||
                    (contact.getLastName().toLowerCase() + " " + contact.getFirstName()).toLowerCase().contains(name.toLowerCase())) {
                foundContacts.add(contact);
            }
        }

        if (foundContacts.isEmpty()) {
            System.out.println("\nNo results.");
            System.out.println("\nPress ENTER to continue.");
            sc.nextLine();
            return;
        }

        for (Contact foundContact : foundContacts) {
            System.out.printf("%-16s%-5s%-8s%-25s %n", foundContact.getPhoneNumber(), foundContact.getFirstName(),
                    foundContact.getLastName(), foundContact.getEmail());
        }

        System.out.println("\nPress ENTER to continue.");
        sc.nextLine();
    }
}
