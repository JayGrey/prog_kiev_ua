package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex2.Student;
import homework5.ex3.GroupController;

import java.util.HashMap;
import java.util.Map;


public class IndexAction implements Action {

    private final Template template;
    private final GroupController group;

    public IndexAction(GroupController group) {
        this.template = Template.getInstance();
        this.group = group;
    }

    @Override
    public Response process(Request request) {
        Map<String, String> params = new HashMap<>();
        params.put("group_name", group.getName());
        params.put("students_table", formTable());


        return new Response(Response.StatusCode._200,
                template.renderPage("index", params));
    }

    private String formTable() {
        StringBuilder result = new StringBuilder();
        for (Student student : group.getAllStudents()) {
            result.append("<tr>").append(System.lineSeparator());
            result.append("<td>").append(student.getLastName()).append("</td>");
            result.append("<td>").append(student.getFirstName()).append("</td>");
            result.append("<td>").append(student.getMiddleName()).append("</td>");
            result.append("<td>").append(student.getAge()).append("</td>");
            result.append("<td>").append(student.isSex() ? "male" : "female")
                    .append("</td>");
            result.append("</tr>").append(System.lineSeparator());
        }
        return result.toString();
    }
}
