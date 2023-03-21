package lab4.ex2;

public class DirectEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectEdge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        if (w < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        if (Double.isNaN(weight)) throw new
                IllegalArgumentException("Blad! to nie jest liczba.");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f",
                weight);
    }
}
