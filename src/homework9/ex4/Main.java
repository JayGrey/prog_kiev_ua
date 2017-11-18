package homework9.ex4;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int checkArg(String[] args) {
        int drinks = 0;
        try {
            if (args.length < 1) {
                System.out.println("Usage: Main <drinks>");
                System.exit(1);
            }
            drinks = Integer.valueOf(args[0]);
            if (drinks < 0) {
                System.out.println("number of drinks must be greater than 0");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("drinks not a number");
            System.exit(1);
        }

        return drinks;
    }

    public static void main(String[] args) {
        int drinks = checkArg(args);

        Queue<String> queue = new LinkedList<>();

        queue.add("Sheldon");
        queue.add("Leonard");
        queue.add("Volovitc");
        queue.add("Kutrapalli");
        queue.add("Penny");

        System.out.println("initial queue: " + queue);

        for (int i = 0; i < drinks; i++) {
            String s = queue.poll();
            queue.add(s);
            queue.add(s);
        }
        System.out.println("result queue:  " + queue);
    }
}
