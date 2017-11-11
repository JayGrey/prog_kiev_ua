package homework7.ex1;

public class Dock {
    private boolean full = false;
    private boolean done;

    public synchronized void put() throws InterruptedException {
        while (full) {
            wait();
        }
        full = true;
        notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        while (!full) {
            wait();
        }
        full = false;
        notifyAll();
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
