package lab4.ex2;

import java.util.Iterator;

class Bag<Item> implements Iterable<Item> {
    private Node first;  //wierzcholek stosu
    private int N;      //liczba elementow


    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Item> iterator = iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString()).append(" ");
        }
        return sb.toString();
    }

    private class Node {
        //klasa wewnetrzna z def. wezlow
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }
}
