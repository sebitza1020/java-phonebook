package com.ausy.sebastian.phonebook.controller;


import java.lang.reflect.InvocationTargetException;

/**
 * This interface defines add/edit, find and retrieve methods, which needs to be implemented in a separate class.
 */
public interface PhoneBook {
    /**
     * This is just a display menu.
     */
    void printMenu();

    /**
     * This method is used to add a new or edit an existing contact.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    void addEditPhoneNumber() throws InvocationTargetException, IllegalAccessException;

    /**
     * This method is used to add a contact using the phone number as a parameter.
     * @param phoneNumber String - it uses a regex validation, if the validation is true, the method can be used, if not, it can't.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    void addContact(String phoneNumber) throws InvocationTargetException, IllegalAccessException;

    /**
     * This method is used to find a contact by the phone number.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    void findContactByPhoneNumber() throws InvocationTargetException, IllegalAccessException;

    /**
     * This method is used to retrieve all available contacts.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    void getAllContacts() throws InvocationTargetException, IllegalAccessException;

    /**
     * This method is used to find a contact by typing the first name, the last name, or both names.
     */
    void findContactByName();

    /**
     * This method is used to retrieve all available contacts from the database, especially used at the beginning of the display menu, if there are contacts.
     * @return The number of contacts in the database.
     * @throws InvocationTargetException a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    int getAllContactsFromDB() throws InvocationTargetException, IllegalAccessException;
}
