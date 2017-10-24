package homework2.ex4;

import homework2.ex1.Shape;

public class Board {
    private static final int MAX_SHAPES = 4;

    private Shape[] shapes;

    public Board() {
        shapes = new Shape[MAX_SHAPES];
    }

    public void addShape(int index, Shape shape) {
        if (index >= 0 && index < MAX_SHAPES && shape != null) {
            shapes[index] = shape;
        }
    }

    public void deleteShape(int index) {
        if (index >= 0 && index < MAX_SHAPES) {
            shapes[index] = null;
        }
    }

    public double totalArea() {
        double result = 0;

        for (Shape cell : shapes) {
            if (cell != null) {
                result += cell.getArea();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < shapes.length; i++) {
            sb.append(String.format("cell %d: %s%n", i + 1,
                    (shapes[i] == null ? "[empty]" : shapes[i])));
        }
        sb.append(String.format("total shapes area is= %.02f%n", totalArea()));

        return sb.toString();
    }
}
