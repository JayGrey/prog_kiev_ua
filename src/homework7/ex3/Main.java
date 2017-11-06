package homework7.ex3;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Main <path> <filename>");
            System.exit(1);
        }

        String[] filenames = FileSearch.find(args[0], args[1]);

        if (filenames.length > 0) {
            System.out.println("found files in following directories:");
            for (String filename : filenames) {
                System.out.println(filename);
            }
        } else {
            System.out.println("file not found");
        }
    }
}
