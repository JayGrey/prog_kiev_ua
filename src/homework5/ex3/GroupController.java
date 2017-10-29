package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

public class GroupController {
    private GroupDAO groupDAO;
    private Group group;

    public GroupController(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
        group = new Group("test group");
    }

    public void add(Student student) {
        group.addStudent(student);
    }

    public Student[] getAll() {
        return group.getStudents();
    }

    public void delete(Student student) {
        group.deleteStudent(student);
    }

    public void save() {
        groupDAO.saveGroup(group);
    }

    public void load(String groupName) {
        group = groupDAO.loadGroup(groupName);
    }
}
