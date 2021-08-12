package com.ausy.sebastian.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ContactTest {
    static Contact contact = new Contact("0712 345 678", "John", "Smith", "john.smith@example.com",
            "Bd. Regele Mihai, nr. 1, Bucuresti");

    @BeforeAll
    static void getContact() {
        System.out.printf("%-16s%-5s%-8s%-25s%-30s %n", contact.getPhoneNumber(), contact.getFirstName(),
                contact.getLastName(), contact.getEmail(), contact.getAddress());
    }

    @Test
    void getPhoneNumber() {
        Assertions.assertEquals("0712 345 678", contact.getPhoneNumber());
    }

    @Test
    void getFirstName() {
        Assertions.assertEquals("John", contact.getFirstName());
    }

    @Test
    void setFirstName() {
        String firstName = "Jane";
        contact.setFirstName(firstName);
        Assertions.assertEquals(firstName, contact.getFirstName());
    }

    @Test
    void setLastName() {
        String lastName = "Doe";
        contact.setLastName(lastName);
        Assertions.assertEquals(lastName, contact.getLastName());
    }

    @Test
    void getLastName() {
        Assertions.assertEquals("Doe", contact.getLastName());
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("jane.doe@example.com", contact.getEmail());
    }

    @Test
    void setEmail() {
        String email = "jane.doe@example.com";
        contact.setEmail(email);
        Assertions.assertEquals(email, contact.getEmail());
    }

    @Test
    void getAddress() {
        Assertions.assertEquals("Bd. Regele Mihai, nr. 1, Bucuresti", contact.getAddress());
    }

    @Test
    void setAddress() {
        String address = "Str. Moldovei, nr. 21, Sibiu";
        contact.setAddress(address);
        Assertions.assertEquals(address, contact.getAddress());
    }

    @AfterAll
    static void getNewContact() {
        System.out.printf("%-16s%-5s%-8s%-25s%-30s %n", contact.getPhoneNumber(), contact.getFirstName(),
                contact.getLastName(), contact.getEmail(), contact.getAddress());
    }
}