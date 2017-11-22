package homework10.ex1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private Map<String, String> dictionary = new HashMap<>();

    public String get(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }

        String result = dictionary.get(word.toLowerCase());

        return result == null ? "" : result;
    }

    public void put(String original, String translation) {
        if (original == null || translation == null) {
            throw new IllegalArgumentException();
        }
        dictionary.put(original.toLowerCase().trim(), translation.toLowerCase
                ().trim());
    }

    public void save(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Map.Entry<String, String> e : dictionary.entrySet()) {
                writer.write(String.format("%s=%s%n", e.getKey(), e.getValue
                        ()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String fileName) {
        if (fileName == null || !new File(fileName).isFile()) {
            throw new IllegalArgumentException();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader
                (fileName))) {
            while (reader.ready()) {
                String[] elements = reader.readLine().split("=");
                if (elements.length < 2) {
                    continue;
                }
                put(elements[0], elements[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int size() {
        return dictionary.size();
    }
}
