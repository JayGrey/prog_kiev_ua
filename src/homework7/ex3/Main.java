package homework7.ex3;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        if (args.length < 2) {
            System.out.println("Usage: Main <path> <filename>");
            System.exit(1);
        }

        String[] filenames = new FileSearch(args[0], args[1]).find();

        if (filenames.length > 0) {
            System.out.println("found files in following directories:");
            for (String filename : filenames) {
                System.out.println(filename);
            }
        } else {
            System.out.println("file not found");
        }
    }
}
