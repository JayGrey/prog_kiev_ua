package homework8;

import homework3.ex2.Student;
import homework3.ex3.Group;
import homework8.ex1.Department;
import homework8.ex1.DepartmentDAOImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestHW8Ex1 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void departmentNullArgTest() {
        thrown.expect(NullPointerException.class);
        Department d = new Department(null);
    }

    @Test
    public void departmentAddGroupTest() {
        Department department = new Department("test dpt");
        assertEquals(0, department.getAll().size());

        Group group = new Group("test group");
        department.add(group);
        assertEquals(1, department.getAll().size());

        thrown.expect(NullPointerException.class);
        department.add(null);
    }

    @Test
    public void departmentDeleteGroupTest() {
        Department department = new Department("test dpt");
        assertEquals(0, department.getAll().size());

        Group group = new Group("test group");
        department.add(group);
        assertEquals(1, department.getAll().size());

        department.delete(group);
        assertEquals(0, department.getAll().size());
    }

    @Test
    public void departmentGetGroupTest() {
        Department department = new Department("test dpt");

        Group group = new Group("test group");
        department.add(group);
        assertEquals(1, department.getAll().size());

        Group group1 = department.get("test group");
        assertEquals(group, group1);

        assertNull(department.get(""));

        thrown.expect(NullPointerException.class);
        department.get(null);
    }

    @Test
    public void departmentGetAllTest() {
        Department department = new Department("test dpt");

        Group[] groups = new Group[10];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = new Group("" + ('A' + i));
            department.add(groups[i]);
        }

        assertArrayEquals(groups, department.getAll().toArray(new Group[0]));

    }

    @Test
    public void departmentSetNullNameTest() {
        thrown.expect(NullPointerException.class);
        Department department = new Department("test dpt");
        department.setName(null);
    }

    @Test
    public void serializeTest() throws IOException {
        File file = tempDir.newFile();
        DepartmentDAOImpl dao = new DepartmentDAOImpl(file.getCanonicalPath());

        Group group = new Group("test group");
        group.addStudent(new Student("a", "b", "c", 20, false));
        group.addStudent(new Student("d", "e", "f", 20, true));

        Department department = new Department("test dpt");
        department.add(group);
        dao.save(department);

        assertTrue(file.length() > 0);
    }

    @Test
    public void deserializeTest() throws IOException {
        File file = tempDir.newFile();
        DepartmentDAOImpl dao = new DepartmentDAOImpl(file.getCanonicalPath());

        Group group = new Group("test group");
        group.addStudent(new Student("a", "b", "c", 20, false));
        group.addStudent(new Student("d", "e", "f", 20, true));

        Department department = new Department("test dpt");
        department.add(group);
        dao.save(department);

        Department dpt = dao.load("test dpt");

        assertNotNull(dpt);

        assertArrayEquals(group.getStudents(), dpt.getAll().get(0)
                .getStudents());

    }
}
