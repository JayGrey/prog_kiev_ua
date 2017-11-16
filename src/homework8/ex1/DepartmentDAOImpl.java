package homework8.ex1;

import java.io.*;

import static java.util.Objects.requireNonNull;

public class DepartmentDAOImpl implements DepartmentDAO {

    private String fileName;

    public DepartmentDAOImpl(String fileName) {
        this.fileName = requireNonNull(fileName);
    }

    @Override
    public void save(Department department) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.writeObject(department);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Department load(String name) {
        Department dpt;

        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))) {
            dpt = (Department) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return dpt.getName().equals(requireNonNull(name)) ? dpt : null;
    }
}
