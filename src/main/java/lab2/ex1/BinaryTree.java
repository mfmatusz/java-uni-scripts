package lab2.ex1;

import java.util.Iterator;
import java.util.Stack;

/**
 * Lab 2 / Exercise 1 — Binary Search Tree with in-order iterator
 *
 * A generic BST that supports in-order (ascending) traversal via the
 * {@link Iterable} interface. Duplicates are placed in the right subtree.
 */
class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;

    /** Inserts a value into the tree. */
    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root.add(value);
        }
    }

    /** Returns an in-order iterator over the tree's elements. */
    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    // -------------------------------------------------------------------------

    private static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }

        void add(T value) {
            if (value.compareTo(this.value) < 0) {
                if (left == null) left = new Node<>(value);
                else left.add(value);
            } else {
                if (right == null) right = new Node<>(value);
                else right.add(value);
            }
        }
    }

    private static class InOrderIterator<T extends Comparable<T>> implements Iterator<T> {
        private Node<T> current;
        private final Stack<Node<T>> stack = new Stack<>();

        InOrderIterator(Node<T> root) {
            this.current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public T next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            T value = current.value;
            current = current.right;
            return value;
        }
    }
}
