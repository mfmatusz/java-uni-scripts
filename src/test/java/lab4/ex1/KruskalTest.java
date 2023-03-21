package lab4.ex1;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {

    /**
     * Simple 4-vertex graph:
     *   0-1 weight 1.0
     *   1-2 weight 2.0
     *   0-2 weight 4.0  (should be excluded from MST)
     *   2-3 weight 3.0
     *
     * MST edges: 0-1 (1.0), 1-2 (2.0), 2-3 (3.0) → total 6.0
     */
    @Test
    void mstHasCorrectTotalWeight() {
        List<Edge> edges = List.of(
                new Edge(0, 1, 1.0),
                new Edge(1, 2, 2.0),
                new Edge(0, 2, 4.0),
                new Edge(2, 3, 3.0)
        );
        Kruskal kruskal = new Kruskal(4, edges);
        List<Edge> mst = kruskal.findMST();

        assertEquals(3, mst.size(), "MST of 4 vertices should have 3 edges");

        double totalWeight = mst.stream().mapToDouble(e -> e.weight).sum();
        assertEquals(6.0, totalWeight, 1e-9);
    }

    @Test
    void mstExcludesHeaviestEdgeThatWouldFormCycle() {
        List<Edge> edges = List.of(
                new Edge(0, 1, 1.0),
                new Edge(1, 2, 2.0),
                new Edge(0, 2, 4.0) // would create a cycle, should be excluded
        );
        Kruskal kruskal = new Kruskal(3, edges);
        List<Edge> mst = kruskal.findMST();

        assertEquals(2, mst.size());
        assertTrue(mst.stream().noneMatch(e -> e.weight == 4.0),
                "The 4.0-weight edge should not be in the MST");
    }

    @Test
    void mstFromFileHasKnownTotalWeight() throws FileNotFoundException {
        // g8.txt: 8 vertices, the known MST weight is 1.81
        Kruskal kruskal = Kruskal.fromFile("laboratories/lab4/g8.txt");
        List<Edge> mst = kruskal.findMST();

        assertEquals(7, mst.size());
        double total = mst.stream().mapToDouble(e -> e.weight).sum();
        assertEquals(1.81, total, 0.001);
    }
}
