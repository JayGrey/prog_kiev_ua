package homework7.ex1;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Port port = new Port(2);

        port.moor(new Ship("Medusa", 10));
        port.moor(new Ship("Valkyrie", 10));
        port.moor(new Ship("Charon", 10));
        port.moor(new Ship("Pegasus", 10));
        port.unload();
    }
}
