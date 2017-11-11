package homework7.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Port {
    private List<Ship> ships = new ArrayList<>();
    private ExecutorService pool;

    public Port(int docs) {
        ships = new ArrayList<>();
        pool = Executors.newFixedThreadPool(docs * 2);
    }

    public void moor(Ship ship) {
        ships.add(ship);
    }


    public void unload() throws InterruptedException {
        final int DOCKWORKER_DELAY = 500;

        for (Ship ship : ships) {
            Dock dock = new Dock();
            DockWorker worker = new DockWorker(DOCKWORKER_DELAY);
            ship.setDock(dock);
            worker.setDock(dock);

            pool.submit(ship);
            pool.submit(worker);
        }

        pool.shutdown();
    }

    private class DockWorker implements Runnable {

        private Dock dock;
        private final int delay;

        DockWorker(int delay) {
            this.delay = delay;
        }

        void setDock(Dock dock) {
            this.dock = dock;
        }

        @Override
        public void run() {
            while (!dock.isDone()) {
                try {
                    dock.get();
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
