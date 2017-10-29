package homework5;

import homework3.ex2.Student;
import homework5.ex3.GroupController;
import homework5.ex3.GroupDAOImpl;
import homework5.ex3.LoadGroupException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestHW5Ex4 {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Student[] testStudents = {
            new Student("Ivan", "Alekseevich", "Ivanov", 18, true),
            new Student("Ivan", "Andreevich", "Ivanov", 20, true),
            new Student("Kirill", "Ivanovich", "Juravlev", 22, true),
            new Student("Maria", "Olegovna", "Petrova", 24, false),
            new Student("Nina", "Romanovna", "Sidorova", 25, false),
    };

    @Test
    public void testLoad() throws IOException {
        File file = tempFolder.newFile();
        GroupController controller = new GroupController(
                new GroupDAOImpl(file.getCanonicalPath()));

        for (Student student : testStudents) {
            controller.add(student);
        }
        controller.setName("test group");
        controller.save();

        controller.load("test group");

        assertEquals(5, controller.getAllStudents().length);
    }

    @Test
    public void testLoadWrongName() throws IOException {
        File file = tempFolder.newFile();
        GroupController controller = new GroupController(
                new GroupDAOImpl(file.getCanonicalPath()));

        for (Student student : testStudents) {
            controller.add(student);
        }
        controller.setName("test group");
        controller.save();

        thrown.expect(LoadGroupException.class);

        controller.load("test 1 group");
    }
}
