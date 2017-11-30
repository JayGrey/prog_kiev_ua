package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex2.Student;
import homework5.ex3.GroupController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindStudentAction extends BaseAction {
    private Template template;

    public FindStudentAction(GroupController group) {
        super(group);
        this.template = Template.getInstance();
    }

    @Override
    public Response process(Request request) {
        Map<String, String> params = new HashMap<>();
        params.put("students_table", "");

        if (request.getBody().containsKey("lastname")) {
            String lastname = request.getBody().get("lastname");
            List<Student> students = group.findAllByLastName(lastname);
            params.put("students_table", formTable(students));
        }

        return new Response(Response.StatusCode._200,
                template.renderPage("find_student", params));
    }
}
