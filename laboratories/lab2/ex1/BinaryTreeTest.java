package lab2.ex1;

import org.junit.Test;
import org.junit.Assert;
import java.util.Iterator;

public class BinaryTreeTest {

    @Test
    public void testBinaryTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(2);
        tree.add(6);
        tree.add(8);
        tree.add(4);
        Iterator<Integer> iter = tree.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(4), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(5), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(6), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(7), iter.next());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(8), iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testDegenerateTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        Iterator<Integer> iter = tree.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(5), iter.next());
        Assert.assertFalse(iter.hasNext());
    }
}

