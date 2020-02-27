package com.company;

import java.io.*;
import java.util.Scanner;

/**
 * Collection of static methods to get input from user and return a string
 * @author Jamie Nguyen
 */
public class Menu {
    /**
     * Prompts user to input a first name and returns it
     * @return firstName string input from user
     */
    static String prompt_FirstName() {
        Scanner input = new Scanner(System.in);
        String firstName;
        do {
            System.out.println("First Name: ");
            firstName = input.nextLine();
            if(!firstName.matches("[a-zA-Z]+|^$")) {
                System.out.println("Invalid input. Please enter a valid name.");
            }
        } while(!firstName.matches("[a-zA-Z]+|^$"));
        return firstName;
    }

    /**
     * Prompts user to input a last name and returns it
     * @return lastName string input from user
     */
    static String prompt_LastName() {
        Scanner input = new Scanner(System.in);
        String lastName;
        do {
            System.out.println("Last Name: ");
            lastName = input.nextLine();
            if(!lastName.matches("[a-zA-Z]+|^$")) {
                System.out.println("Invalid input. Please enter a valid name.");
            }
        } while(!lastName.matches("[a-zA-Z]+|^$"));
        return lastName;
    }

    /**
     * Prompts user to input a street and returns it
     * @return street string input from user
     */
    static String prompt_Street() {
        Scanner input = new Scanner(System.in);
        String street;
        do {
            System.out.println("Street: ");
            street = input.nextLine();
            if(!street.matches("[a-zA-Z0-9 ]+|^$")) {
                System.out.println("Invalid input. Please enter a valid street.");
            }
        } while(!street.matches("[a-zA-Z0-9 ]+|^$"));
        return street;
    }

    /**
     * Prompts user to input city and returns it
     * @return city string input from user
     */
    static String prompt_City() {
        Scanner input = new Scanner(System.in);
        String city;
        do {
            System.out.println("City: ");
            city = input.nextLine();
            if(!city.matches("[a-zA-Z ]+|^$")) {
                System.out.println("Invalid input. Please enter a valid city.");
            }
        } while(!city.matches("[a-zA-Z ]+|^$"));
        return city;
    }

    /**
     * Prompts user to input state and returns it
     * @return state string input from user
     */
    static String prompt_State() {
        Scanner input = new Scanner(System.in);
        String state;
        do {
            System.out.println("State: ");
            state = input.nextLine();
            if(!state.matches("[a-zA-Z]+|^$")) {
                System.out.println("Invalid input. Please enter a valid state.");
            }
        } while(!state.matches("[a-zA-Z]+|^$"));
        return state;
    }

    /**
     * Prompts user to input zip code and returns it
     * @return zip string input from user
     */
    static int prompt_Zip() {
        Scanner input = new Scanner(System.in);
        String zip;
        do {
            System.out.println("Zip Code: ");
            zip = input.nextLine();
            if(!zip.matches("\\d\\d\\d\\d\\d|^$")) {
                System.out.println("Invalid zip code. Please enter a valid zip code.");
            }
        } while(!zip.matches("\\d\\d\\d\\d\\d|^$"));
        return Integer.parseInt(zip);
    }

    /**
     * Prompts user to input telephone number and returns it
     * @return phoneNum string input from user
     */
    static String prompt_Telephone() {
        Scanner input = new Scanner(System.in);
        String phoneNum;
        do {
            System.out.println("Phone Number: xxx-aaa-bbb");
            phoneNum = input.nextLine();
            if(!phoneNum.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d|^$")) {
                System.out.println("Invalid phone number. Please follow the format specified for entering a phone number");
            }
        } while(!phoneNum.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d|^$"));
        return phoneNum;
    }

    /**
     * Prompts user to input email address
     * @return email string input from user
     */
    static String prompt_Email() {
        Scanner input = new Scanner(System.in);
        String email;

        // Checks user input for a valid email or empty string if the user leaves line blank
        do {
            System.out.println("Email: ");
            email = input.nextLine();
            if(!email.matches("\\w+@[a-zA-Z]+\\.[a-zA-Z]+|^$")) {
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        } while(!email.matches("\\w+@[a-zA-Z]+\\.[a-zA-Z]+|^$"));
        return email;
    }
}