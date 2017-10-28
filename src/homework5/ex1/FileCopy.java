package homework5.ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void copyFilesByExtension(String srcDirectory,
                                            String dstDirectory,
                                            String extension) {
        File src = new File(srcDirectory);
        File dst = new File(dstDirectory);

        if (!src.isDirectory()) {
            throw new IllegalArgumentException("source path " + srcDirectory
                    + " is not a directory");
        }

        if (!dst.isDirectory()) {
            throw new IllegalArgumentException("destination path "
                    + dstDirectory + " is not a directory");
        }

        if(src.equals(dst)) {
            throw new IllegalArgumentException("source and destination paths" +
                    " are the same");
        }

        if (extension == null || extension.trim().length() == 0) {
            throw new IllegalArgumentException("file extension must be set");
        }

        File[] files = src.listFiles((file) -> {
            if (file.isDirectory()) {
                return false;
            }
            String[] ext = file.getName().split("\\.");
            return ext[ext.length - 1].compareToIgnoreCase(
                    extension.trim()) == 0;
        });

        if (files != null) {
            for (File file : files) {
                try {
                    copy(file, new File(dstDirectory, file.getName()));
                } catch (IOException e) {
                    System.err.println("error copying: " + e.getMessage());
                }
            }
        }

    }

    private static void copy(File from, File to) throws IOException {
        int BUFFER_SIZE = 1024 * 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        try (FileInputStream fIn = new FileInputStream(from);
             FileOutputStream fOut = new FileOutputStream(to)) {

            int read;
            while (true) {
                read = fIn.read(buffer);
                if (read == -1) {
                    break;
                }
                fOut.write(buffer, 0, read);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("usage: FileCopy <src dir> <dst dir> <ext>");
            System.exit(1);
        }
        copyFilesByExtension(args[0], args[1], args[2]);

        System.exit(0);
    }
}
