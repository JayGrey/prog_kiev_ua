package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

import java.util.Arrays;
import java.util.List;

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

    public synchronized void add(Student student) {
        group.addStudent(student);
    }

    public synchronized void delete(int id) {
        group.deleteStudent(id);
    }

    public List<Student> findAllByLastName(String lastName) {
        return group.findAllByLastName(lastName);
    }

    public List<Student> getConscripts() {
        return Arrays.asList(group.getConscripts());
    }

    public synchronized void sort(Group.SortField field, Group.SortOrder
            order) {
        group.sortStudentsBy(field, order);
    }

    public Student[] getAllStudents() {
        return group.getStudents();
    }

    public synchronized void setName(String name) {
        group.setName(name);
    }

    public String getName() {
        return group.getName();
    }

}
