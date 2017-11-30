package homework11.ex4.actions;

import homework11.ex4.Request;
import homework11.ex4.Response;
import homework11.ex4.Template;
import homework3.ex3.Group;
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
        if (request.getBody().containsKey("sorted")) {
            String sortField = request.getBody().get("sorted");
            switch (sortField) {
                case "lastname": {
                    group.sort(Group.SortField.LASTNAME,
                            Group.SortOrder.ASCENDING);
                    break;
                }

                case "firstname": {
                    group.sort(Group.SortField.FIRSTNAME,
                            Group.SortOrder.ASCENDING);
                    break;
                }

                case "middlename": {
                    group.sort(Group.SortField.MIDDLENAME,
                            Group.SortOrder.ASCENDING);
                    break;
                }

                case "age": {
                    group.sort(Group.SortField.AGE,
                            Group.SortOrder.ASCENDING);
                    break;
                }

                case "sex": {
                    group.sort(Group.SortField.SEX,
                            Group.SortOrder.ASCENDING);
                    break;
                }
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("group_name", group.getName());
        params.put("students_table", formTable(
                Arrays.asList(group.getAllStudents())));


        return new Response(Response.StatusCode._200,
                template.renderPage("index", params));
    }
}
