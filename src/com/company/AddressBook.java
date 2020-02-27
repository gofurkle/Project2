package com.company;

import java.io.*;
import java.util.*;

/**
 * Maintains a list of AddressEntry's
 * @author Jamie Nguyen
 */
public class AddressBook {
    /**
     * ArrayList to hold all AddressEntry's
     */
    public ArrayList<AddressEntry> contacts;

    /**
     * Default constructor
     * Initializes an empty ArrayList to hold AddressEntry's
     */
    AddressBook() {
        contacts = new ArrayList<AddressEntry>();
    }

    /**
     * Adds specified AddressEntry to the list then sorts the list to maintain alphabetical order.
     * @param entry Specified AddressEntry to be added to the list of contacts
     */
    public void add(AddressEntry entry) {
        contacts.add(entry);
        contacts.sort(new ContactComparator());
    }

    /**
     * Removes the specified AddressEntry
     * @param entry Specified AddressEntry to be removed from the list of contacts
     */
    public void remove(AddressEntry entry) {
        contacts.remove(entry);
    }

    /**
     * Finds all AddressEntry's with the specified prefix in their lastName.
     * @param lastNamePrefix Any string, including the empty string
     * @return A list of all AddressEntry's with the specified lastNamePrefix. If user inputs the empty string a list of all AddressEntry's will be returned.
     */
    public ArrayList<AddressEntry> find(String lastNamePrefix) {
        if(lastNamePrefix.equals("")) {
            return this.contacts;
        } else {
            ArrayList<AddressEntry> result = new ArrayList<AddressEntry>();
            for(int i = 0; i < contacts.size(); i++) {
                // Checks if the current AddressEntry's lastName starts with lastNamePrefix
                if(contacts.get(i).lastName.startsWith(lastNamePrefix)) {
                    result.add(contacts.get(i));
                }
            }
            return result;
        }
    }

    /**
     * Goes through the list of AddressEntry's using an iterator and displays the contents of each AddressEntry
     */
    public void list() {
        Iterator it = contacts.iterator();
        while(it.hasNext()) {
            System.out.print(it.next()+"\n");
        }
    }

    /**
     * Initializes the AddressBook with AddressEntry's from a specially formatted
     * @param filename Specially formatted text file
     * @throws FileNotFoundException
     */
    public void readFromFile(String filename) throws FileNotFoundException {
        try {
            File in = new File("src/com/company/" + filename);            //
            BufferedReader inputBuffer = new BufferedReader(new FileReader(in));
            String line;                                // Temporary string to hold data from current line
            int lineNum = 0;                            // Keeps count of lines to help with parsing input file
            AddressEntry entry = new AddressEntry();    // Temporary AddressEntry to hold values

            /* Goes through the specially formatted file line by line, creates new AddressEntry's and adds
             * them to the AddressBook
             */
            while((line = inputBuffer.readLine()) != null) {
                if(lineNum % 8 == 0) {
                    entry.firstName = line;
                }
                if(lineNum % 8 == 1) {
                    entry.lastName = line;
                }
                if(lineNum % 8 == 2) {
                    entry.street = line;
                }
                if(lineNum  % 8 == 3) {
                    entry.city = line;
                }
                if(lineNum % 8 == 4) {
                    entry.state = line;
                }
                if(lineNum % 8 == 5) {
                    entry.zip = Integer.parseInt(line);
                }
                if(lineNum % 8 == 6) {
                    entry.email = line;
                }
                if(lineNum % 8 == 7) {
                    entry.telephone = line;
                    this.add(entry);
                    entry = new AddressEntry();
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Custom compare method used to sort the list of AddressEntry's in AddressBook
 */
class ContactComparator implements Comparator<AddressEntry> {
    /**
     * Overrides the compare method to sort AddressEntry's.
     * Used to sort based on last name, but if last names are the same, sorts by first name;
     * @param e1
     * @param e2
     * @return int value containing the result of compareTo()
     */
    @Override
    public int compare(AddressEntry e1, AddressEntry e2) {
        int lastNameComp = (e1.lastName).compareTo(e2.lastName);
        if(lastNameComp != 0) {
            return (e1.lastName).compareTo(e2.lastName);
        } else {
            return (e1.firstName).compareTo(e2.firstName);
        }
    }
}
