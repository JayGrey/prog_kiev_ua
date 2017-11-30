package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex2.Student;
import homework5.ex3.GroupController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindConscriptsAction extends BaseAction {
    private Template template;

    public FindConscriptsAction(GroupController group) {
        super(group);
        template = Template.getInstance();
    }

    @Override
    public Response process(Request request) {
        List<Student> conscripts = group.getConscripts();
        Map<String, String> params = new HashMap<>();
        params.put("conscripts_table", formTable(conscripts));
        return new Response(Response.StatusCode._200,
                template.renderPage("find_conscripts", params));
    }
}
