package homework2.ex3;

import homework2.ex1.Shape;
import homework2.ex2.Point;

import static java.lang.Math.sqrt;

public class Square extends Shape {

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Square(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double getPerimetr() {
        return a.length(b) + b.length(c) + c.length(d) + d.length(a);
    }

    @Override
    public double getArea() {
        double p = getPerimetr() / 2;
        return sqrt((p - a.length(b)) * (p - b.length(c)) * (p - c.length(d))
                * (p - d.length(a)));
    }

    @Override
    public String toString() {
        return String.format("Square [a=%.02f, b=%.02f, c=%.02f, d=%.02f]",
                a.length(b), b.length(c), c.length(d), d.length(a));
    }
}
