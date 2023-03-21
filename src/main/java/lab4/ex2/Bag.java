package lab4.ex2;

import java.util.Iterator;

/** A simple unordered bag (multi-set) backed by a singly linked list. */
class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    public boolean isEmpty() { return first == null; }
    public int size()        { return size; }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) sb.append(item).append(" ");
        return sb.toString();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class BagIterator implements Iterator<Item> {
        private Node current = first;

        @Override public boolean hasNext() { return current != null; }

        @Override public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
