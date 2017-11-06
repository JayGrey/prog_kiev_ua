package homework7.ex3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FileSearch {
    private static void checkArgs(String path, String filename) {
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

    public static String[] find(String path, String filename) {
        checkArgs(path, filename);

        List<String> foundFiles = Collections.synchronizedList(
                new ArrayList<>());

        new ForkJoinPool().invoke(new FileFinder(path, filename, foundFiles));

        return foundFiles.toArray(new String[0]);
    }

    private static class FileFinder extends RecursiveAction {

        private final String path;
        private final String filename;
        private final List<String> result;

        public FileFinder(String path, String filename, List<String> result) {
            this.path = path;
            this.filename = filename;
            this.result = result;
        }

        @Override
        protected void compute() {
            File[] nodes = new File(path).listFiles();
            if (nodes == null) {
                return;
            }
            try {
                for (File node : nodes) {
                    if (node.isDirectory()) {

                        invokeAll(new FileFinder(node.getCanonicalPath(),
                                filename, result));

                    } else if (node.getName().equals(filename)) {
                        result.add(node.getCanonicalPath());
                    }
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
