package homework10.ex1;

import homework10.ex3.Counter;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestHW10Ex3 {
    @Test
    public void nullTest() {
        assertNull(Counter.countFrequency(null));
    }

    @Test
    public void emptyTest() {
        Map<Integer, Integer> result = Counter.countFrequency(new Integer[]{});
        assertEquals(0, result.size());
    }

    @Test
    public void testInts() {
        Map<Integer, Integer> result = Counter.countFrequency(
                new Integer[]{0, 1, 2, 2, 0});
        assertEquals(3, result.size());
        assertEquals(2, (int) result.get(0));
    }

    @Test
    public void testStrings() {
        Map<String, Integer> result = Counter.countFrequency(
                new String[]{"0", "1", "2", "2", "0"});
        assertEquals(3, result.size());
        assertEquals(2, (int) result.get("0"));
    }
}
