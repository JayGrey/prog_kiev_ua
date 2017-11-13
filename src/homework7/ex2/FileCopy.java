package homework7.ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.*;

public class FileCopy {

    private static final int BUFFER_SIZE = 2 * 1024 * 1024;
    private Exchanger<byte[]> exchanger;

    public FileCopy() {
        exchanger = new Exchanger<>();
    }

    private void checkArgs(String filename, String path) {
        if (filename == null || path == null || !new File(filename).isFile()
                || !new File(path).isDirectory()) {
            throw new IllegalArgumentException();
        }

        try {
            if (new File(filename).getParent().equals(
                    new File(path).getCanonicalPath())) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void copy(String filename, String path) {
        checkArgs(filename, path);
        File fileIn = new File(filename);
        File fileOut = new File(path, fileIn.getName());


        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            Future<?> fr = pool.submit(new Reader(fileIn));
            Future<?> fw = pool.submit(new Writer(fileOut, fileIn.length()));
            fr.get();
            fw.get();
        } catch (InterruptedException | ExecutionException e) {
            //
        } finally {
            pool.shutdown();
        }

    }

    private class Reader implements Runnable {

        private final File filename;
        private byte[] buffer;

        Reader(File filename) {
            this.filename = filename;
            this.buffer = new byte[BUFFER_SIZE];
        }

        @Override
        public void run() {
            int read;
            try (FileInputStream in = new FileInputStream(filename)) {
                while (true) {
                    read = in.read(buffer);

                    if (read == -1) {
                        buffer = new byte[0];
                    } else if (read < BUFFER_SIZE) {
                        byte[] tmp = new byte[read];
                        System.arraycopy(buffer, 0, tmp, 0, read);
                        buffer = tmp;
                    }
                    buffer = exchanger.exchange(buffer);

                    if (read < BUFFER_SIZE) {
                        break;
                    }
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Writer implements Runnable {
        private final File filename;
        private final long totalLength;
        private long alreadyRead = 0;
        private int percents = 0;
        private final int step = 5;

        Writer(File filename, long totalLength) {
            this.filename = filename;
            this.totalLength = totalLength;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[BUFFER_SIZE];
            try (FileOutputStream out = new FileOutputStream(filename)) {
                System.out.print("copying [");
                while (true) {
                    buffer = exchanger.exchange(buffer);
                    out.write(buffer);
                    alreadyRead += buffer.length;

                    while (((alreadyRead * 100) / totalLength) >= (percents + step)) {
                        System.out.print('#');
                        percents += step;
                    }

                    if (buffer.length < BUFFER_SIZE) {
                        break;
                    }
                }
                System.out.println("] done");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}