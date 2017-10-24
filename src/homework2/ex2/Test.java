package homework2.ex2;

public class Test {

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(0, 10);

        System.out.println("(a, b) length = " + a.length(b));
        System.out.println("(b, a) length = " + b.length(a));

        a = new Point(-10, 0);
        b = new Point(10, 0);

        System.out.println("(a, b) length = " + a.length(b));
        System.out.println("(b, a) length = " + b.length(a));
    }
}
