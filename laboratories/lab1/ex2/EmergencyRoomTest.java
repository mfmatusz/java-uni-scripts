package lab1.ex2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmergencyRoomTest {

    @Test
    void testAddPatient() {
        EmergencyRoom er = new EmergencyRoom();

        Patient p1 = new Patient("Joe", 2);
        Patient p2 = new Patient("Sarah", 1);
        Patient p3 = new Patient("Bob", 3);

        er.addPatient(p1);
        er.addPatient(p2);
        er.addPatient(p3);

        assertEquals(p2, er.getNextPatient());
        assertEquals(p1, er.getNextPatient());
        assertEquals(p3, er.getNextPatient());
    }

    @Test
    void testGetNextPatient() {
        EmergencyRoom er = new EmergencyRoom();
        assertNull(er.getNextPatient());
    }

    @Test
    void testToString() {
        EmergencyRoom er = new EmergencyRoom();

        Patient p1 = new Patient("Joe", 2);
        Patient p2 = new Patient("Sarah", 1);
        Patient p3 = new Patient("Bob", 3);

        er.addPatient(p1);
        er.addPatient(p2);
        er.addPatient(p3);

        String expected = "EmergencyRoom{ \n" +
                "Patient{name='Sarah', priority=1" + "}\n" +
                "Patient{name='Joe', priority=2"  + "}\n" +
                "Patient{name='Bob', priority=3" +  "}\n" +
                "}";

        assertEquals(expected, er.toString());
    }
}
