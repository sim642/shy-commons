package eu.sim642.shy.commons.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ArrayUtilsTest {
    @Test
    public void testAsSet() throws Exception {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(set, ArrayUtils.asSet(1, 2, 3));
    }

    @Test
    public void testAsSetDuplicates() throws Exception {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(set, ArrayUtils.asSet(1, 2, 3, 2, 2, 1, 3));
    }

    @Test
    public void testAsSetEmpty() throws Exception {
        assertEquals(Collections.emptySet(), ArrayUtils.asSet());
    }

    @Test
    public void testAsSetSingle() throws Exception {
        assertEquals(Collections.singleton(1), ArrayUtils.asSet(1));
    }
}