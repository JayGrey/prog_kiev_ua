package homework6;

import homework6.ex1.CalculateFactorial;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;


public class TestHW6Ex1 {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFactorial() {
        CalculateFactorial factorial = new CalculateFactorial(10);
        assertEquals(new BigInteger("3628800"), factorial.calculate());
    }

    @Test
    public void testWrongArgument() {
        thrown.expect(IllegalArgumentException.class);
        new CalculateFactorial(0);
    }
}
