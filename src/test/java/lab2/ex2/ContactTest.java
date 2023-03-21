package lab2.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    private Contact contacts;

    @BeforeEach
    void setUp() {
        contacts = new Contact();
        contacts.add("John Smith");
        contacts.add("Jane Doe");
        contacts.add("Jack Johnson");
        contacts.add("Kagiso Ejiroghene");
        contacts.add("Fouad Maqsud");
        contacts.add("Fryderyk Mariusz");
    }

    @Test
    void findByPrefixReturnsAllMatches() {
        List<String> results = contacts.find("J");
        assertEquals(3, results.size());
        assertTrue(results.containsAll(List.of("John Smith", "Jane Doe", "Jack Johnson")));
    }

    @Test
    void findByLongerPrefixNarrowsResults() {
        List<String> results = contacts.find("Fry");
        assertEquals(1, results.size());
        assertTrue(results.contains("Fryderyk Mariusz"));
    }

    @Test
    void findWithNoMatchReturnsEmptyList() {
        assertTrue(contacts.find("Mari").isEmpty());
    }

    @Test
    void findExactNameReturnsItself() {
        List<String> results = contacts.find("Jane Doe");
        assertEquals(1, results.size());
        assertEquals("Jane Doe", results.get(0));
    }
}
