package homework4.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHW4Ex3 {
    private Student[] testStudents = {
            new Student("Ivan", "Andreevich", "Ivanov", 20, true),
            new Student("Kirill", "Ivanovich", "Juravlev", 22, true),
            new Student("Maria", "Olegovna", "Petrova", 24, false),
            new Student("Nina", "Romanovna", "Sidorova", 25, false),
    };

    @Test
    public void testGetStudents() {
        Group group = new Group();
        Student[] students = group.getStudents();
        assertEquals(0, students.length);

        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[1]);
        students = group.getStudents();

        assertEquals(2, students.length);
        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[1], students[1]);
    }

    @Test
    public void testSortStudentsByLastNameAscendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_LASTNAME, Group.ACSENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[1], students[1]);
        assertEquals(testStudents[2], students[2]);
        assertEquals(testStudents[3], students[3]);
    }

    @Test
    public void testSortStudentsByLastNameDescendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_LASTNAME, Group.DESCENDING_ORDER);

        Student[] students = group.getStudents();
        assertEquals(testStudents[0], students[3]);
        assertEquals(testStudents[1], students[2]);
        assertEquals(testStudents[2], students[1]);
        assertEquals(testStudents[3], students[0]);
    }

    @Test
    public void testSortStudentsByFirstNameAscendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_FIRSTNAME, Group.ACSENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[1], students[1]);
        assertEquals(testStudents[2], students[2]);
        assertEquals(testStudents[3], students[3]);
    }

    @Test
    public void testSortStudentsByFirstNameDescendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_FIRSTNAME, Group.DESCENDING_ORDER);

        Student[] students = group.getStudents();
        assertEquals(testStudents[0], students[3]);
        assertEquals(testStudents[1], students[2]);
        assertEquals(testStudents[2], students[1]);
        assertEquals(testStudents[3], students[0]);
    }

    @Test
    public void testSortStudentsByMiddleNameAscendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_MIDDLENAME, Group.ACSENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[1], students[1]);
        assertEquals(testStudents[2], students[2]);
        assertEquals(testStudents[3], students[3]);
    }

    @Test
    public void testSortStudentsByMiddleNameDescendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_MIDDLENAME, Group.DESCENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[3]);
        assertEquals(testStudents[1], students[2]);
        assertEquals(testStudents[2], students[1]);
        assertEquals(testStudents[3], students[0]);
    }

    @Test

    public void testSortStudentsByAgeAscendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_AGE, Group.ACSENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[1], students[1]);
        assertEquals(testStudents[2], students[2]);
        assertEquals(testStudents[3], students[3]);
    }

    @Test
    public void testSortStudentsByAgeDescendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[1]);
        group.addStudent(testStudents[3]);
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_AGE, Group.DESCENDING_ORDER);

        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[3]);
        assertEquals(testStudents[1], students[2]);
        assertEquals(testStudents[2], students[1]);
        assertEquals(testStudents[3], students[0]);
    }

    @Test
    public void testSortStudentsBySexAscendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[0]);
        group.addStudent(testStudents[2]);

        group.sortStudentsBy(Group.SORT_BY_SEX, Group.ACSENDING_ORDER);
        Student[] students = group.getStudents();

        assertEquals(testStudents[2], students[0]);
        assertEquals(testStudents[0], students[1]);
    }

    @Test
    public void testSortStudentsBySexDescendingOrder() {
        Group group = new Group();
        group.addStudent(testStudents[2]);
        group.addStudent(testStudents[0]);

        group.sortStudentsBy(Group.SORT_BY_SEX, Group.DESCENDING_ORDER);
        Student[] students = group.getStudents();

        assertEquals(testStudents[0], students[0]);
        assertEquals(testStudents[2], students[1]);
    }
}
