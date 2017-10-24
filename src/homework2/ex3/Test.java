package homework2.ex3;

import homework2.ex2.Point;

import static java.lang.StrictMath.abs;

public class Test {
    public static void main(String[] args) {
        System.out.println("test Triangle class ... "
                + (testTriangle() ? "OK" : "FAILURE"));
    }

    private static boolean checkEquals(double actual, double expected,
                                       double delta) {
        return abs(actual - expected) <= delta;
    }

    private static boolean testTriangle() {
        double EXPECTED_PERIMETER = 20.60;
        double EXPECTED_AREA = 17.50;

        // Допустимое отклонение
        double DELTA = 0.01;

        // Проверка для положительной плоскости
        Triangle t = new Triangle(new Point(0, 0), new Point(5, 0),
                new Point(0, 7));

        boolean test1 = checkEquals(t.getPerimetr(), EXPECTED_PERIMETER, DELTA)
                && checkEquals(t.getArea(), EXPECTED_AREA, DELTA);

        // Проверка для отрицательной плоскости
        t = new Triangle(new Point(-1, 0), new Point(-8, 0),
                new Point(-1, -5));

        boolean test2 = checkEquals(t.getPerimetr(), EXPECTED_PERIMETER, DELTA)
                && checkEquals(t.getArea(), EXPECTED_AREA, DELTA);

        return test1 && test2;
    }
}
