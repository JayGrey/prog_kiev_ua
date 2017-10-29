package homework5;

import homework3.ex2.Student;
import homework5.ex3.GroupController;
import homework5.ex3.GroupDAOImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class TestHW5Ex3 {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private Student[] testStudents = {
            new Student("Ivan", "Alekseevich", "Ivanov", 18, true),
            new Student("Ivan", "Andreevich", "Ivanov", 20, true),
            new Student("Kirill", "Ivanovich", "Juravlev", 22, true),
            new Student("Maria", "Olegovna", "Petrova", 24, false),
            new Student("Nina", "Romanovna", "Sidorova", 25, false),
    };

    @Test
    public void test() throws IOException {
        File file = tempFolder.newFile();
        GroupController controller = new GroupController(
                new GroupDAOImpl(file.getCanonicalPath()));

        for (Student student : testStudents) {
            controller.add(student);
        }
        controller.save();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        }

    }
}
