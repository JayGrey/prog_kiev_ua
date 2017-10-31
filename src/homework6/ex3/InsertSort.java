package homework6.ex3;

public class InsertSort implements ArraySort {

    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        int tmp;
        int j;
        for (int i = 1; i < array.length; i++) {
            tmp = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = tmp;
        }
        return array;
    }
}
