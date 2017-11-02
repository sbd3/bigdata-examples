package geeksforgeeks.basic;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private static LinkedHashMap<Integer, Integer> cache;

    /* Inititalize an LRU cache with size N */
    public LRUCache(int N) {
        cache = new LinkedHashMap<Integer, Integer>(N, .75f, true) {
            private static final long serialVersionUID = 1L;
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > N;
            }
        };
    }

    /*
     * Returns the value of the key x if present else returns -1
     */
    public int get(int x) {
        return cache.containsKey(x) ? cache.get(x) : -1;
    }

    /* Sets the key x with value y in the LRU cache */
    public void set(int x, int y) {
        cache.put(x, y);
    }
}
