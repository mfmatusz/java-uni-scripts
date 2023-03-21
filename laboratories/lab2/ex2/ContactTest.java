package lab2.ex2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ContactTest {

    private Contact contactManager;

    @Before
    public void setUp() {
        contactManager = new Contact();
        contactManager.add("John Smith");
        contactManager.add("Jane Doe");
        contactManager.add("Jack Johnson");

        contactManager.add("Kagiso Ejiroghene");
        contactManager.add("Fouad Maqsud");
        contactManager.add("Fryderyk Mariusz");
    }

    @Test
    public void testFindContacts() {
        List<String> contacts = contactManager.find("J");
        assertEquals(3, contacts.size());

        assertTrue(contacts.contains("Jane Doe"));
        assertTrue(contacts.contains("Jack Johnson"));
        assertTrue(contacts.contains("John Smith"));

        contacts = contactManager.find("Fry");
        assertEquals(1, contacts.size());

        assertTrue(contacts.contains("Fryderyk Mariusz"));
    }

    @Test
    public void testFindContactsNoResults() {
        List<String> contacts = contactManager.find("Mari");
        assertEquals(0, contacts.size());
    }

}

