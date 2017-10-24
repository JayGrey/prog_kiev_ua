package homework1.ex2;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        // формула Герона
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double perimeter() {
        return a + b + c;
    }


    @Override
    public String toString() {
        return String.format("Triangle{A=%.02f B=%.02f C=%.02f}", a, b, c);
    }
}
