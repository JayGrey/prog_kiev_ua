package homework5.ex2;

import java.io.*;
import java.util.Scanner;

public class SimilarWords {

    public static Container<String> findWords(String file1, String file2) {

        Container<String> wordsContainer1 = new Container<>(String.class);
        Container<String> wordsContainer2 = new Container<>(String.class);
        Container<String> result = new Container<>(String.class);

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            Scanner scanner = new Scanner(reader1);
            while (scanner.hasNext()) {
                wordsContainer1.add(scanner.next());
            }

            scanner = new Scanner(reader2);
            while (scanner.hasNext()) {
                wordsContainer2.add(scanner.next());
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String s : wordsContainer1) {
            if (wordsContainer2.contains(s)) {
                result.add(s);
            }
        }

        return result;
    }

    public static void writeResult(Container<String> container, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String s : container) {
                writer.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: SimilarWords <src file 1> <src file 2>");
            System.exit(1);
        }

        writeResult(findWords(args[0], args[1]), "out.log");

    }
}
