package lab4.ex2;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class BellmanFordAlg {
    private final double[] distTo; //dlugosc sciezki s->v
    private final DirectEdge[] edgeTo; //ostatnia krawedz sciezki s->v
    private final boolean[] onQueue; //obecnosc v w kolejce
    private final ListQueue<Integer> queue; //wierzcholki w kolejce
    private final boolean verbose;

    public BellmanFordAlg(DigraphWeighted G, int s, boolean verbose) {
        this.verbose = verbose;
        distTo = new double[G.V()];
        edgeTo = new DirectEdge[G.V()];
        onQueue = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        //Algorytm Bellmana-Forda
        queue = new ListQueue<>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    //Relaksacja krawędzi v− >w oznacza sprawdzenie, czy
    //najlepsza znana droga z s do w prowadzi z s do v, a następnie
    //krawędzią z v do w. Jeśli tak jest, należy zaktualizować
    //struktury danych, by uwzględnić te informacje.
    private void relax(DigraphWeighted G, int v) {
        System.out.println("Relaksacja krawędzi " + G);
        for (DirectEdge e : G.adj(v)) {
            int w = e.to();
            if(verbose)System.out.println("Porównanie odległości do " + w + " i " + v);
            if (distTo[w] > distTo[v] + e.weight()) {
                if (verbose) {
                    System.out.println("Odległość do " + w + " jest większa niż odległość do " + v);
                }
                if(verbose) System.out.println("Aktualizowanie odległości do " + w);
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    if (verbose) System.out.println("Dodawanie do kolejki " + w);
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectEdge> path = new
                Stack<>();
        for (DirectEdge e = edgeTo[v]; e != null; e =
                edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Blad! wierzcholek nie nalezy do grafu.");
    }

}
