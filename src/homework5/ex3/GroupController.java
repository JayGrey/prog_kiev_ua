package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

public class GroupController {
    private GroupDAO groupDAO;
    private Group group;

    public GroupController(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
        group = new Group();
    }

    public void save() {
        groupDAO.saveGroup(group);
    }

    public void load(String groupName) {
        group = groupDAO.loadGroup(groupName);
    }

    public void add(Student student) {
        group.addStudent(student);
    }

    public Student[] getAllStudents() {
        return group.getStudents();
    }

    public void setName(String name) {
        group.setName(name);
    }

    public String getName() {
        return group.getName();
    }

}
