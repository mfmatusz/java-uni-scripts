package lab1.ex2;

import java.util.LinkedList;

/**
 * Lab 1 / Exercise 2 — Emergency Room Priority Queue
 *
 * Maintains patients in ascending priority order using a sorted linked list.
 * The patient with the lowest priority number is served first.
 *
 * Time complexity: O(n) insertion, O(1) removal.
 */
class EmergencyRoom {

    private final LinkedList<Patient> patients = new LinkedList<>();

    /** Inserts the patient in sorted order by priority (lowest first). */
    public void addPatient(Patient patient) {
        int insertIndex = 0;
        for (Patient existing : patients) {
            if (patient.compareTo(existing) >= 0) {
                insertIndex++;
            } else {
                break;
            }
        }
        patients.add(insertIndex, patient);
    }

    /** Returns and removes the highest-urgency (lowest priority number) patient. */
    public Patient getNextPatient() {
        return patients.poll();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EmergencyRoom{ \n");
        for (Patient patient : patients) {
            sb.append(patient).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
