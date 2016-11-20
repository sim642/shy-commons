package eu.sim642.shy.commons.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utilities class for working with arrays.
 */
public final class ArrayUtils {
    private ArrayUtils() {

    }

    /**
     * Returns a set of the elements of the specified array.
     * This method provides a convenient way to create a set initialized to contain several elements.
     *
     * @param arr array of objects
     * @param <T> type of objects in the array
     * @return a set of the elements of the specified array
     * @see Arrays#asList(Object[])
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... arr) {
        return new HashSet<>(Arrays.asList(arr));
    }
}
