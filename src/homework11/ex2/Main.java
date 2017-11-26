package homework11.ex2;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Main <port_number>");
            System.exit(1);
        }

        System.out.println("Status server started, press Ctrl-C to exit");
        new StatusServer(Integer.valueOf(args[0])).start();
    }
}
