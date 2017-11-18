package homework9.ex3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RelativeFrequency {

    private static void checkFileName(String fileName) {
        if (fileName == null || !new File(fileName).isFile()) {
            throw new IllegalArgumentException();
        }
    }

    public static List<Char> calculate(String fileName) {
        checkFileName(fileName);

        List<Char> characters = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            characters.add(new Char((char) ('a' + i)));
        }

        try (Reader reader = new BufferedReader(new FileReader
                (fileName))) {
            while (reader.ready()) {
                int cp = reader.read();
                if (Character.toLowerCase(cp) >= 'a' && Character.toLowerCase
                        (cp) <= 'z') {
                    characters.get(Character.toLowerCase(cp) - 'a').inc();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return characters;
    }

    public static class Char {
        public final char ch;
        private int counter;

        public Char(char ch) {
            this.ch = ch;
        }

        public void inc() {
            counter += 1;
        }

        public int get() {
            return counter;
        }
    }
}
