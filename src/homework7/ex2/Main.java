package homework7.ex2;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("usage: Main <filename> <path>");
            System.exit(1);
        }
        new FileCopy().copy(args[0], args[1]);
    }
}
