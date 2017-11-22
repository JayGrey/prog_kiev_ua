package homework10.ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Translate {
    private final Dictionary dictionary;

    public Translate(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void process(String originalFile, String translatedFile) {
        checkArgs(originalFile, translatedFile);
        try (BufferedReader reader = new BufferedReader(new FileReader
                (originalFile));
             FileWriter writer = new FileWriter(translatedFile)) {
            while (reader.ready()) {
                String str = reader.readLine();
                int begin = 0;
                int end = 0;
                int i = 0;
                while (i < str.length()) {
                    char ch = str.charAt(i);
                    if (Character.isAlphabetic(ch)) {
                        if (begin != i) {
                            writer.write(str.substring(end, i));
                        }

                        begin = i;
                        end = i;
                        while (end < str.length() && Character.isAlphabetic(str
                                .charAt(end))) {
                            end++;
                        }
                        writer.write(dictionary.get(str.substring(begin, end)));
                        i = end + 1;
                    } else {
                        i += 1;
                    }
                }
                if (end < str.length()) {
                    writer.write(str.substring(end));
                }
                writer.write(System.lineSeparator());
            }
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkArgs(String originalFile, String translatedFile) {
        if (originalFile == null || translatedFile == null || !new File
                (originalFile).isFile()) {
            throw new IllegalArgumentException();
        }
    }
}
