package homework10.ex1;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        final String INPUT_FILE = "English.in";
        final String OUTPUT_FILE = "Ukrainian.out";
        final String DICT_FILE = "en_ua_dict.txt";

        Dictionary dictionary = new Dictionary();
        dictionary.load(DICT_FILE);
        Translate translate = new Translate(dictionary);

        try (BufferedReader reader
                     = new BufferedReader(new FileReader(INPUT_FILE));
             PrintWriter writer = new PrintWriter(OUTPUT_FILE)) {
            while (reader.ready()) {
                writer.println(translate.process(reader.readLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("error: File not found, cant proceed further");
            e.printStackTrace(System.err);
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
