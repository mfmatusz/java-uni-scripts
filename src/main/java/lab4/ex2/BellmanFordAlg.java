package lab4.ex2;

import java.util.Stack;

/**
 * Lab 4 / Exercise 2 — Bellman-Ford Shortest Path (queue-based / SPFA variant)
 *
 * Computes single-source shortest paths in a weighted digraph. Works correctly
 * with negative edge weights (but not negative cycles).
 *
 * @see DigraphWeighted
 */
public class BellmanFordAlg {

    private final double[] distTo;     // shortest distance from source to v
    private final DirectEdge[] edgeTo; // last edge on shortest path to v
    private final boolean[] onQueue;   // whether v is currently in the queue
    private final ListQueue<Integer> queue;

    public BellmanFordAlg(DigraphWeighted G, int source) {
        distTo  = new double[G.V()];
        edgeTo  = new DirectEdge[G.V()];
        onQueue = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;
        distTo[source] = 0.0;

        queue = new ListQueue<>();
        queue.enqueue(source);
        onQueue[source] = true;

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    /**
     * Relaxes all edges leaving vertex v: if a shorter path to some neighbour
     * is found, update distTo/edgeTo and enqueue the neighbour.
     */
    private void relax(DigraphWeighted G, int v) {
        for (DirectEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
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
        Stack<DirectEdge> path = new Stack<>();
        for (DirectEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= distTo.length)
            throw new IllegalArgumentException("Vertex " + v + " is out of range.");
    }
}
