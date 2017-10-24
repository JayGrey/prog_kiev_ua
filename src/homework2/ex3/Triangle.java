package homework2.ex3;

import homework2.ex1.Shape;
import homework2.ex2.Point;

import static java.lang.Math.abs;


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
        return Math.sqrt(p * (p - abs(a.length(b))) * (p - abs(b.length(c))) *
                (p - abs(c.length(a))));
    }
}
