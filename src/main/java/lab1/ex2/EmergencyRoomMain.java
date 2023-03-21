package lab1.ex2;

/** Demonstrates the EmergencyRoom priority queue with sample patients. */
public class EmergencyRoomMain {

    public static void main(String[] args) {
        EmergencyRoom emergencyRoom = new EmergencyRoom();
        emergencyRoom.addPatient(new Patient("Saul",    1));
        emergencyRoom.addPatient(new Patient("Jesse",   2));
        emergencyRoom.addPatient(new Patient("Mike",    1));
        emergencyRoom.addPatient(new Patient("Walter",  4));
        emergencyRoom.addPatient(new Patient("Skyler",  3));
        emergencyRoom.addPatient(new Patient("Greta",   3));
        emergencyRoom.addPatient(new Patient("Summer",  4));

        System.out.println(emergencyRoom);

        emergencyRoom.addPatient(new Patient("Jacob", 1));
        Patient next = emergencyRoom.getNextPatient();
        System.out.println("Next patient: " + next.getName());
        System.out.println(emergencyRoom);
    }
}
