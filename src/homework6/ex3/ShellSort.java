package homework6.ex3;

public class ShellSort implements ArraySort {

    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        int tmp;
        int j;
        for (int d = array.length / 2; d > 0; d /= 2) {
            for (int i = d; i < array.length; i++) {

                tmp = array[i];

                for (j = i; j >= d; j -= d) {
                    if (tmp < array[j - d]) {
                        array[j] = array[j - d];
                    } else {
                        break;
                    }
                }
                array[j] = tmp;
            }
        }

        return array;
    }
}
