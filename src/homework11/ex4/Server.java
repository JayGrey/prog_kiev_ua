package homework11.ex4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private final ExecutorService pool;
    private Template template;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Server <port>");
            System.exit(1);
        }
        new Server(Integer.valueOf(args[0])).start();
    }


    public Server(int port) {
        this.port = port;
        pool = Executors.newFixedThreadPool(4);
        template = new Template();
    }

    private void start() {
        System.out.println("Start web server on port " + port);
        loadTemplates();
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                pool.submit(new WebWorker(socket.accept()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    private void loadTemplates() {
        template.loadPage("index", "index.tmpl");
        template.loadPage("page_not_found", "404.tmpl");
        template.loadPage("change_name", "change_group_name.tmpl");
    }

    private class WebWorker implements Runnable {
        private final Socket socket;

        WebWorker(Socket socket) {
            if (socket == null) {
                throw new IllegalArgumentException();
            }
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Request request = new Request(socket.getInputStream());
                Response response = dispatchRequest(request);
                response.write(socket.getOutputStream());

                System.out.println("end processing client");
                socket.close();
            } catch (InternalServerError | UnsupportedMediaTypeException
                    | IOException e) {
                Response response =
                        new Response(Response.StatusCode._500, "error");
                try {
                    response.write(socket.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

        private Response dispatchRequest(Request request) {
            Response result = new Response(Response.StatusCode._404,
                    template.renderPage("page_not_found"));
            if (request == null) {
                return result;
            }

            System.out.println(request);

            switch (request.getPath()) {
                case "/": {
                    result = new Response(Response.StatusCode._200,
                            template.renderPage("index"));
                    break;
                }

                case "/change_name": {
                    if (request.getMethod() == Request.Method.GET) {
                        result = new Response(Response.StatusCode._200,
                                template.renderPage("change_name"));
                    } else if (request.getMethod() == Request.Method.POST) {
                        result = new Response(Response.StatusCode._200,
                                template.renderPage("index"));
                    }
                    break;
                }
            }

            return result;
        }
    }
}