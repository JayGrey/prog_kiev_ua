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
            result.put(t, result.getOrDefault(t, 0) + 1);
        }

        return result;
    }
}
