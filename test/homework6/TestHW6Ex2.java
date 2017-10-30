package homework6;

import homework6.ex2.ParallelSum;
import homework6.ex2.Sum;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TestHW6Ex2 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSum() {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        assertEquals(55, new Sum().calculate(array));
    }

    @Test
    public void testSumEmptyArray() {
        assertEquals(0, new Sum().calculate(new int[0]));
    }

    @Test
    public void testSumNull() {
        thrown.expect(IllegalArgumentException.class);
        new Sum().calculate(null);
    }

    @Test
    public void testParallelSum() {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(55, new ParallelSum(10).calculate(array));

        assertEquals(55, new ParallelSum(5).calculate(array));

        array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(45, new ParallelSum(10).calculate(array));

        assertEquals(45, new ParallelSum(5).calculate(array));
    }

    @Test
    public void testManyThreads() {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(55, new ParallelSum(20).calculate(array));
    }

    @Test
    public void testParallelSumEmptyArray() {
        assertEquals(0, new ParallelSum(10).calculate(new int[0]));
    }

    @Test
    public void testParallelSumZeroThreads() {
        thrown.expect(IllegalArgumentException.class);
        new ParallelSum(0).calculate(new int[]{0, 1, 2});
    }

    @Test
    public void testParallelSumNull() {
        thrown.expect(IllegalArgumentException.class);
        new ParallelSum(10).calculate(null);
    }
}
