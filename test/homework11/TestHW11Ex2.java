package homework11;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class TestHW11Ex2 {
    @Test
    public void testConcurrent() throws ExecutionException,
            InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();
        Set<Integer> requests = new HashSet<>();

        final int NUMBER_OF_REQUESTS = 100;

        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            futures.add(pool.submit(new RequestWorker("127.0.0.1", 1111)));
        }

        for (Future<Integer> future : futures) {
            requests.add(future.get());
        }

        assertEquals(NUMBER_OF_REQUESTS, requests.size());

        pool.shutdown();
    }

    private class RequestWorker implements Callable<Integer> {
        private final String host;
        private final int port;

        RequestWorker(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public Integer call() throws Exception {
            Random random = new Random();
            try (Socket socket = new Socket(host, port);
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(
                                 socket.getInputStream()))) {
                String line = reader.readLine();
                Thread.sleep(random.nextInt(47));
                return Integer.valueOf(line.split(":")[1].trim());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
