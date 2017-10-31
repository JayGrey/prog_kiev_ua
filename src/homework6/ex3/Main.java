package homework6.ex3;

import java.util.Random;

public class Main {

    private static void randomize(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }
    }

    private static void calculateTime(ArraySort as, int[] array) {
        long timeStart = System.currentTimeMillis();
        as.sort(array);
        long timeEnd = System.currentTimeMillis();

        System.out.format("%-12s = %d ms%n", as.getClass().getSimpleName(),
                timeEnd - timeStart);
    }

    public static void main(String[] args) {
        int NUMBER_OF_ELEMENTS = 100_000;
        int[] array = new int[NUMBER_OF_ELEMENTS];

        System.out.format("start, %d elements in array%n", NUMBER_OF_ELEMENTS);

        randomize(array);
        calculateTime(new SimpleSort(), array);

        randomize(array);
        calculateTime(new InsertSort(), array);

        randomize(array);
        calculateTime(new ShellSort(), array);

        randomize(array);
        calculateTime(new PShellSort(), array);

        randomize(array);
        calculateTime(new JDKSort(), array);
    }
}
