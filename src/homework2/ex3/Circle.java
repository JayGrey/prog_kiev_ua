package homework2.ex3;

import homework2.ex1.Shape;
import homework2.ex2.Point;

public class Circle extends Shape {

    private Point center;
    private double radius;

    public Circle(Point center, Point other) {
        this.center = center;
        this.radius = center.length(other);
    }

    @Override
    public double getPerimetr() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle [center=(%.02f, %.02f), r=%.02f]",
                center.x, center.y, radius);
    }
}
