package homework10;

import homework10.ex4.CharacterMatrixEnglish;
import homework10.ex4.Translator;
import org.junit.Test;

public class TestHW10Ex4 {

    @Test
    public void test() {
        Translator translator = new Translator(new CharacterMatrixEnglish());
        System.out.println(translator.translate("HELLO WORLD"));
    }
}
