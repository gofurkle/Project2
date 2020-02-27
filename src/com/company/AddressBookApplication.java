//package com.company;
//import java.io.*;
//import java.util.*;
//
///**
// * Runs a menu of options allowing the user to load entries from a file, add or remove entries, search
// * the address book for an entry/entries, or display all entries
// * @author Jamie Nguyen
// */
//public class AddressBookApplication {
//    public static void main(String[] args) throws FileNotFoundException {
//        AddressBook ab = new AddressBook();
//
//        Scanner scanTask = new Scanner(System.in);
//        boolean menu = true;
//        String task = "";
//        while(menu) {
//            // Prints to console menu options for the user to choose
//            System.out.println("Please enter in your menu selection");
//            System.out.println("a) Load from File\n" +
//                               "b) Add new entry\n"  +
//                               "c) Remove entry\n"   +
//                               "d) Find entry\n"     +
//                               "e) List entry\n"     +
//                               "f) Quit");
//
//            task = scanTask.nextLine();
//            ArrayList<AddressEntry> entriesFound;
//            Scanner scanPrefix = new Scanner(System.in);
//            String prefix;
//
//            switch(task) {
//                case "a":   // Load from file
//                    Scanner scanFileName = new Scanner(System.in);
//                    System.out.println("Enter file name: ");
//                    String fileName = scanFileName.nextLine();
//                    ab.readFromFile(fileName);
//                    break;
//                case "b":   // Add an entry
//                    String firstName = Menu.prompt_FirstName();
//                    String lastName = Menu.prompt_LastName();
//                    String street = Menu.prompt_Street();
//                    String city = Menu.prompt_City();
//                    String state = Menu.prompt_State();
//                    int zip = Menu.prompt_Zip();
//                    String phoneNumber = Menu.prompt_Telephone();
//                    String email = Menu.prompt_Email();
//                    AddressEntry newEntry = new AddressEntry(firstName, lastName, street, city, state, zip, phoneNumber, email);
//                    ab.add(newEntry);
//                    break;
//                case "c":   // Remove an entry
//                    // Get user input for prefix of last name to remove
//                    System.out.println("Enter the last name of the contact you want to remove: ");
//                    prefix = scanPrefix.nextLine();
//
//                    // Gets list of contacts with specified prefix in their last name and prints to console
//                    entriesFound = ab.find(prefix);
//                    if(entriesFound.size() > 0) {
//                        System.out.printf("The following %d entries were found in the AddressBook that match your search criteria. Select the" +
//                                " number for the entry you wish to remove:\n", entriesFound.size());
//                        for(int i = 0; i < entriesFound.size(); i++) {
//                            System.out.printf("%d)\n%s", i+1, entriesFound.get(i));
//                        }
//                    } else {
//                        break;
//                    }
//
//                    // Get user input for which contact with specified prefix in their last name to remove
//                    Scanner scanSelection = new Scanner(System.in);
//                    Scanner scanConfirm = new Scanner(System.in);
//                    String selection = scanSelection.nextLine();
//                    int selectionIndex = Integer.parseInt(selection)-1;
//                    String confirmation;
//
//                    // Request user input until valid input, either 'y' or 'n'
//                    do {
//                        System.out.println("Hit 'y' to remove the following entry or 'n' to return to the main menu:");
//                        System.out.print(entriesFound.get(selectionIndex));
//                        confirmation = scanConfirm.nextLine();
//                        if(!confirmation.equals("y") && !confirmation.equals("n")) {
//                            System.out.println("Invalid entry!");
//                        }
//                    } while(!confirmation.equals("y") && !confirmation.equals("n"));
//
//                    ab.remove(entriesFound.get(selectionIndex));
//                    break;
//                case "d":   // Find an entry(s)
//                    // Get user input for prefix of last name
//                    System.out.println("Enter in all or the beginning of the last name of the contact you wish to find: ");
//                    prefix = scanPrefix.nextLine();
//
//                    // Gets list of contacts with specified prefix in their last name and prints to console
//                    entriesFound = ab.find(prefix);
//                    System.out.printf("The following %d entries were found in the AddressBook for a last name starting with '%s':\n",
//                            entriesFound.size(), prefix);
//                    for(int i = 0; i < entriesFound.size(); i++) {
//                        System.out.printf("%d)\n%s", i+1, entriesFound.get(i));
//                    }
//                    break;
//                case "e":   // List all entry(s)
//                    ab.list();
//                    break;
//                case "f":
//                    menu = false;
//                    System.out.print("Goodbye!");
//                    System.exit(0);
//            }
//        }
//    }
//}
