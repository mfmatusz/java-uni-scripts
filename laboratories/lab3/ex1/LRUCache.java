package lab3.ex1;

import java.util.HashMap;

public class LRUCache<K, V>{
    class Node<T, U> {
        Node<T, U> previous;
        Node<T, U> next;
        T key;
        U value;

        public Node(Node<T, U> previous, Node<T, U> next, T key, U value){
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<K, Node<K, V>> cache;
    private Node<K, V> leastRecentlyUsed;
    private Node<K, V> mostRecentlyUsed;
    private int maxSize;
    private int currentSize;

    public LRUCache(int maxSize){
        this.maxSize = maxSize;
        this.currentSize = 0;
        leastRecentlyUsed = new Node<K, V>(null, null, null, null);
        mostRecentlyUsed = leastRecentlyUsed;
        cache = new HashMap<K, Node<K, V>>();
    }

    public V get(K key){
        Node<K, V> node = cache.get(key);
        if (node == null){
            throw new IllegalArgumentException();
        }
        else if (node.key == mostRecentlyUsed.key){
            return mostRecentlyUsed.value;
        }


        Node<K, V> nextNode = node.next;
        Node<K, V> previousNode = node.previous;

        if (node.key == leastRecentlyUsed.key){
            nextNode.previous = null;
            leastRecentlyUsed = nextNode;
        }

        else if (node.key != mostRecentlyUsed.key){
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        node.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = node;
        mostRecentlyUsed = node;
        mostRecentlyUsed.next = null;

        return node.value;
    }

    public void put(K key, V value){
        if (cache.containsKey(key)){
            Node<K, V> node = cache.get(key);
            node.value = value;

            Node<K, V> nextNode = node.next;
            Node<K, V> previousNode = node.previous;

            if (node.key == leastRecentlyUsed.key && node.key != mostRecentlyUsed.key){
                nextNode.previous = null;
                leastRecentlyUsed = nextNode;
            }

            else if (node.key != mostRecentlyUsed.key){
                previousNode.next = nextNode;
                nextNode.previous = previousNode;

                node.previous = mostRecentlyUsed;
                mostRecentlyUsed.next = node;
                mostRecentlyUsed = node;
                mostRecentlyUsed.next = null;
            }
        }

        else {
            Node<K, V> myNode = new Node<K, V>(mostRecentlyUsed, null, key, value);
            mostRecentlyUsed.next = myNode;
            cache.put(key, myNode);
            mostRecentlyUsed = myNode;

            if (currentSize == maxSize){
                cache.remove(leastRecentlyUsed.key);
                leastRecentlyUsed = leastRecentlyUsed.next;
                leastRecentlyUsed.previous = null;
            }

            else if (currentSize < maxSize){
                if (currentSize == 0){
                    leastRecentlyUsed = myNode;
                }
                currentSize++;
            }
        }
    }
}
