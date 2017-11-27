package homework11.ex3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: Main <site> <filename>");
            System.exit(1);
        }

        Set<String> links = new LinkFinder().getLinks(args[0]);

        if (links.size() > 0) {
            try (PrintWriter writer = new PrintWriter(args[1])) {
                for (String link : links) {
                    writer.println(link);
                }
            } catch (FileNotFoundException e) {
                System.err.println("unable to write to file " + args[1]);
            }
        }
    }
}
