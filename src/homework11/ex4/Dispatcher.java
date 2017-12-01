package homework11.ex4;

import homework11.ex4.actions.Action;
import homework11.ex4.exceptions.InternalServerException;
import homework11.ex4.exceptions.PageNotFoundException;

import java.util.HashMap;
import java.util.Map;

public final class Dispatcher {

    private static Dispatcher dispatcher;

    private final Template template;
    private final Map<String, Action> actions;

    private Dispatcher() {
        this.template = Template.getInstance();
        actions = new HashMap<>();
    }

    public static Dispatcher getInstance() {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
        }

        return dispatcher;
    }

    public void map(String path, Action action) {
        if (path == null || action == null) {
            throw new InternalServerException("illegal parameters");
        }

        actions.put(path, action);
    }

    public Response dispatch(Request request) {

        Action action = actions.get(request.getPath());

        if (action == null) {
            throw new PageNotFoundException(request.getPath());
        }

        return action.process(request);
    }

    public Response forward(String path, Request request) {
        Action action = actions.get(path);
        if (action == null) {
            throw new PageNotFoundException(path);
        }
        return action.process(request);
    }
}

