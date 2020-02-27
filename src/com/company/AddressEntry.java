package com.company;

/**
 * Maintains information about an entry's name, address, phone number, and email address
 * @author Jamie Nguyen
 */
public class AddressEntry {
    /**
     * Stores the id for this AddressEntry
     */
    int id;

    /**
     * Stores the first name of the person of this AddressEntry
     */
    String firstName;

    /**
     * Stores the first name of the person of this AddressEntry
     */
    String lastName;

    /**
     * Stores the street of the person of this AddressEntry
     */
    String street;

    /**
     * Stores the city of the person of this AddressEntry
     */
    String city;

    /**
     * Stores the state of the person of this AddressEntry
     */
    String state;

    /**
     * Stores the zip code of the person of this AddressEntry
     */
    int zip;

    /**
     * Stores the telephone number of the person of this AddressEntry
     */
    String telephone;

    /**
     * Stores the email address of the person of this AddressEntry
     */
    String email;

    /**
     * Default constructor. Sets all fields blank
     */
    AddressEntry() {
        this.firstName = "";
        this.lastName = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.zip = 0;
        this.telephone = "";
        this.email = "";
    }

    /**
     * Entry Constructor
     * @param first first name
     * @param last last name
     * @param street street
     * @param city city
     * @param state state
     * @param zip zip code
     * @param tele telephone number
     * @param email email address
     */
    AddressEntry(String first, String last, String street, String city, String state, int zip, String email, String tele) {
        this.firstName = first;
        this.lastName = last;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephone = tele;
        this.email = email;
    }

    AddressEntry(int id, String first, String last, String street, String city, String state, int zip, String email, String tele) {
        this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephone = tele;
        this.email = email;
    }

    public AddressEntry(String text, String text1, String text2, String text3, String text4) {
    }

    /**
     * Sets this AddressEntry's firstName field to the string specified by first
     * @param first Specified first name string
     */
    public void setFirstName(String first) {
        this.firstName = first;
    }

    /**
     * Returns this AddressEntry's firstName
     * @return This AddressEntry's first name field
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets this AddressEntry's lastName field to the string specified by last
     * @param last Specified last name string
     */
    public void setLastName(String last) {
        this.lastName = last;
    }

    /**
     * Returns this AddressEntry's lastName
     * @return This AddressEntry's last name field
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets this AddressEntry's street field to the string specified by street
     * @param street Specified street string
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns this AddressEntry's street
     * @return This AddressEntry's street field
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Sets this AddressEntry's city field to the string specified by city
     * @param city Specified city string
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns this AddressEntry's city
     * @return This AddressEntry's city field
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets this AddressEntry's state field to the string specified by state
     * @param state Specified state string
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns this AddressEntry's state
     * @return This AddressEntry's state field
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets this AddressEntry's lastName zip to the string specified by zip
     * @param zip Specified zip string
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Returns this AddressEntry's zip
     * @return This AddressEntry's zip field
     */
    public int getZip() {
        return this.zip;
    }

    /**
     * Sets this AddressEntry's telephone field to the string specified by tele
     * @param tele Specified telephone string
     */
    public void setTelephone(String tele) {
        this.telephone = tele;
    }

    /**
     * Returns this AddressEntry's telephone
     * @return telephone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Sets this AddressEntry's email field to the string specified by email
     * @param email Specified email string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns this AddressEntry's email
     * @return This AddressEntry's email field
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns a string containing contents of this AddressEntry separated by new lines
     * @return formatted string that displays the contents of this AddressEntry
     */
    public String toString() {
        String entry = String.format
                ("%s %s\n%s\n%s, %s, %d\n%s\n%s\n",
                        firstName, lastName, street, city, state, zip, email, telephone);
        return entry;
    }
}