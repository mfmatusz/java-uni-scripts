package lab4.ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Interactive CLI for exploring a weighted digraph loaded from a file.
 *
 * File format (same as Sedgewick's tinyEWDG.txt):
 *   Line 1: number of vertices
 *   Line 2: number of edges
 *   Lines 3+: "from to weight"
 *
 * Usage: java lab4.ex2.GraphReader [file-path]
 * Default file: laboratories/lab4/ex2.txt
 */
public class GraphReader {

    public static DigraphWeighted loadFromFile(String filepath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            int vertices = Integer.parseInt(reader.readLine().trim());
            int edges    = Integer.parseInt(reader.readLine().trim());
            DigraphWeighted graph = new DigraphWeighted(vertices);
            for (int i = 0; i < edges; i++) {
                String[] parts = reader.readLine().trim().split("\\s+");
                graph.addEdge(new DirectEdge(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2])
                ));
            }
            return graph;
        }
    }

    public static void main(String[] args) throws IOException {
        String path = args.length > 0 ? args[0] : "laboratories/lab4/ex2.txt";
        DigraphWeighted graph = loadFromFile(path);
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nChoose an action:");
            System.out.println("  1 - Print graph");
            System.out.println("  2 - Find shortest paths from a vertex (Bellman-Ford)");
            System.out.println("  3 - Save shortest path from vertex 0 to file (shortest_path.txt)");
            System.out.println("  0 - Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println(graph);
                case 2 -> {
                    System.out.print("Source vertex: ");
                    int src = scanner.nextInt();
                    BellmanFordAlg bf = new BellmanFordAlg(graph, src);
                    for (int v = 0; v < graph.V(); v++) {
                        if (bf.hasPathTo(v)) {
                            System.out.printf("%d to %d (%.2f):  ", src, v, bf.distTo(v));
                            for (DirectEdge e : bf.pathTo(v)) System.out.print(e + "  ");
                            System.out.println();
                        } else {
                            System.out.printf("No path from %d to %d%n", src, v);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Target vertex: ");
                    int target = scanner.nextInt();
                    BellmanFordAlg bf = new BellmanFordAlg(graph, 0);
                    try (PrintWriter writer = new PrintWriter("shortest_path.txt")) {
                        if (bf.hasPathTo(target)) {
                            writer.printf("0 to %d (total: %.2f)%n", target, bf.distTo(target));
                            for (DirectEdge e : bf.pathTo(target)) writer.println(e);
                        } else {
                            writer.printf("No path from 0 to %d%n", target);
                        }
                    }
                    System.out.println("Written to shortest_path.txt");
                }
            }
        }
    }
}
