package homework6.ex5;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("usage: Main <dir>");
            System.exit(1);
        }

        new FileMonitor(args[0]).start();
        System.out.println("monitoring " + args[0]);

        while (true) {
            System.out.println("to quit press Ctrl-C");
            Scanner scanner = new Scanner(System.in);
            scanner.next();
        }
    }
}
