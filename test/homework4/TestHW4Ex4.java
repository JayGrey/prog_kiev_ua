package homework4;

import homework3.ex2.Student;
import homework3.ex3.Group;
import homework3.ex3.Voenkom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHW4Ex4 {
    private Student[] testStudents = {
            new Student("Ivan", "Alekseevich", "Ivanov", 18, true),
            new Student("Ivan", "Andreevich", "Ivanov", 20, true),
            new Student("Kirill", "Ivanovich", "Juravlev", 22, true),
            new Student("Maria", "Olegovna", "Petrova", 24, false),
            new Student("Nina", "Romanovna", "Sidorova", 25, false),
    };

    @Test
    public void testVoenkomInterface() {
        Group group = new Group();

        for (Student student : testStudents) {
            group.addStudent(student);
        }

        Voenkom voenkom = group;

        Student[] students = voenkom.getConscripts();
        assertEquals(2, students.length);
        assertEquals(testStudents[1], students[0]);
        assertEquals(testStudents[2], students[1]);
    }
}
