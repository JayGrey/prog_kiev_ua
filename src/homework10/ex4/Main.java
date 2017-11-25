package homework10.ex4;

public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: Main <text>");
            System.exit(1);
        }

        Translator translator = new Translator(new CharacterMatrixEnglish());

        System.out.println(translator.translate(args[0]));

    }
}
