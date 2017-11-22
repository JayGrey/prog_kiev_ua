package homework10.ex1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translate {
    private final Dictionary dictionary;

    public Translate(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String process(String originalString) {
        checkArgs(originalString);

        Pattern pattern = Pattern.compile("\\w+");
        StringBuilder result = new StringBuilder();

        Matcher matcher = pattern.matcher(originalString);
        int begin = 0;
        while (matcher.find()) {
            result.append(originalString.substring(begin, matcher.start()));
            String translated = dictionary.get(matcher.group());
            result.append(translated.length() == 0 ?
                    matcher.group() : translated);
            begin = matcher.end();
        }
        result.append(originalString.substring(begin));

        return result.toString();
    }

    private void checkArgs(String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException();
        }
    }
}
