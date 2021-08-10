package com.ausy.sebastian;

import java.util.List;

public interface PhoneBook {
    void printMenu();

    void addEditPhoneNumber();

    void addContact(String phoneNumber);

    void findContactByPhoneNumber();

    void getAllContacts();

    void findContactByName();

    int getAllContactsFromDB();
}
