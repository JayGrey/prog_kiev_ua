package homework3.ex2;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestHW3Ex2 {

    @Test
    public void testStudent() {
        Student student = new Student("Ivan", "Ivanovich", "Ivanov", 20, true);
        assertNotNull(student);
    }

    @Test
    public void testStudentNullArgs() {
        try {
            new Student(null, "Ivanovich", "Ivanov", 20, true);
            new Student("Ivanon", null, "Ivanov", 20, true);
            new Student("Ivanon", "Ivanovich", null, 20, true);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }
    }

    @Test
    public void testStudentEmptyStringArg() {
        try {
            new Student("", "Ivanovich", "Ivanov", 20, true);
            new Student("Ivan", "", "Ivanov", 20, true);
            new Student("Ivan", "Ivanovich", "", 20, true);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }
    }

    @Test
    public void TestStudentAge() {
        try {
            new Student("Ivan", "Ivanovich", "Ivanov", -10, true);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }
    }
}
