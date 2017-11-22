package homework10.ex1;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        Translate translate = new Translate(dictionary);
        translate.process("English.in", "Ukrainian.out");
    }
}
