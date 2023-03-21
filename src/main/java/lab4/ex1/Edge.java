package lab4.ex1;

/** Represents an undirected weighted edge between two vertices. */
class Edge {
    final int from;
    final int to;
    final double weight;

    Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return from + "-" + to + " (" + weight + ")";
    }
}
