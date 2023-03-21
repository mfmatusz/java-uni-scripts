package lab4.ex2;

public class DigraphWeighted {
    private final int V;
    private int E;
    private final Bag<DirectEdge>[] adj;

    public DigraphWeighted(int V) {
        if (V < 0) throw new IllegalArgumentException("Blad! liczba nie moze byc ujemna.");
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectEdge>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Blad! wierzcholek nie nalezy do grafu.");
    }

    public void addEdge(DirectEdge e) {
        int v = e.from();
        int w = e.to();
        validVertex(v);
        validVertex(w);
        adj[v].add(e);
        E++;
    }

    public Iterable<DirectEdge> adj(int v) {
        validVertex(v);
        return adj[v];
    }

    public Iterable<DirectEdge> edges() {
        Bag<DirectEdge> list = new Bag<DirectEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectEdge e : adj[v]) {
                s.append(e + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public Bag<DirectEdge>[] getAdj() {
        return adj;
    }
}

