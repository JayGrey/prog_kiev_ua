package homework10.ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterMatrixEnglish implements CharacterMatrix {

    private final HashMap<Character, String[]> forms;
    private final int FORM_HEIGHT = 7;
    private final String FORM_FILE = "font.json";

    public CharacterMatrixEnglish() {
        this.forms = new HashMap<>();
        loadFromFile(FORM_FILE);
    }

    @Override
    public String[] get(char c) {
        return forms.getOrDefault(c, getEmpty());
    }

    @Override
    public String[] getEmpty() {
        String[] result = new String[FORM_HEIGHT];
        for (int i = 0; i < FORM_HEIGHT; i++) {
            result[i] = "";
        }
        return result;
    }

    private void loadFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader
                (fileName))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        parseElements(sb.toString());
    }

    private void parseElements(String str) {
        Matcher matcher = Pattern.compile("\\\"(.+?)\\\"\\s*:\\s*\\[(.+?)\\]")
                .matcher(str);
        while (matcher.find()) {
            forms.put(getKey(matcher.group(1)), getArray(matcher.group(2)));
        }
    }

    private Character getKey(String s) {
        return (s == null || s.length() < 1) ? null : s.trim().charAt(0);
    }

    private String[] getArray(String s) {
        String[] array = new String[FORM_HEIGHT];
        int index = 0;
        Matcher matcher = Pattern.compile("\"(.+?)\"").matcher(s);
        while (matcher.find()) {
            array[index++] = matcher.group(1);
        }
        return array;
    }
}







