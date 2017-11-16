package homework8.ex1;

import homework3.ex3.Group;

public class DepartmentController {
    private DepartmentDAO departmentDAO;
    private Department department;

    public DepartmentController(DepartmentDAO departmentDAO) {
        checkArgs(departmentDAO);
        this.departmentDAO = departmentDAO;
    }

    private void checkArgs(DepartmentDAO departmentDAO) {

    }

    public void add(Group group) {

    }

    public void save() {
        departmentDAO.save(department);
    }

    public void load(String name) {
        department = departmentDAO.load(name);
    }
}
