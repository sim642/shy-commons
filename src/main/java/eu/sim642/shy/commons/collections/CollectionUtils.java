package eu.sim642.shy.commons.collections;

import java.util.List;
import java.util.Map;

/**
 * Utilities class for working with collections.
 */
public final class CollectionUtils {
    private CollectionUtils() {

    }

    /**
     * Swaps the values at the specified keys in the specified map.
     * (If the specified keys are equal, invoking this method leaves the map unchanged.)
     *
     * @param map map in which to swap values
     * @param key1 key of one value to be swapped
     * @param key2 key of the other value to be swapped
     * @param <K> type of the keys of the map
     * @param <V> type of the values of the map
     * @see java.util.Collections#swap(List, int, int)
     */
    public static <K, V> void swap(Map<K, V> map, K key1, K key2) {
        map.put(key1, map.put(key2, map.get(key1)));
    }
}
