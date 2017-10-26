package homework4.ex1;

import homework3.ex3.Group;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHW4Ex1 {

    @Test
    public void testAddStudentInteractive() {
        Group group = new Group();
        assertEquals(0, group.getNumberOfStudents());
        group.addStudent();
        assertEquals(1, group.getNumberOfStudents());
    }
}
