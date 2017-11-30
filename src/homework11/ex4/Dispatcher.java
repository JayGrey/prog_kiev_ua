package homework11.ex4;

import homework11.ex4.actions.Action;

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
            return;
        }

        actions.put(path, action);
    }

    public Response dispatch(Request request) {

        Action action = actions.get(request.getPath());

        if (action == null) {
            return new Response(Response.StatusCode._404,
                    template.renderPage("page_not_found"));
        }

        return action.process(request);
    }

    public Response forward(String path, Request request) {
        return actions.get(path).process(request);
    }
}

