package homework10.ex3;

import java.util.HashMap;
import java.util.Map;

public class Counter {
    public static <T> Map<T, Integer> countFrequency(T[] array) {
        if (array == null) {
            return null;
        }

        Map<T, Integer> result = new HashMap<>();

        for (T t : array) {
            if (t != null) {
                result.put(t, result.getOrDefault(t, 0) + 1);
            }
        }

        return result;
    }

    public static Map<Byte, Integer> countFrequency(byte[] array) {
        if (array == null) {
            return null;
        }

        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Character, Integer> countFrequency(char[] array) {
        if (array == null) {
            return null;
        }

        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Boolean, Integer> countFrequency(boolean[] array) {
        if (array == null) {
            return null;
        }

        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Short, Integer> countFrequency(short[] array) {
        if (array == null) {
            return null;
        }

        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Integer, Integer> countFrequency(int[] array) {
        if (array == null) {
            return null;
        }

        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Long, Integer> countFrequency(long[] array) {
        if (array == null) {
            return null;
        }

        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Float, Integer> countFrequency(float[] array) {
        if (array == null) {
            return null;
        }

        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }

    public static Map<Double, Integer> countFrequency(double[] array) {
        if (array == null) {
            return null;
        }

        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return countFrequency(result);
    }
}
