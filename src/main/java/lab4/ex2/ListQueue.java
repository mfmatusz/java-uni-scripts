package lab4.ex2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A FIFO queue backed by a singly linked list. */
public class ListQueue<T> implements Iterable<T> {

    private Node<T> first; // oldest element
    private Node<T> last;  // newest element
    private int size;

    public boolean isEmpty() { return first == null; }
    public int size()        { return size; }

    public void enqueue(T item) {
        Node<T> oldLast = last;
        last = new Node<>(item);
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        T item = first.key;
        first = first.next;
        if (isEmpty()) last = null;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private static class Node<T> {
        T key;
        Node<T> next;

        Node(T key) { this.key = key; }
    }

    private class QueueIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override public boolean hasNext() { return current != null; }

        @Override public T next() {
            T item = current.key;
            current = current.next;
            return item;
        }
    }
}
