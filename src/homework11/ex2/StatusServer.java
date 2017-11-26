package homework11.ex2;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class StatusServer {
    private static final int NUMBER_OF_THREADS = 4;

    private final int port;

    private AtomicInteger request;

    public StatusServer(int port) {
        this.port = port;
        request = new AtomicInteger(0);
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                pool.submit(new Worker(socket.accept()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    private synchronized String getInfo() {
        OperatingSystemMXBean bean =
                ManagementFactory.getOperatingSystemMXBean();
        return String.format("request: %s%nos: %s %s, %s%n",
                request.incrementAndGet(), bean.getName(), bean.getVersion(),
                bean.getArch());
    }

    private class Worker implements Runnable {

        private final Socket socket;

        Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            if (socket == null) {
                return;
            }

            try (PrintStream output =
                         new PrintStream(socket.getOutputStream())) {
                output.println(getInfo());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
