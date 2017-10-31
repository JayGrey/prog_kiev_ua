package homework6.ex3;

public class SimpleSort implements ArraySort {

    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
            }
        }

        return array;
    }
}
