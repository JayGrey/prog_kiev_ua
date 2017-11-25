package homework10.ex4;

public class Translator {
    private final CharacterMatrix matrix;

    public Translator(CharacterMatrix matrix) {
        this.matrix = matrix;
    }

    public String translate(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String result[] = matrix.getEmpty();
        for (char c : input.toCharArray()) {
            result = append(result, matrix.get(c));
        }

        StringBuilder builder = new StringBuilder();
        for (String s : result) {
            builder.append(s).append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String[] append(String[] head, String tail[]) {
        if (head == null || tail == null || (head.length != tail.length)) {
            throw new IllegalArgumentException();
        }

        String[] result = new String[head.length];

        for (int i = 0; i < head.length; i++) {
            result[i] = head[i] + ' ' + tail[i];
        }

        return result;
    }
}
