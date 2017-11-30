package homework11.ex4;

import homework11.ex4.actions.AddStudentAction;
import homework11.ex4.actions.ChangeNameAction;
import homework11.ex4.actions.IndexAction;
import homework11.ex4.actions.RemoveStudentAction;
import homework5.ex3.GroupController;
import homework5.ex3.GroupDAOImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private final ExecutorService pool;
    private final Template template;
    private final Dispatcher dispatcher;
    private final GroupController group;


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
        template = Template.getInstance();
        dispatcher = Dispatcher.getInstance();
        group = new GroupController(new GroupDAOImpl("group.json"));
    }

    private void start() {
        System.out.println("Start web server on port " + port);
        loadTemplates();
        bindActions();
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
        template.loadPage("index", "templates/index.tmpl");
        template.loadPage("change_name", "templates/change_group_name.tmpl");
        template.loadPage("add_student", "templates/add_student.tmpl");
        template.loadPage("delete_student", "templates/delete_student.tmpl");
        template.loadPage("page_not_found", "templates/404.tmpl");
    }

    private void bindActions() {
        dispatcher.map("/", new IndexAction(group));
        dispatcher.map("/change_name", new ChangeNameAction(group));
        dispatcher.map("/add_student", new AddStudentAction(group));
        dispatcher.map("/delete_student", new RemoveStudentAction(group));
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
                Request request = new Request().read(socket.getInputStream());
                Response response = dispatcher.dispatch(request);
                response.write(socket.getOutputStream());
                socket.close();
            } catch (InternalServerException | UnsupportedMediaTypeException
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
    }
}