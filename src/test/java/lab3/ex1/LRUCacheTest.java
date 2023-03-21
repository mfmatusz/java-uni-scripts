package lab3.ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void getMissingKeyThrows() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(8);
        assertThrows(IllegalArgumentException.class, () -> cache.get(99));
    }

    @Test
    void leastRecentlyUsedEntryIsEvictedWhenFull() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 4);
        cache.put(3, 9);
        cache.put(4, 16); // evicts key 1 (LRU)

        assertThrows(IllegalArgumentException.class, () -> cache.get(1));
        assertEquals(4,  cache.get(2));
        assertEquals(9,  cache.get(3));
        assertEquals(16, cache.get(4));
    }

    @Test
    void getPromotesEntryToMruSoItIsNotEvicted() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 4);
        cache.put(3, 9);
        cache.get(1);    // promotes key 1; key 2 is now LRU
        cache.put(4, 16); // should evict key 2

        assertThrows(IllegalArgumentException.class, () -> cache.get(2));
        assertEquals(1,  cache.get(1));
        assertEquals(9,  cache.get(3));
        assertEquals(16, cache.get(4));
    }

    @Test
    void putUpdatesExistingValue() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(4);
        cache.put(1, 10);
        cache.put(1, 99);
        assertEquals(99, cache.get(1));
    }
}
