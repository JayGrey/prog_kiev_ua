package homework6.ex4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FileCopy {

    private static final int NUMBER_OF_THREADS = 4;

    private Queue<File> queue;
    private File dirTo;

    private void checkArgs(String from, String to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("argument is null");
        }

        File dirFrom = new File(from);
        File dirTo = new File(to);
        if (!dirFrom.isDirectory() || !dirTo.isDirectory()) {
            throw new IllegalArgumentException("is not a directory");
        }

        if (dirFrom.equals(dirTo)) {
            throw new IllegalArgumentException("source and destination are " +
                    "the same");
        }
    }

    public void copy(String from, String to) {

        checkArgs(from, to);

        dirTo = new File(to);
        File[] files = new File(from).listFiles(File::isFile);

        if (files != null && files.length > 0) {
            queue = new ArrayBlockingQueue<>(files.length, true,
                    Arrays.asList(files));

            Thread[] workers = new Thread[NUMBER_OF_THREADS];
            for (int i = 0; i < NUMBER_OF_THREADS; i++) {
                Thread thread = new Thread(new CopyWorker());
                thread.start();
                workers[i] = thread;
            }
            for (Thread worker : workers) {
                try {
                    worker.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private class CopyWorker implements Runnable {

        @Override
        public void run() {
            File file;
            while ((file = queue.poll()) != null) {
                copy(file);
                System.out.println("file " + file.getName() + " copied");
            }

        }

        private void copy(File in) {
            int BUFFER_SIZE = 1024 * 1024;
            byte[] buffer = new byte[BUFFER_SIZE];
            try (FileInputStream fIn = new FileInputStream(in);
                 FileOutputStream fOut = new FileOutputStream(
                         new File(dirTo, in.getName()))) {

                int read;
                while ((read = fIn.read(buffer)) != -1) {
                    fOut.write(buffer, 0, read);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
