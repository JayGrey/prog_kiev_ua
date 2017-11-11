package homework7.ex1;

public class Ship implements Runnable {

    private Dock dock;
    private int cargo;
    private String name;

    public Ship(String name, int cargo) {
        this.name = name;
        this.cargo = cargo;
    }

    public void setDock(Dock dock) {
        this.dock = dock;
    }

    @Override
    public void run() {
        try {
            System.out.format("ship %s start unloading, cargo amount: %d%n", name, cargo);
            while (cargo > 0) {
                dock.put();
                cargo--;
                System.out.format("ship %s cargo remain: %d%n", name, cargo);
            }
            System.out.format("ship %s is unloaded%n", name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            dock.setDone(true);
        }
    }
}
