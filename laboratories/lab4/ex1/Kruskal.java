package lab4.ex1;

import java.io.*;
import java.util.*;

public class Kruskal {
    private static List<Edge> edgesList;
    private static List<Set<Integer>> subtrees;
    private static int V;

    public static void kruskal() {
        double totalWeight = 0;
        System.out.print("Minimalne drzewo rozpinające:");
        while (!edgesList.isEmpty()){
            Edge current = poolMinimumEdge();
            Set<Integer> fromTree = findSubtree(current.from);
            Set<Integer> toTree = findSubtree(current.to);
            if (!fromTree.equals(toTree)) {
                unionSubtrees(fromTree, toTree);
                System.out.printf("\n%d-%d  %.2f",current.from,current.to,current.weight);
                totalWeight += current.weight;
            }
        }
        System.out.println("\nWaga: " + totalWeight);
    }

    static void unionSubtrees(Set<Integer> a, Set<Integer> b) {
        a.addAll(b);
        subtrees.remove(b);
    }

    private static Set<Integer> findSubtree(int vertex) {
        return subtrees.stream().filter(subtree -> subtree.contains(vertex)).findFirst().orElseThrow();
    }

    static Edge poolMinimumEdge() {
        Edge current = edgesList.get(0);
        edgesList.remove(0);
        return current;
    }

    private static void readFromFile(String filename) {
        edgesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            V = Integer.parseInt(scanner.nextLine());
            initializeSubtrees();
            int E = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNext()) {
                addEdge(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(filename + " can't be found");
        }
        edgesList.sort(new EdgeWeightComparator());
    }

    static void initializeSubtrees() {
        subtrees = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            Set<Integer> newSubtree = new HashSet<>();
            newSubtree.add(i);
            subtrees.add(newSubtree);
        }
    }

    private static void addEdge(String line) {
        String[] lineSplitted = line.split(" ");
        int from = Integer.parseInt(lineSplitted[0]);
        int to = Integer.parseInt(lineSplitted[1]);
        double weight = Double.parseDouble(lineSplitted[2]);
        edgesList.add(new Edge(from, to, weight));
    }

    public static void main(String[] args) {
        readFromFile("laboratories/lab4/g8.txt");
        kruskal();
    }
}