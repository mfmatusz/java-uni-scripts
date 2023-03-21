package lab1.ex2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmergencyRoomTest {

    @Test
    void patientsAreReturnedInPriorityOrder() {
        EmergencyRoom er = new EmergencyRoom();
        er.addPatient(new Patient("Joe",   2));
        er.addPatient(new Patient("Sarah", 1));
        er.addPatient(new Patient("Bob",   3));

        assertEquals(new Patient("Sarah", 1), er.getNextPatient());
        assertEquals(new Patient("Joe",   2), er.getNextPatient());
        assertEquals(new Patient("Bob",   3), er.getNextPatient());
    }

    @Test
    void getNextPatientOnEmptyRoomReturnsNull() {
        assertNull(new EmergencyRoom().getNextPatient());
    }

    @Test
    void toStringListsPatientsInOrder() {
        EmergencyRoom er = new EmergencyRoom();
        er.addPatient(new Patient("Joe",   2));
        er.addPatient(new Patient("Sarah", 1));
        er.addPatient(new Patient("Bob",   3));

        String expected =
                "EmergencyRoom{ \n" +
                "Patient{name='Sarah', priority=1}\n" +
                "Patient{name='Joe', priority=2}\n" +
                "Patient{name='Bob', priority=3}\n" +
                "}";
        assertEquals(expected, er.toString());
    }
}
