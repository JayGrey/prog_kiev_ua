package homework4;

import homework3.ex2.Student;
import homework3.ex3.Group;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHW4Ex2 {
    @Test
    public void testSortByLastName() {
        Group group = new Group();
        Student std1 = new Student("Ivan", "Ivanovich", "Ivanov", 20, true);
        Student std2 = new Student("Ivan", "Ivanovich", "Petrenko", 20, true);
        Student std3 = new Student("Ivan", "Ivanovich", "Petrov", 20, true);
        Student std4 = new Student("Ivan", "Ivanovich", "Sidorov", 20, true);

        group.addStudent(std3);
        group.addStudent(std2);
        group.addStudent(std1);
        group.addStudent(std4);

        String[] result = group.toString().split(System.lineSeparator());
        assertEquals(result[1], std1.toString());
        assertEquals(result[2], std2.toString());
        assertEquals(result[3], std3.toString());
        assertEquals(result[4], std4.toString());
    }
}
