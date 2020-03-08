package com.company;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Maintains a list of AddressEntry's
 * @author Emmanuel Gallegos & Jamie Nguyen
 */
public class AddressBook {
    /**
     * ArrayList to hold all AddressEntry's
     */
    DefaultListModel<AddressEntry> addressEntryList;

    /**
     * Default constructor
     * Initializes an empty ArrayList to hold AddressEntry's
     */
    AddressBook() {
        addressEntryList = new DefaultListModel<AddressEntry>();
    }

    public void add(int ID, String firstName, String lastName, String street, String city, String state, int zip,
                    String telephone, String email) {
        AddressEntry newEntry = new AddressEntry(ID, firstName, lastName, street, city, state, zip, telephone, email);
        this.add(newEntry);
    }
    /**
     * Adds specified AddressEntry to the list then sorts the list to maintain alphabetical order.
     * @param entry Specified AddressEntry to be added to the list of addressEntryList
     */
    public void add(AddressEntry entry) {
        /**
         * index of where entry is to be inserted
         */
        int indexToAdd = 0;
        /*
         * While we haven't fallen off array and our entry's last name is lexicographically greater than our
         * current search index's last name, advance to next index
         */
        while( indexToAdd < addressEntryList.size() &&
                entry.getLastName().compareTo( addressEntryList.get( indexToAdd ).getLastName() ) > 0 ) {
            indexToAdd++;
        }
        /*
        if we haven't hit the end of the list, and the two last names are identical,
        search according to first name as well
        */
        if( indexToAdd != addressEntryList.size() &&
                entry.getLastName().compareTo( addressEntryList.get( indexToAdd ).getLastName() ) == 0 )
        {
            while( indexToAdd < addressEntryList.size() &&
                    entry.getFirstName().compareTo( addressEntryList.get( indexToAdd ).getFirstName() ) > 0 &&
                    entry.getLastName().compareTo( addressEntryList.get( indexToAdd ).getLastName() ) == 0 )
            {
                indexToAdd++;
            }
        }
        // once appropriate position has been found, add entry to addressBook
        addressEntryList.add( indexToAdd, entry );
    }

    /**
     * Removes the specified AddressEntry
     * @param entry Specified AddressEntry to be removed from the list of addressEntryList
     */
    public void remove(AddressEntry entry) {
        addressEntryList.removeElement(entry);
    }

    /**
     * Finds all AddressEntry's with the specified prefix in their lastName.
     * @param lastNamePrefix Any string, including the empty string
     * @return A list of all AddressEntry's with the specified lastNamePrefix. If user inputs the empty string a list of all AddressEntry's will be returned.
     */
    public  DefaultListModel<AddressEntry> find(String lastNamePrefix) {
        if(lastNamePrefix.equals("")) {
            return this.addressEntryList;
        } else {
            DefaultListModel<AddressEntry> result = new DefaultListModel<AddressEntry>();
            for(int i = 0; i < addressEntryList.size(); i++) {
                // Checks if the current AddressEntry's lastName starts with lastNamePrefix
                if(addressEntryList.get(i).lastName.startsWith(lastNamePrefix)) {
                    result.addElement(addressEntryList.get(i));
                }
            }
            return result;
        }
    }

    /**
     * Goes through the list of AddressEntry's using an iterator and displays the contents of each AddressEntry
     */
    public void list() {
        for( int i = 0; i < addressEntryList.size(); i++) {
            System.out.print(addressEntryList.get(i) + "\n");
        }
    }
}
