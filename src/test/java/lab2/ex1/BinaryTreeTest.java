package lab2.ex1;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void inOrderTraversalReturnsElementsAscending() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int v : new int[]{5, 3, 7, 1, 2, 6, 8, 4}) tree.add(v);

        Iterator<Integer> it = tree.iterator();
        for (int expected : new int[]{1, 2, 3, 4, 5, 6, 7, 8}) {
            assertTrue(it.hasNext());
            assertEquals(expected, it.next());
        }
        assertFalse(it.hasNext());
    }

    @Test
    void singleNodeTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);

        Iterator<Integer> it = tree.iterator();
        assertTrue(it.hasNext());
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void emptyTreeIteratorHasNoNext() {
        assertFalse(new BinaryTree<Integer>().iterator().hasNext());
    }
}
