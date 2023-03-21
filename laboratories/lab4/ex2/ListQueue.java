package lab4.ex2;

import java.util.Iterator;

public class ListQueue<Key> implements Iterable<Key> {
    private Node first; //najdawniej dodany wezel
    private Node last; //ostatnio dodany wezel
    private int N; //liczba elementow w kolejce

    public ListQueue() {
        this.last = this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Key item) { //dodanie na koniec listy
        Node<Key> oldlast = last; //zapamietnie ogona
        last = new Node<Key>(); //nowy wezel
        last.key = item; //ustawienie nowego elementu
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Key dequeue() {//usuwanie z poczatku listy
        Key item = (Key) first.key;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    public Iterator<Key> iterator() {
        return new ListIterator();
    }

        public void show() {
        ListIterator iterator = new ListIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private class ListIterator implements Iterator<Key> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            Key item = (Key) current.key;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }

    private class Node<Key> { //klasa pomocnicza wezla
        private Key key; // klucz
        private Node next;

        public Node(Key key) {
            this.key = key;
            this.next = null;
        }

        public Node() {
            this.key = null;
            this.next = null;
        }
    }
}
