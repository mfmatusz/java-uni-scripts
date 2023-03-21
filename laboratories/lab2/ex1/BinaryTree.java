package lab2.ex1;

import java.util.Iterator;
import java.util.Stack;

class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;

    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root.add(value);
        }
    }

    public Iterator<T> iterator() {
        return new TreeIterator<>(root);
    }

    private static class Node<T extends Comparable<T>> {

        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
        }

        public void add(T value) {
            if (value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = new Node<>(value);
                } else {
                    left.add(value);
                }
            } else {
                if (right == null) {
                    right = new Node<>(value);
                } else {
                    right.add(value);
                }
            }
        }
    }

    private static class TreeIterator<T extends Comparable<T>> implements Iterator<T> {

        private Node<T> current;
        private Stack<Node<T>> stack;

        public TreeIterator(Node<T> root) {
            current = root;
            stack = new Stack<>();
        }

        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

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

