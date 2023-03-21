package lab4.ex2;

/** A directed, weighted edge from vertex {@code v} to vertex {@code w}. */
public class DirectEdge {

    private final int v;
    private final int w;
    private final double weight;

    public DirectEdge(int v, int w, double weight) {
        if (v < 0 || w < 0) throw new IllegalArgumentException("Vertex index cannot be negative.");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight must be a number.");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from()       { return v; }
    public int to()         { return w; }
    public double weight()  { return weight; }

    @Override
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
