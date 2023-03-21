package lab1.ex2;

import java.util.LinkedList;

class EmergencyRoom {
    private LinkedList<Patient> patients = new LinkedList<>();

    public void addPatient(Patient patient) {
        int i = 0;
        for (Patient p : patients) {
            if (patient.compareTo(p) >= 0) {
                i++;
            } else {
                break;
            }
        }
        patients.add(i, patient);
    }

    public Patient getNextPatient() {
        return patients.poll();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder( "EmergencyRoom{ " + "\n");
        for (Patient patient : patients) {
            sb.append(patient.toString());
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}

