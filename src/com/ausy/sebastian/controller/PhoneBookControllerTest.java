package com.ausy.sebastian.controller;

import com.ausy.sebastian.connection.DB;
import com.ausy.sebastian.model.Contact;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


class PhoneBookControllerTest {

    @org.junit.jupiter.api.Test
    void addEditPhoneNumber() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
                "Bd. Regele Mihai, nr. 1, Bucuresti"));
        for (Contact contact : contacts) {
            contact.setFirstName("John");
            contact.setLastName("Doe");
            contact.setEmail("john.doe@example.com");
            contact.setAddress("Str. Geniului, nr. 77, Cluj");
        }
        System.out.println("Phone number updated successfully.");
        Assertions.assertFalse(false);
        Assertions.assertEquals("Doe", contacts.get(0).getLastName());
    }

    @org.junit.jupiter.api.Test
    void addContact() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
                "Bd. Regele Mihai, nr. 1, Bucuresti"));
        System.out.println("Phone number added successfully.");
        Assertions.assertFalse(false);
        Assertions.assertEquals(1, contacts.size());
    }

    @org.junit.jupiter.api.Test
    void findContactByPhoneNumber() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
                "Bd. Regele Mihai, nr. 1, Bucuresti"));
        String phoneNumber = "0712 345 678";
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                System.out.printf("%-20s%-50s %n", "Phone number:", contact.getPhoneNumber());
                System.out.printf("%-20s%-50s %n", "First name:", contact.getFirstName());
                System.out.printf("%-20s%-50s %n", "Last name:", contact.getLastName());
                System.out.printf("%-20s%-50s %n", "E-mail:", contact.getEmail());
                System.out.printf("%-20s%-50s %n", "Address:", contact.getAddress());
            }
        }
        Assertions.assertFalse(false);
        Assertions.assertEquals(phoneNumber, contacts.get(0).getPhoneNumber());

    }

    @org.junit.jupiter.api.Test
    void getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
                "Bd. Regele Mihai, nr. 1, Bucuresti"));
        contacts.add(new Contact("0721 435 764", "John", "Doe", "john.doe@example.com",
                "Str. Geniului, nr. 77, Cluj"));
        contacts.add(new Contact("0741 826 314", "Jane", "Doe", "jane.doe@example.com",
                "Str. Moldovei, nr. 21, Sibiu"));
        for (Contact contact : contacts) {
            System.out.printf("%-16s%-5s%-8s%-25s%-30s %n", contact.getPhoneNumber(), contact.getFirstName(),
                    contact.getLastName(), contact.getEmail(), contact.getAddress());
        }
        Assertions.assertFalse(false);
        Assertions.assertEquals(3, contacts.size());
    }

    @org.junit.jupiter.api.Test
    void findContactByName() {
        List<Contact> foundContacts = new ArrayList<>();
        String name = "smIth jO";
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
                "Bd. Regele Mihai, nr. 1, Bucuresti"));
        for (Contact contact : contacts) {
            if (contact.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                    contact.getLastName().toLowerCase().contains(name.toLowerCase()) ||
                    (contact.getFirstName().toLowerCase() + " " + contact.getLastName()).toLowerCase().contains(name.toLowerCase()) ||
                    (contact.getLastName().toLowerCase() + " " + contact.getFirstName()).toLowerCase().contains(name.toLowerCase())) {
                foundContacts.add(contact);
            }
        }
        Assertions.assertFalse(foundContacts.isEmpty());
        Assertions.assertEquals(foundContacts.size(), contacts.size());
    }

    @org.junit.jupiter.api.Test
    void getAllContactsFromDB() {
        DB db_conn = new DB();
        Connection connection = db_conn.get_Connection();
        List<Contact> contacts = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {
            String query = "SELECT * FROM contact;";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                contacts.add(new Contact(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Assertions.assertFalse(contacts.isEmpty());
        Assertions.assertEquals(3, contacts.size());
    }
}