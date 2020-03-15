package com.company;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Test class for AddressBook
 */
public class AddressBookTest {

    AddressBook ab = new AddressBook(); // generic address book for testing
    InputStream in;  // for generating fake stream of possible user data
    ByteArrayOutputStream generatedOutput = new ByteArrayOutputStream();    // for capturing console output

    @org.junit.jupiter.api.BeforeEach
    public void init(){
        // generate and add 2 dummy entries
        AddressEntry entry1 = new AddressEntry(1, "Jenny", "Patterson", "2452 Bermuda Ave",
                "San Leandro", "CA", 94577, "510-566-9569", "jenny@gmail.com");

        AddressEntry entry2 = new AddressEntry(2,  "Carl", "Patterson", "2452 Bermuda Ave",
                "San Leandro", "CA", 94577, "510-833-6270", "carl@gmail.com");
        ab.add( entry1 );
        ab.add( entry2 );
        // double check that size is 2
        assertEquals( 2, ab.addressEntryList.size() );
    }

    @org.junit.jupiter.api.Test
    void find() {
        // check that two entries are returned for perfect match
        assertEquals( 2, ab.find( "Patterson" ).size() );
        // check that two entries are returned for first three letters
        assertEquals( 2, ab.find( "Pat" ).size() );
        // check that zero entries are returned for non-match
        assertEquals( 0, ab.find( "Zap" ).size() );
        // generate and new entry
        AddressEntry entry3 = new AddressEntry(1, "Frank", "Zappa", "303 Howard Ln",
                "San Leandro", "CA", 94577, "510-566-9569", "jenny@gmail.com");
        ab.add( entry3 );
        // check that one entry is found for query
        assertEquals( 1, ab.find( "Z" ).size() );
    }

    @org.junit.jupiter.api.Test
    void add() {
        // check that initial size is 2
        assertEquals( 2, ab.addressEntryList.size() );
        // generate 2 new entries
        AddressEntry entry3 = new AddressEntry(1, "Frank", "Zappa", "303 Howard Ln",
                "San Leandro", "CA", 94577, "510-566-9569", "jenny@gmail.com");

        AddressEntry entry4 = new AddressEntry( 2,"John", "Lennon", "123 Abbey Rd",
                "Liverpool", "UK", 101, "510-432-4567", "jlennon@gmail.com");
        // add 1 new entry
        ab.add( entry3 );
        // check that new size is 3
        assertEquals( 3, ab.addressEntryList.size() );
        // add 1 new entry
        ab.add( entry4 );
        // check that new size is 4
        assertEquals( 4, ab.addressEntryList.size() );
    }

    @org.junit.jupiter.api.Test
    void remove() {
        // check that initial size is 2
        assertEquals( 2, ab.addressEntryList.size());
        // remove 0'th element calling MY remove method
        ab.remove(ab.addressEntryList.elementAt(0));
        // check that new size is 1
        assertEquals( 1, ab.addressEntryList.size());
    }
}
