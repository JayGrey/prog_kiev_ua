package homework11.ex4.actions;

import homework3.ex2.Student;
import homework5.ex3.GroupController;

import java.util.List;

public abstract class BaseAction implements Action {

    protected final GroupController group;

    public BaseAction(GroupController group) {
        this.group = group;
    }

    protected String formTable(List<Student> students) {
        StringBuilder result = new StringBuilder();
        for (Student student : students) {
            result.append("<tr>").append(System.lineSeparator());
            result.append("<td>").append(student.getLastName()).append("</td>");
            result.append("<td>").append(student.getFirstName()).append
                    ("</td>");
            result.append("<td>").append(student.getMiddleName()).append
                    ("</td>");
            result.append("<td>").append(student.getAge()).append("</td>");
            result.append("<td>").append(student.isSex() ? "male" : "female")
                    .append("</td>");
            result.append("</tr>").append(System.lineSeparator());
        }
        return result.toString();
    }
}
