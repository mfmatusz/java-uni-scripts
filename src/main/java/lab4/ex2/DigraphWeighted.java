package lab4.ex2;

/**
 * A weighted directed graph represented as an adjacency list of {@link Bag}s.
 */
public class DigraphWeighted {

    private final int V;
    private int E;
    private final Bag<DirectEdge>[] adj;

    @SuppressWarnings("unchecked")
    public DigraphWeighted(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices cannot be negative.");
        this.V = V;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) adj[v] = new Bag<>();
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(DirectEdge e) {
        validateVertex(e.from());
        validateVertex(e.to());
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Bag<DirectEdge>[] getAdj() {
        return adj;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex " + v + " is out of range [0, " + (V - 1) + "].");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ").append(adj[v]).append("\n");
        }
        return sb.toString();
    }
}
