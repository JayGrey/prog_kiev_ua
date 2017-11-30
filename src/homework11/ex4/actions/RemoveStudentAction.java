package homework11.ex4.actions;

import homework11.ex4.Dispatcher;
import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex2.Student;
import homework5.ex3.GroupController;

import java.util.HashMap;
import java.util.Map;


public class RemoveStudentAction implements Action {

    private final Template template;
    private final GroupController group;
    private final Dispatcher dispatcher;

    public RemoveStudentAction(GroupController group) {
        this.group = group;
        this.template = Template.getInstance();
        this.dispatcher = Dispatcher.getInstance();
    }

    @Override
    public Response process(Request request) {
        Response result = null;
        if (request.getMethod() == Request.Method.GET) {
            Map<String, String> params = new HashMap<>();
            params.put("students_cb", formComboBox());
            result = new Response(Response.StatusCode._200,
                    template.renderPage("delete_student", params));
        } else if (request.getMethod() == Request.Method.POST) {
            result = dispatcher.forward("/", request);
        }
        return result;
    }

    private String formComboBox() {
        StringBuilder sb = new StringBuilder();
        if (group.getAllStudents().length > 0) {
            sb.append("<select name=\"name\">").append(System.lineSeparator());
            for (Student student : group.getAllStudents()) {
                sb.append("<option value=\"student\">")
                        .append(String.format("%s %s %s", student.getLastName(),
                                student.getFirstName(), student.getMiddleName
                                        ())).append("</option>");
            }
            sb.append("</select>").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
