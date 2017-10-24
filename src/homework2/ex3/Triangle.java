package homework2.ex3;

import homework2.ex1.Shape;
import homework2.ex2.Point;


public class Triangle extends Shape {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getPerimetr() {
        return a.length(b) + b.length(c) + c.length(a);
    }

    @Override
    public double getArea() {
        double p = getPerimetr() / 2;
        return Math.sqrt(p * (p - a.length(b)) * (p - b.length(c)) *
                (p - c.length(a)));
    }
}
