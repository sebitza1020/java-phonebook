package com.ausy.sebastian.phonebook.controller;

import com.ausy.sebastian.phonebook.connection.Db;
import com.ausy.sebastian.phonebook.model.Contact;
import com.ausy.sebastian.phonebook.repository.PhoneBook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class is used to implement the interface's methods.
 */
public class PhoneBookImpl implements PhoneBook {
    Scanner scanner = new Scanner(System.in);
    Db dbConn = Db.getInstance();
    Method method = dbConn.getClass().getDeclaredMethod("getConnection");
    List<Contact> contacts = new ArrayList<>();
    Pattern phonePattern = Pattern.compile("^07[1-9][0-9]\\s\\d{3}\\s\\d{3}$");
    Pattern namePattern = Pattern.compile("^[A-Za-z]$");
    String phoneNumber;
    int count = getAllContactsFromDB();

    /**
     * This class is used to implement the interface's methods.
     * @throws NoSuchMethodException when a particular method cannot be found.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    public PhoneBookImpl() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    }

    public void printMenu() {
        System.out.println("Phone Book");
        System.out.println("There are currently " + count + " contacts in the phone book.\n");
        System.out.println(" 1. Add or edit a contact.");
        System.out.println(" 2. View all contacts.");
        System.out.println(" 3. Find a contact by phone number.");
        System.out.println(" 4. Find contacts by name.");
        System.out.println(" 0. Exit");
        System.out.print("\nSelect an option: ");
    }

    public void addEditPhoneNumber() throws InvocationTargetException, IllegalAccessException {
        System.out.println("Add/edit a contact\n");

        do {
            System.out.print("Enter a phone number: ");
            phoneNumber = scanner.nextLine();
        } while (phoneNumber.isEmpty() || !phonePattern.matcher(phoneNumber).matches());

        if (phonePattern.matcher(phoneNumber).matches()) {
            if (count == 0) {
                addContact(phoneNumber);
                return;
            }
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    System.out.println("This phone number already exists. Editing an existing entry.");

                    String firstName;
                    do {
                        System.out.print("First Name: ");
                        firstName = scanner.nextLine();
                    } while (firstName.isEmpty() || namePattern.matcher(firstName).matches());
                    contact.setFirstName(firstName);

                    String lastName;
                    do {
                        System.out.print("Last Name: ");
                        lastName = scanner.nextLine();
                    } while (lastName.isEmpty() || namePattern.matcher(lastName).matches());
                    contact.setLastName(lastName);

                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    contact.setEmail(email);

                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    contact.setAddress(address);

                    PreparedStatement ps;
                    method.setAccessible(true);
                    Connection connection = (Connection) method.invoke(dbConn);
                    try {
                        String query = "UPDATE contact SET first_name=?,last_name=?,email=?,address=? WHERE phone_number=?;";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, contact.getFirstName());
                        ps.setString(2, contact.getLastName());
                        ps.setString(3, contact.getEmail());
                        ps.setString(4, contact.getAddress());
                        ps.setString(5, contact.getPhoneNumber());
                        ps.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("\nPhone book was updated successfully.\n");
                    System.out.println("Press ENTER to continue.");
                    scanner.nextLine();
                    return;
                }
            }
            addContact(phoneNumber);
        }
    }

    public void addContact(String phoneNumber) throws InvocationTargetException, IllegalAccessException {
        System.out.println("This phone number is new. Adding a new entry to the phone book.");

        String firstName;
        do {
            System.out.print("First Name: ");
            firstName = scanner.nextLine();
        } while (firstName.isEmpty() || namePattern.matcher(firstName).matches());

        String lastName;
        do {
            System.out.print("Last Name: ");
            lastName = scanner.nextLine();
        } while (lastName.isEmpty() || namePattern.matcher(lastName).matches());

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        PreparedStatement ps;
        method.setAccessible(true);
        Connection connection = (Connection) method.invoke(dbConn);
        try {
            String query = "INSERT INTO contact(phone_number,first_name,last_name,email,address) VALUES (?,?,?,?,?);";
            ps = connection.prepareStatement(query);
            ps.setString(1, phoneNumber);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPhone book was updated successfully.\n");
        System.out.println("Press ENTER to continue.");
        scanner.nextLine();
    }

    public void findContactByPhoneNumber() throws InvocationTargetException, IllegalAccessException {
        System.out.println("Search by phone number\n");

        do {
            System.out.print("Enter a phone number: ");
            phoneNumber = scanner.nextLine();
        } while (phoneNumber.isEmpty() || !phonePattern.matcher(phoneNumber).matches());

        if (phonePattern.matcher(phoneNumber).matches()) {
            PreparedStatement ps;
            ResultSet rs;
            method.setAccessible(true);
            Connection connection = (Connection) method.invoke(dbConn);

            try {
                String query = "SELECT * FROM contact WHERE phone_number=?;";
                ps = connection.prepareStatement(query);
                ps.setString(1, phoneNumber);
                rs = ps.executeQuery();

                if (rs.next()) {
                    System.out.printf("%-20s%-50s %n", "Phone number:", rs.getString("phone_number"));
                    System.out.printf("%-20s%-50s %n", "First name:", rs.getString("first_name"));
                    System.out.printf("%-20s%-50s %n", "Last name:", rs.getString("last_name"));
                    System.out.printf("%-20s%-50s %n", "E-mail:", rs.getString("email"));
                    System.out.printf("%-20s%-50s %n", "Address:", rs.getString("address"));
                    System.out.println("Press ENTER to continue.");
                    scanner.nextLine();
                    return;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nThe phone number could not be found in the address book.\n");
            System.out.println("Press ENTER to continue.");
            scanner.nextLine();
        }
    }

    public void getAllContacts() throws InvocationTargetException, IllegalAccessException {

        System.out.println("Contact list\n");
        PreparedStatement ps;
        ResultSet rs;
        method.setAccessible(true);
        Connection connection = (Connection) method.invoke(dbConn);
        try {
            String query = "SELECT * FROM contact;";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.printf("%-16s%-5s%-8s%-25s %n", rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nPress ENTER to continue.");
        scanner.nextLine();
    }

    public void findContactByName() {
        List<Contact> foundContacts = new ArrayList<>();
        System.out.println("Search by name\n");

        String name;
        do {
            System.out.print("Enter a name (first name, last name or both): ");
            name = scanner.nextLine();
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
            scanner.nextLine();
            return;
        }

        for (Contact foundContact : foundContacts) {
            System.out.printf("%-16s%-5s%-8s%-25s %n", foundContact.getPhoneNumber(), foundContact.getFirstName(),
                    foundContact.getLastName(), foundContact.getEmail());
        }

        System.out.println("\nPress ENTER to continue.");
        scanner.nextLine();
    }

    public int getAllContactsFromDB() throws InvocationTargetException, IllegalAccessException {
        int count = 0;
        PreparedStatement ps;
        ResultSet rs;
        method.setAccessible(true);
        Connection connection = (Connection) method.invoke(dbConn);
        try {
            String query = "SELECT * FROM contact;";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                contacts.add(new Contact(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
                count++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
