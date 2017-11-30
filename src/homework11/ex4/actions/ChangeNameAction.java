package homework11.ex4.actions;


import homework11.ex4.Dispatcher;
import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework5.ex3.GroupController;

public class ChangeNameAction extends BaseAction {

    private final Template template;
    private Dispatcher dispatcher;

    public ChangeNameAction(GroupController group) {
        super(group);
        this.template = Template.getInstance();
        this.dispatcher = Dispatcher.getInstance();
    }

    @Override
    public Response process(Request request) {
        Response result = null;
        if (request.getMethod() == Request.Method.GET) {
            result = new Response(Response.StatusCode._200,
                    template.renderPage("change_name"));
        } else if (request.getMethod() == Request.Method.POST) {
            group.setName(request.getBody().get("groupName"));

            result = dispatcher.forward("/", request);
        }

        return result;
    }
}
