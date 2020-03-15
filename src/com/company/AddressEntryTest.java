package com.company;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for AddressEntry
 */
public class AddressEntryTest {

    @org.junit.jupiter.api.Test
    void testToString() {

        AddressEntry entry1 = new AddressEntry( 1,"Jenny", "Patterson", "2452 Bermuda Ave",
                "San Leandro", "CA", 94577, "510-566-9569", "jenny@gmail.com");

        AddressEntry entry2 = new AddressEntry( 2, "Carl", "Patterson", "2452 Bermuda Ave",
                "San Leandro", "CA", 94577, "510-833-6270", "carl@gmail.com");

        // test AddressEntry 1
        assertEquals( "Jenny Patterson\n2452 Bermuda Ave\nSan Leandro" + ", " + "CA" + ", " + 94577
                + "\n510-566-9569\njenny@gmail.com\n", entry1.toString() );

        // test AddressEntry 2
        assertEquals( "Carl Patterson\n2452 Bermuda Ave\nSan Leandro, CA, " + 94577
                + "\n510-833-6270\ncarl@gmail.com\n", entry2.toString() );
    }

    @org.junit.jupiter.api.Test
    void setFirstName() {
        AddressEntry entry = new AddressEntry();

        // first assert that default first name is ""
        assertEquals( "", entry.getFirstName() );

        // next change the first name of the entry and assert that the new name is correct
        entry.setFirstName( "Alyssa" );
        assertEquals( "Alyssa", entry.getFirstName() );
    }

    @org.junit.jupiter.api.Test
    void setLastName() {
        AddressEntry entry = new AddressEntry();

        // first assert that default last name is ""
        assertEquals( "", entry.getLastName() );

        // next change the last name of the entry and assert that the new name is correct
        entry.setLastName( "Ochoa" );
        assertEquals( "Ochoa", entry.getLastName() );
    }

    @org.junit.jupiter.api.Test
    void setStreet() {
        AddressEntry entry = new AddressEntry();

        // first assert that default street name is ""
        assertEquals( "", entry.getStreet() );

        // next change the street name of the entry and assert that the new name is correct
        entry.setStreet( "321 Tulip Ln" );
        assertEquals( "321 Tulip Ln", entry.getStreet() );
    }

    @org.junit.jupiter.api.Test
    void setCity() {
        AddressEntry entry = new AddressEntry();

        // first assert that default city name is ""
        assertEquals( "", entry.getCity() );

        // next change the city name of the entry and assert that the new name is correct
        entry.setCity( "Carlstown" );
        assertEquals( "Carlstown", entry.getCity() );
    }

    @org.junit.jupiter.api.Test
    void setState() {
        AddressEntry entry = new AddressEntry();

        // first assert that default state name is ""
        assertEquals( "", entry.getState() );

        // next change the state name of the entry and assert that the new name is correct
        entry.setState( "Oregon" );
        assertEquals( "Oregon", entry.getState() );
    }

    @org.junit.jupiter.api.Test
    void setZip() {
        AddressEntry entry = new AddressEntry();

        // first assert that default zip code is 0
        assertEquals( 0, entry.getZip() );

        // next change the zip code of the entry and assert that the new name is correct
        entry.setZip( 94577 );
        assertEquals( 94577, entry.getZip() );
    }

    @org.junit.jupiter.api.Test
    void setPhone() {
        AddressEntry entry = new AddressEntry();

        // first assert that default phone number is ""
        assertEquals( "", entry.getTelephone() );

        // next change the phone number of the entry and assert that the new name is correct
        entry.setTelephone( "510-566-9569" );
        assertEquals( "510-566-9569", entry.getTelephone() );
    }

    @org.junit.jupiter.api.Test
    void setEmail() {
        AddressEntry entry = new AddressEntry();

        // first assert that default email is ""
        assertEquals( "", entry.getEmail() );

        // next change the first name of the entry and assert that the new name is correct
        entry.setEmail( "b.jennings@gmail.com" );
        assertEquals( "b.jennings@gmail.com", entry.getEmail() );
    }

    @org.junit.jupiter.api.Test
    void getFirstName() {
        AddressEntry entry = new AddressEntry();

        // first assert that default first name is ""
        assertEquals( "", entry.getFirstName() );

        // next change the first name of the entry and assert that the new name is correct
        entry.setFirstName( "Alyssa" );
        assertEquals( "Alyssa", entry.getFirstName() );
    }

    @org.junit.jupiter.api.Test
    void getLastName() {
        AddressEntry entry = new AddressEntry();

        // first assert that default last name is ""
        assertEquals( "", entry.getLastName() );

        // next change the last name of the entry and assert that the new name is correct
        entry.setLastName( "Ochoa" );
        assertEquals( "Ochoa", entry.getLastName() );
    }

    @org.junit.jupiter.api.Test
    void getStreet() {
        AddressEntry entry = new AddressEntry();

        // first assert that default street name is ""
        assertEquals( "", entry.getStreet() );

        // next change the street name of the entry and assert that the new name is correct
        entry.setStreet( "321 Tulip Ln" );
        assertEquals( "321 Tulip Ln", entry.getStreet() );
    }

    @org.junit.jupiter.api.Test
    void getCity() {
        AddressEntry entry = new AddressEntry();

        // first assert that default city name is ""
        assertEquals( "", entry.getCity() );

        // next change the city name of the entry and assert that the new name is correct
        entry.setCity( "Carlstown" );
        assertEquals( "Carlstown", entry.getCity() );
    }

    @org.junit.jupiter.api.Test
    void getState() {
        AddressEntry entry = new AddressEntry();

        // first assert that default state name is ""
        assertEquals( "", entry.getState() );

        // next change the state name of the entry and assert that the new name is correct
        entry.setState( "Oregon" );
        assertEquals( "Oregon", entry.getState() );
    }

    @org.junit.jupiter.api.Test
    void getZip() {
        AddressEntry entry = new AddressEntry();

        // first assert that default zip code is 0
        assertEquals( 0, entry.getZip() );

        // next change the zip code of the entry and assert that the new name is correct
        entry.setZip( 94577 );
        assertEquals( 94577, entry.getZip() );
    }

    @org.junit.jupiter.api.Test
    void getTelephone() {
        AddressEntry entry = new AddressEntry();

        // first assert that default phone number is ""
        assertEquals( "", entry.getTelephone() );

        // next change the phone number of the entry and assert that the new name is correct
        entry.setTelephone( "510-566-9569" );
        assertEquals( "510-566-9569", entry.getTelephone() );
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        AddressEntry entry = new AddressEntry();

        // first assert that default email is ""
        assertEquals( "", entry.getEmail() );

        // next change the first name of the entry and assert that the new name is correct
        entry.setEmail( "b.jennings@gmail.com" );
        assertEquals( "b.jennings@gmail.com", entry.getEmail() );
    }
}
