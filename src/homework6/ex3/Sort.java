package homework6.ex3;

public class Sort implements ArraySort {

    @Override
    public int[] sort(int[] array) {
        checkArgs(array);
        return array;
    }

    private void checkArgs(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("argument is null");
        }
    }
}
