package homework6.ex2;

public class Sum implements ArraySum {
    @Override
    public int calculate(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int anArray : array) {
            sum += anArray;
        }
        return sum;

    }
}
