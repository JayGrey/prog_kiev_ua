package homework6.ex3;

import java.util.ArrayList;
import java.util.List;

public class PShellSort implements ArraySort {

    private int[] array;

    @Override
    public int[] sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        this.array = array;

        // эмпирическая последовательность Марцина Циура
        int[] D = {132, 57, 23, 10, 4, 1};

        for (int d : D) {
            List<Thread> threads = new ArrayList<>(d);

            for (int j = 0; j < d; j++) {
                Thread t = new Thread(new SortWorker(d, j));
                t.start();
                threads.add(t);
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return array;
    }

    private class SortWorker implements Runnable {
        private final int step;
        private final int from;

        SortWorker(int step, int from) {
            this.step = step;
            this.from = from;
        }

        @Override
        public void run() {

            int j;
            int tmp;

            for (int i = from; i < array.length; i += step) {
                tmp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (tmp < array[j - step]) {
                        array[j] = array[j - step];
                    } else {
                        break;
                    }
                }
                array[j] = tmp;
            }
        }
    }
}
