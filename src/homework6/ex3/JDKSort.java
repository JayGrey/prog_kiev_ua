package homework6.ex3;

import java.util.Arrays;

public class JDKSort implements ArraySort {
    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(array);
        return array;
    }
}
