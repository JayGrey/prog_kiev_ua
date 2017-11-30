package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework5.ex3.GroupController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class IndexAction extends BaseAction {

    private final Template template;

    public IndexAction(GroupController group) {
        super(group);
        this.template = Template.getInstance();
    }

    @Override
    public Response process(Request request) {
        Map<String, String> params = new HashMap<>();
        params.put("group_name", group.getName());
        params.put("students_table", formTable(
                Arrays.asList(group.getAllStudents())));


        return new Response(Response.StatusCode._200,
                template.renderPage("index", params));
    }
}
