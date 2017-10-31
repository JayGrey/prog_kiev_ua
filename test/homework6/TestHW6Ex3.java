package homework6;

import homework6.ex3.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;

public class TestHW6Ex3 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testSimpleSortArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new SimpleSort().sort(null);
    }

    @Test
    public void testSimpleSort() {
        int[] result = new SimpleSort().sort(new int[]{5, 4, 3, 2, 1});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);

        result = new SimpleSort().sort(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testInsertSortArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new InsertSort().sort(null);
    }

    @Test
    public void testInsertSort() {
        int[] result = new InsertSort().sort(new int[]{5, 4, 3, 2, 1});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);

        result = new InsertSort().sort(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testJDKSortArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new JDKSort().sort(null);
    }

    @Test
    public void testJDKSort() {
        int[] result = new JDKSort().sort(new int[]{5, 4, 3, 2, 1});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);

        result = new JDKSort().sort(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testShellSortArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new ShellSort().sort(null);
    }

    @Test
    public void testShellSort() {
        int[] result = new ShellSort().sort(new int[]{5, 4, 3, 2, 1});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testPShellSortArgumentNull() {
        thrown.expect(IllegalArgumentException.class);
        new PShellSort().sort(null);
    }

    @Test
    public void testPShellSort() {
        int[] result = new PShellSort().sort(new int[]{11, 10, 9, 8, 7, 6, 5, 4,
                3, 2, 1});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, result);
    }

}
