package homework8.ex1;

import homework3.ex3.Group;

import static java.util.Objects.requireNonNull;

public class DepartmentController {
    private DepartmentDAO departmentDAO;
    private Department department;

    public DepartmentController(DepartmentDAO departmentDAO) {
        this.departmentDAO = requireNonNull(departmentDAO);
    }

    public void add(Group group) {
        department.add(group);
    }

    public void save() {
        departmentDAO.save(department);
    }

    public void load(String name) {
        department = departmentDAO.load(name);
    }
}
