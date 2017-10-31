package homework6.ex5;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMonitor {

    private final File directory;
    private List<File> files;

    public FileMonitor(String directory) {
        checkArgs(directory);
        this.directory = new File(directory);
    }

    private void checkArgs(String directory) {
        if (directory == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!(new File(directory).isDirectory())) {
            throw new IllegalArgumentException("argument is not a directory");
        }
    }

    public void start() {

        files = new ArrayList<>(
                Arrays.asList(directory.listFiles(File::isFile)));

        Thread t = new Thread(new Worker());
        t.setName("file monitor");
        t.setDaemon(true);
        t.start();
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            int CHECK_INTERVAL = 1;
            boolean changed = false;

            while (true) {
                File[] f = directory.listFiles(File::isFile);
                if (f == null) {
                    continue;
                }
                List<File> newFiles = new ArrayList<>(Arrays.asList(f));

                for (File file : newFiles) {
                    if (!FileMonitor.this.files.contains(file)) {
                        System.out.println("+ " + file.getName());
                        changed = true;
                    }
                }

                for (File file : FileMonitor.this.files) {
                    if (!newFiles.contains(file)) {
                        System.out.println("- " + file.getName());
                        changed = true;
                    }
                }

                if (changed) {
                    FileMonitor.this.files = newFiles;
                    changed = false;
                }

                try {
                    Thread.sleep(CHECK_INTERVAL * 1000);

                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
