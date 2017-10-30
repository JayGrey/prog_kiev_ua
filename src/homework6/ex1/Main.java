package homework6.ex1;

public class Main {
    public static void main(String[] args) {
        int NUMBER_OF_THREADS = 100;
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CalculateFactorial(i + 1));
            threads[i].start();
        }
    }
}
