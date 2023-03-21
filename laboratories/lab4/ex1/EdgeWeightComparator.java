package lab4.ex1;

import java.util.Comparator;

class EdgeWeightComparator implements Comparator<Edge> {

    public int compare(Edge one, Edge two) {
        return Double.compare(one.weight, two.weight);
    }

    public String toString() {
        return "EdgeWeightComparator";
    }
}