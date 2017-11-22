package homework10.ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translate {
    private final Dictionary dictionary;

    public Translate(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void process(String originalFile, String translatedFile) {
        checkArgs(originalFile, translatedFile);

        Pattern pattern = Pattern.compile("\\w+");

        try (BufferedReader reader = new BufferedReader(new FileReader
                (originalFile));
             FileWriter writer = new FileWriter(translatedFile)) {
            while (reader.ready()) {
                String input = reader.readLine();
                Matcher matcher = pattern.matcher(input);
                int begin = 0;
                while (matcher.find()) {
                    writer.write(input.substring(begin, matcher.start()));
                    String translated = dictionary.get(matcher.group());
                    writer.write(translated.length() == 0
                            ? matcher.group() : translated);
                    begin = matcher.end();
                }
                writer.write(input.substring(begin));
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
