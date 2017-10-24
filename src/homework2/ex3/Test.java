package homework2.ex3;

import homework2.ex2.Point;

import static java.lang.Math.abs;

public class Test {

    // Допустимое отклонение
    private static final double DELTA = 0.01;

    public static void main(String[] args) {
        System.out.println("test Triangle class ... "
                + (testTriangle() ? "OK" : "FAIL"));

        System.out.println("test Square class ... "
                + (testSquare() ? "OK" : "FAIL"));
    }


    private static boolean checkEquals(double actual, double expected,
                                       double delta) {
        return abs(actual - expected) <= delta;
    }

    private static boolean testTriangle() {
        double EXPECTED_PERIMETER = 20.60;
        double EXPECTED_AREA = 17.50;

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

    private static boolean testSquare() {
        double EXPECTED_PERIMETER = 24.00;
        double EXPECTED_AREA = 35.00;

        // Проверка для положительной плоскости
        Square s = new Square(new Point(0, 0), new Point(5, 0), new Point(5, 7),
                new Point(0, 7));

        boolean test1 = checkEquals(s.getPerimetr(), EXPECTED_PERIMETER, DELTA)
                && checkEquals(s.getArea(), EXPECTED_AREA, DELTA);

        // Проверка для отрицательной плоскости
        s = new Square(new Point(-1, -5), new Point(-1, 0), new Point(-8, 0),
                new Point(-8, -5));

        boolean test2 = checkEquals(s.getPerimetr(), EXPECTED_PERIMETER, DELTA)
                && checkEquals(s.getArea(), EXPECTED_AREA, DELTA);

        return test1 && test2;
    }
}
