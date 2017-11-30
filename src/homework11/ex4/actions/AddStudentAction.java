package homework11.ex4.actions;


import homework11.ex4.Dispatcher;
import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex2.Student;
import homework5.ex3.GroupController;

public class AddStudentAction extends BaseAction {

    private final Template template;
    private final Dispatcher dispatcher;

    public AddStudentAction(GroupController group) {
        super(group);
        this.template = Template.getInstance();
        this.dispatcher = Dispatcher.getInstance();
    }

    @Override
    public Response process(Request request) {
        Response result = null;
        if (request.getMethod() == Request.Method.GET) {
            result = new Response(Response.StatusCode._200,
                    template.renderPage("add_student"));
        } else if (request.getMethod() == Request.Method.POST) {
            Student student = new Student(
                    request.getBody().get("firstname"),
                    request.getBody().get("middlename"),
                    request.getBody().get("lastname"),
                    Integer.valueOf(request.getBody().get("age")),
                    request.getBody().get("sex").equalsIgnoreCase("male")
            );

            group.add(student);

            result = dispatcher.forward("/", request);
        }

        return result;
    }
}
