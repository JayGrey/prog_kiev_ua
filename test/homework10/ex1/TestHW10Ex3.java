package homework10.ex1;

import homework10.ex3.Counter;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestHW10Ex3 {
    @Test
    public void nullTest() {
        assertNull(Counter.countFrequency((Object[]) null));
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

    @Test
    public void testNullValues() {
        Map<String, Integer> result = Counter.countFrequency(
                new String[]{null, null});
        assertEquals(0, result.size());
    }

    @Test
    public void testPrimitiveByteArray() {
        Map<Byte, Integer> result = Counter.countFrequency(new byte[]{0, 1, 0});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get((byte) 0));
    }

    @Test
    public void testPrimitiveShortArray() {
        Map<Short, Integer> result = Counter.countFrequency(
                new short[]{0, 1, 0});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get((short) 0));
    }

    @Test
    public void testPrimitiveIntegerArray() {
        Map<Integer, Integer> result = Counter.countFrequency(
                new int[]{0, 1, 0});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get(0));
    }

    @Test
    public void testPrimitiveLongArray() {
        Map<Long, Integer> result = Counter.countFrequency(
                new long[]{0, 1, 0});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get((long) 0));
    }

    @Test
    public void testPrimitiveFloatArray() {
        Map<Float, Integer> result = Counter.countFrequency(
                new float[]{0.0f, 1.0f, 0.0f});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get((float) 0));
    }

    @Test
    public void testPrimitiveDoubleArray() {
        Map<Double, Integer> result = Counter.countFrequency(
                new double[]{0.0, 1.0, 0.0});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get((double) 0));
    }

    @Test
    public void testPrimitiveBooleanArray() {
        Map<Boolean, Integer> result = Counter.countFrequency(
                new boolean[]{false, true, true});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get(true));
    }

    @Test
    public void testPrimitiveCharacterArray() {
        Map<Character, Integer> result = Counter.countFrequency(
                new char[]{'a', 'b', 'a'});
        assertEquals(2, result.size());
        assertEquals(2, (int) result.get('a'));
    }
}
