package lab4.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Lab 4 / Exercise 1 — Kruskal's Minimum Spanning Tree Algorithm
 *
 * Builds the MST by greedily picking the cheapest edge that connects
 * two previously disconnected components (union-find via set merging).
 *
 * Time complexity: O(E log E) dominated by sorting edges.
 */
public class Kruskal {

    private final List<Edge> edges;
    private final int vertexCount;

    public Kruskal(int vertexCount, List<Edge> edges) {
        this.vertexCount = vertexCount;
        this.edges = new ArrayList<>(edges);
        Collections.sort(this.edges, (a, b) -> Double.compare(a.weight, b.weight));
    }

    /**
     * Runs Kruskal's algorithm and returns the edges in the MST.
     *
     * @return list of MST edges (size = vertexCount - 1 for a connected graph)
     */
    public List<Edge> findMST() {
        List<Set<Integer>> components = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            Set<Integer> s = new HashSet<>();
            s.add(i);
            components.add(s);
        }

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            Set<Integer> fromComponent = findComponent(components, edge.from);
            Set<Integer> toComponent   = findComponent(components, edge.to);
            if (!fromComponent.equals(toComponent)) {
                fromComponent.addAll(toComponent);
                components.remove(toComponent);
                mst.add(edge);
            }
        }
        return mst;
    }

    private Set<Integer> findComponent(List<Set<Integer>> components, int vertex) {
        return components.stream()
                .filter(s -> s.contains(vertex))
                .findFirst()
                .orElseThrow();
    }

    public static Kruskal fromFile(String filename) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int v = Integer.parseInt(scanner.nextLine().trim());
            int e = Integer.parseInt(scanner.nextLine().trim());
            List<Edge> edges = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                edges.add(new Edge(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2])
                ));
            }
            return new Kruskal(v, edges);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = args.length > 0 ? args[0] : "laboratories/lab4/g8.txt";
        Kruskal kruskal = fromFile(path);
        List<Edge> mst = kruskal.findMST();

        System.out.println("Minimum Spanning Tree:");
        double total = 0;
        for (Edge e : mst) {
            System.out.printf("  %d-%d  %.2f%n", e.from, e.to, e.weight);
            total += e.weight;
        }
        System.out.printf("Total weight: %.2f%n", total);
    }
}
