package lab4.ex2;

import java.io.*;
import java.util.Scanner;

public class GraphTest {

    public static DigraphWeighted generateGraphWeightedFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine();
            int vertices = Integer.parseInt(line);
            DigraphWeighted digraphWeighted = new DigraphWeighted(vertices);
            line = reader.readLine();
            int edges = Integer.parseInt(line);
            line = reader.readLine();
            for (int i = 0; i < edges; i++) {
                String[] edgeData = line.split(" ");
                int v = Integer.parseInt(edgeData[0]);
                int w = Integer.parseInt(edgeData[1]);
                double weight = Double.parseDouble(edgeData[2]);
                digraphWeighted.addEdge(new DirectEdge(v, w, weight));
                line = reader.readLine();
            }
            return digraphWeighted;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        DigraphWeighted digraphWeighted =generateGraphWeightedFromFile("laboratories/lab4/ex2.txt");

        int choice = 4;
        while (choice != 0) {
            System.out.println("\nWybierz co chcesz zrobić: " +
                    "\n1 - wypisz graf na konsolę" +
                    "\n2 - znajdź najkrótsze drogi do wierzchołków"+
                    "\n3 - zapisać do pliku najkrótszą ścieżkę z wierzchołka zerowego"+
                    "\n0 - wyjście z programu");
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    System.out.println("source->destination  weight");
                    for (Bag<DirectEdge> bag :
                            digraphWeighted.getAdj()) {
                        System.out.println(bag);
                    }
                    break;
                case 2:
                    System.out.println("Z którego wierzchołka ruszamy?");
                    int s = new Scanner(System.in).nextInt();
                    System.out.println("Jak dokładny opis procesu chcesz zobaczyć?"+
                            "\n1 - niedokładny"+
                            "\n2 - dokładny");
                    int b = new Scanner(System.in).nextInt();
                    boolean beVerbose = b == 2;
                    BellmanFordAlg bf = new BellmanFordAlg(digraphWeighted, 1, beVerbose);
                    for (int v = 0; v < digraphWeighted.V(); v++) {
                        if (bf.hasPathTo(v)) {
                            System.out.printf("Z %d do %d (%5.2f) ", s, v,
                                    bf.distTo(v));
                            for (DirectEdge e : bf.pathTo(v)) {
                                System.out.print(e + " ");
                            }
                            System.out.println();
                        } else {
                            System.out.printf("Nie istnieje ścieżka z %d do %d\n", s, v);
                        }
                    }
                    break;
                case 3:
                    PrintWriter zapis = new PrintWriter("zad3.txt");
                    System.out.println("Do jakiego wierzchołka chcesz ścieżkę?");
                    int scan = new Scanner(System.in).nextInt();
                    BellmanFordAlg bmf = new BellmanFordAlg(digraphWeighted, 1, false);
                    if (bmf.hasPathTo(scan)) {
                        System.out.printf("Z %d do %d (suma: %5.2f) ", 0, scan,
                                bmf.distTo(scan));
                        zapis.printf("Z %d do %d (suma: %5.2f) ", 0, scan,
                                bmf.distTo(scan));
                        for (DirectEdge e : bmf.pathTo(scan)) {
                            System.out.println();
                            System.out.print(e + " ");
                            zapis.println(e + " ");
                        }
                        System.out.println();
                        System.out.println(0 + "->" + 1 + " " + "0,00");
                        zapis.println(0 + "->" + 1 + " " + "0.00");
                    }
                    zapis.close();

                    break;
            }

        }

    }


}
