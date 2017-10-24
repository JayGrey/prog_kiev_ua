package homework2.ex2;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Point {
    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Расстояние между этой точкой и p
    public double length(Point p) {
        return abs(sqrt(((x - p.x)*(x - p.x)) + ((y - p.y)*(y - p.y))));
    }
}
