package homework7.ex3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        Future<List<String>> future = pool.submit(new SearchWorker(path));
        try {
            return future.get().toArray(new String[0]);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    private class SearchWorker implements Callable<List<String>> {
        private final String path;

        SearchWorker(String path) {
            this.path = path;
        }

        @Override
        public List<String> call() throws IOException, ExecutionException,
                InterruptedException {
            List<String> resultList = new LinkedList<>();
            List<Future<List<String>>> futures = new LinkedList<>();

            File[] files = new File(path).listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (Files.isSymbolicLink(
                                Paths.get(file.getAbsolutePath()))) {
                            continue;
                        }
                        futures.add(pool.submit(new SearchWorker(
                                file.getCanonicalPath())));

                    } else if (file.getName().equals(filename)) {
                        resultList.add(file.getCanonicalPath());
                    }
                }
            }

            for (Future<List<String>> future : futures) {
                resultList.addAll(future.get());
            }

            return resultList;
        }
    }
}
