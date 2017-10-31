package homework6.ex4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCopy {

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

        File dirTo = new File(to);
        File[] files = new File(from).listFiles(File::isFile);

        if (files != null && files.length > 0) {
            List<Thread> workers = new ArrayList<>(files.length);
            for (File file : files) {
                Thread t = new Thread(new CopyWorker(file, dirTo));
                t.start();
                workers.add(t);
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
        private File file;
        private File dirTo;

        CopyWorker(File file, File dirTo) {
            this.file = file;
            this.dirTo = dirTo;
        }

        @Override
        public void run() {
            int BUFFER_SIZE = 1024 * 1024;
            byte[] buffer = new byte[BUFFER_SIZE];
            try (FileInputStream fIn = new FileInputStream(file);
                 FileOutputStream fOut = new FileOutputStream(
                         new File(dirTo, file.getName()))) {

                int read;
                while (true) {
                    read = fIn.read(buffer);
                    if (read == -1) {
                        break;
                    }
                    fOut.write(buffer, 0, read);
                }
                System.out.println("file " + file.getName() + " copied");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
