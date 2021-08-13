package com.ausy.sebastian.phonebook.model;

/**
 * Contact is the class where we define the phone number as an identification key, name, first name, email and contact address.
 */
public class Contact {
    private final String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    /**
     * The constructor takes five parameters and initializes a new Contact object.
     * @param phoneNumber String
     * @param firstName String
     * @param lastName String
     * @param email String
     * @param address String
     */
    public Contact(String phoneNumber, String firstName, String lastName, String email, String address) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    /**
     * This method returns the phone number of an object.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method returns the first name of an object.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method saves the first name in the object.
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method returns the last name of an object.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method saves the last name in the object.
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method returns the email of an object.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method saves the email in the object.
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns the address of an object.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method saves the address in the object.
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
