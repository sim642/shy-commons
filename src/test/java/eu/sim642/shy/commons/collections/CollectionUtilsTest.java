package eu.sim642.shy.commons.collections;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CollectionUtilsTest {
    @Test
    public void testSwap() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        CollectionUtils.swap(map, "a", "b");
        assertEquals(2, (int) map.get("a"));
        assertEquals(1, (int) map.get("b"));
    }

    @Test
    public void testSwapSameKeys() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);

        CollectionUtils.swap(map, "a", "a");
        assertEquals(1, (int) map.get("a"));
    }

    @Test
    public void testSwapFirstKeyMissing() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("b", 2);

        CollectionUtils.swap(map, "a", "b");
        assertEquals(2, (int) map.get("a"));
        assertNull(map.get("b"));
    }

    @Test
    public void testSwapSecondKeyMissing() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);

        CollectionUtils.swap(map, "a", "b");
        assertNull(map.get("a"));
        assertEquals(1, (int) map.get("b"));
    }

    @Test
    public void testSwapBothKeysMissing() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();

        CollectionUtils.swap(map, "a", "b");
        assertNull(map.get("a"));
        assertNull(map.get("b"));
    }
}