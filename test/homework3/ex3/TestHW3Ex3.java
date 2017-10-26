package homework3.ex3;

import homework3.ex2.Student;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestHW3Ex3 {

    @Test
    public void testOverfill() {
        Group group = fillGroup(new Group(), 10);
        assertEquals(10, group.getNumberOfStudents());
    }

    @Test
    public void testAddNullValue() {
        Group group = new Group();
        try {
            group.addStudent(null);
            fail();
        } catch (IllegalArgumentException e) {
            // all OK
        }
    }

    @Test
    public void testDeleteStudentByObject() {
        Student student = getRandomMaleStudent();
        Group group = new Group();

        group.addStudent(getRandomMaleStudent());
        group.addStudent(student);
        assertEquals(2, group.getNumberOfStudents());

        group.deleteStudent(student);
        assertEquals(1, group.getNumberOfStudents());
    }

    @Test
    public void testDeleteStudentByObjectNullArgument() {
        Group group = new Group();
        group.addStudent(getRandomMaleStudent());
        try {
            group.addStudent(null);
            fail();
        } catch (IllegalArgumentException e) {
            // all OK
        }
    }

    @Test
    public void testFindStudent() {
        Group group = new Group();
        Student student = getRandomMaleStudent();
        group.addStudent(getRandomFemaleStudent());
        group.addStudent(getRandomFemaleStudent());
        group.addStudent(student);

        assertEquals(student,
                group.findStudentByLastName(student.getLastName()));
    }

    @Test
    public void testFindStudentNullArgument() {
        Group group = new Group();
        group.addStudent(getRandomMaleStudent());

        try {
            group.findStudentByLastName(null);
            fail();
        } catch (IllegalArgumentException e) {
            // All OK
        }
    }

    @Test
    public void testFindStudentNonExistingLastName() {
        Group group = fillGroup(new Group(), 9);

        assertNull(group.findStudentByLastName(""));
    }

    @Test
    public void testDisplay() {
        Group group = fillGroup(new Group(), 9);
        String output = group.toString();

        assertTrue(output.startsWith("Group consist of"));
    }

    private static Group fillGroup(Group group, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (i % 2 == 0) {
                group.addStudent(getRandomMaleStudent());
            } else {
                group.addStudent(getRandomFemaleStudent());
            }
        }
        return group;
    }

    private static Student getRandomMaleStudent() {
        String[] lastNames = {"Иванов", "Петров", "Сидоров"};
        String[] firstNames = {"Иван", "Петр", "Андрей", "Алексей", "Олег"};
        String[] middleNames = {"Иванович", "Петрович", "Андреевич",
                "Алексеевич", "Олегович"};

        Random random = new Random();
        return new Student(
                firstNames[random.nextInt(firstNames.length)],
                middleNames[random.nextInt(middleNames.length)],
                lastNames[random.nextInt(lastNames.length)],
                18 + random.nextInt(10),
                true);
    }

    private static Student getRandomFemaleStudent() {
        String[] lastNames = {"Иванова", "Петрова", "Сидорова", "Дмитриева"};
        String[] firstNames = {"Ирина", "Ксения", "Анастасия", "Анна", "Ольга"};
        String[] middleNames = {"Ивановна", "Петровна", "Андреевна",
                "Алексеевна", "Олеговна"};

        Random random = new Random();
        return new Student(
                firstNames[random.nextInt(firstNames.length)],
                middleNames[random.nextInt(middleNames.length)],
                lastNames[random.nextInt(lastNames.length)],
                18 + random.nextInt(10),
                false);
    }
}
