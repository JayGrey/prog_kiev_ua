package homework9.ex3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Main <file>");
            System.exit(1);
        }

        List<RelativeFrequency.Char> result = RelativeFrequency.calculate
                (args[0]);

        printStatistics(args[0], result);
    }

    private static void printStatistics(String filename,
                                        List<RelativeFrequency.Char> result) {
        int total = 0;

        for (RelativeFrequency.Char c : result) {
            total += c.get();
        }

        if (total == 0) {
            System.out.println("alphabetic chars not found");
            return;
        }

        result.sort((a, b) -> b.get() - a.get());

        System.out.println("file: " + filename);
        System.out.println("total alphabetic chars: " + total);
        for (RelativeFrequency.Char c : result) {
            System.out.format("%c = %.04f%n", c.ch, (c.get() / (float) total));
        }
    }
}
