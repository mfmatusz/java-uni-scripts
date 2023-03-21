package lab1.ex2;

import java.util.Objects;

/**
 * Represents a patient in an emergency room with a numeric priority.
 * Lower priority number means higher urgency (priority 1 is seen first).
 */
class Patient implements Comparable<Patient> {

    private final String name;
    private final int priority;

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient patient)) return false;
        return priority == patient.priority && name.equals(patient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority);
    }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', priority=" + priority + "}";
    }
}
