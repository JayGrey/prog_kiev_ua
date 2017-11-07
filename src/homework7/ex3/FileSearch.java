package homework7.ex3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class FileSearch {

    private final String path;
    private final String filename;
    private ExecutorService pool;

    private void checkArgs(String path, String filename) {
        if (path == null || filename == null) {
            throw new IllegalArgumentException("argument is null");
        }

        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(path + " is not a directory");
        }

        if (filename.trim().length() == 0) {
            throw new IllegalArgumentException("file name must be > 0");
        }
    }

    public FileSearch(String path, String filename) {
        checkArgs(path, filename);
        this.path = path;
        this.filename = filename;

        pool = Executors.newCachedThreadPool();
    }

    public String[] find() {
        Future<List<String>> task = pool.submit(new SearchWorker(path));

        try {
            return task.get().toArray(new String[0]);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }


    private class SearchWorker implements Callable<List<String>> {
        private final String path;

        public SearchWorker(String path) {
            this.path = path;
        }

        @Override
        public List<String> call() throws IOException, ExecutionException,
                InterruptedException {
            List<Future<List<String>>> futures = new LinkedList<>();
            List<String> resultList = new LinkedList<>();

            File[] files = new File(path).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        Future<List<String>> task = pool.submit(
                                new SearchWorker(file.getCanonicalPath()));
                        futures.add(task);

                    } else if (file.getName().equals(filename)) {
                        resultList.add(file.getCanonicalPath());
                    }
                }

                for (Future<List<String>> future : futures) {
                    resultList.addAll(future.get());
                }
            }

            return resultList;
        }
    }
}
