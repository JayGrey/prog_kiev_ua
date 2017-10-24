package homework2.ex4;

import homework2.ex2.Point;
import homework2.ex3.Circle;
import homework2.ex3.Square;
import homework2.ex3.Triangle;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();

        board.addShape(0, new Triangle(new Point(0, 0), new Point(5, 0),
                new Point(0, 7)));

        board.addShape(1, new Circle(new Point(0, 0), new Point(5, 0)));

        board.addShape(2, new Square(new Point(0, 0), new Point(5, 0),
                new Point(5, 7), new Point(0, 7)));

        board.addShape(3, new Circle(new Point(-1, 0), new Point(-6, 0)));

        board.deleteShape(1);

        System.out.println(board);

    }
}
