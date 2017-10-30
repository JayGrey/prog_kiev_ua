package homework6.ex2;

import java.util.Random;

public class Main {

    private static String measure(ArraySum sum, int[] array) {
        long startTime = System.currentTimeMillis();
        int result = sum.calculate(array);
        long endTime = System.currentTimeMillis();

        return String.format("%d, in %d ms", result, endTime - startTime);
    }

    public static void main(String[] args) {
        int NUMBER_OF_ELEMENTS = 200_000_000;

        int[] array = new int[NUMBER_OF_ELEMENTS];
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            array[i] = random.nextInt(10);
        }
        System.out.println("results:");
        System.out.println("ordinal sum =  "
                + measure(new Sum()::calculate, array));
        System.out.println("parallel sum = "
                + measure(new ParallelSum(4)::calculate, array));
    }
}