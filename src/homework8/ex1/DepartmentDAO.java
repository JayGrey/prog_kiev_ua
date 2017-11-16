package homework8.ex1;

public interface DepartmentDAO {
    void save(Department department);
    Department load(String name);
}
