package lab3.ex1;

import java.util.HashMap;

/**
 * Lab 3 / Exercise 1 — LRU Cache
 *
 * A fixed-capacity cache that evicts the least-recently-used entry when full.
 * Internally maintained as a doubly linked list (most-recent at the tail)
 * backed by a HashMap for O(1) access.
 *
 * @param <K> key type
 * @param <V> value type
 */
public class LRUCache<K, V> {

    private final HashMap<K, Node<K, V>> cache = new HashMap<>();
    private Node<K, V> lru; // head — least recently used
    private Node<K, V> mru; // tail — most recently used
    private final int maxSize;
    private int currentSize;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        lru = new Node<>(null, null, null, null);
        mru = lru;
    }

    /**
     * Returns the value for the given key and marks it as most recently used.
     *
     * @throws IllegalArgumentException if the key is not present
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        if (node.key.equals(mru.key)) {
            return mru.value;
        }
        promoteToMru(node);
        return node.value;
    }

    /** Inserts or updates the entry and marks it as most recently used. */
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            if (!node.key.equals(mru.key)) {
                promoteToMru(node);
            }
        } else {
            Node<K, V> newNode = new Node<>(mru, null, key, value);
            mru.next = newNode;
            cache.put(key, newNode);
            mru = newNode;

            if (currentSize == maxSize) {
                cache.remove(lru.key);
                lru = lru.next;
                lru.previous = null;
            } else {
                if (currentSize == 0) {
                    lru = newNode;
                }
                currentSize++;
            }
        }
    }

    /** Unlinks node from its current position and moves it to the MRU end. */
    private void promoteToMru(Node<K, V> node) {
        Node<K, V> prev = node.previous;
        Node<K, V> next = node.next;

        if (node.key.equals(lru.key)) {
            next.previous = null;
            lru = next;
        } else {
            prev.next = next;
            next.previous = prev;
        }

        node.previous = mru;
        mru.next = node;
        mru = node;
        mru.next = null;
    }

    // -------------------------------------------------------------------------

    private static class Node<K, V> {
        Node<K, V> previous;
        Node<K, V> next;
        K key;
        V value;

        Node(Node<K, V> previous, Node<K, V> next, K key, V value) {
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}
