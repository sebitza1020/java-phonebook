package com.ausy.sebastian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneBookImpl implements PhoneBook {
    Scanner sc = new Scanner(System.in);
    DB db_conn = new DB();
    Connection connection = db_conn.get_Connection();
    List<Contact> contacts = new ArrayList<>();
    Pattern phonePattern = Pattern.compile("^07[1-9][0-9]\\s\\d{3}\\s\\d{3}$");
    Pattern namePattern = Pattern.compile("^[A-Za-z]$");
    String phoneNumber;
    int ct = getAllContactsFromDB();

    public void printMenu() {
        System.out.println("Phone Book");
        System.out.println("There are currently " + ct + " contacts in the phone book.\n");
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
            if (ct == 0) {
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

                    PreparedStatement ps;
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

        PreparedStatement ps;
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
            PreparedStatement ps;
            ResultSet rs;

            try {
                String query = "SELECT * FROM contact WHERE phone_number=?;";
                ps = connection.prepareStatement(query);
                ps.setString(1, phoneNumber);
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.printf("%-20s%-50s %n", "Phone number:", rs.getString("phone_number"));
                    System.out.printf("%-20s%-50s %n", "First name:", rs.getString("first_name"));
                    System.out.printf("%-20s%-50s %n", "Last name:", rs.getString("last_name"));
                    System.out.printf("%-20s%-50s %n", "E-mail:", rs.getString("email"));
                    System.out.printf("%-20s%-50s %n", "Address:", rs.getString("address"));
                    System.out.println("Press ENTER to continue.");
                    sc.nextLine();
                }

            } catch (Exception e) {
                System.out.println("The phone number could not be found in the address book.\n");
                System.out.println("Press ENTER to continue.");
                sc.nextLine();
            }
        }
    }

    public void getAllContacts() {

        System.out.println("Contact list\n");
        PreparedStatement ps;
        ResultSet rs;
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

    public int getAllContactsFromDB() {
        int count = 0;
        PreparedStatement ps;
        ResultSet rs;
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
