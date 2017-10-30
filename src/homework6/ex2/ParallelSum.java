package homework6.ex2;

public class ParallelSum implements ArraySum {

    private final int numberOfThreads;
    private int[] result;
    private int[] array;

    public ParallelSum(int numberOfThreads) {
        if (numberOfThreads < 1) {
            throw new IllegalArgumentException();
        }
        this.numberOfThreads = numberOfThreads;
        result = new int[numberOfThreads];
    }

    @Override
    public int calculate(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        this.array = array;
        Thread[] workers = new Thread[numberOfThreads];

        int step = array.length / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Thread(new Worker(i, i * step,
                    i == numberOfThreads - 1 ? array.length : i * step + step));
            workers[i].start();
        }

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int sum = 0;
        for (int i : result) {
            sum += i;
        }
        return sum;
    }

    private class Worker implements Runnable {
        private int index;
        private int from;
        private int to;

        Worker(int index, int from, int to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = from; i < to; i++) {
                sum += array[i];
            }
            result[index] = sum;
        }
    }
}
