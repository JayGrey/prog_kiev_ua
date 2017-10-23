package homework1.ex2;

public class Test {
    public static void main(String[] args) {

        Triangle[] triangles = {new Triangle(3, 4, 5), new Triangle(1, 2, 3),
                new Triangle(1, 1, 1), new Triangle(0, 0, 0)};

        for (Triangle t : triangles) {
            System.out.format("%s has square %.02f%n", t, t.square());
        }
    }
}
