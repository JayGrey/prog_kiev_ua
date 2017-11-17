package homework8;

import homework8.ex2.Stack;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class TestHW8Ex2 {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void pushTest() {
        Stack stack = new Stack();
        stack.push(10);
        stack.push("a");

        assertEquals("a", (String) stack.pop());
        assertEquals(10, (int) stack.pop());

    }

    @Test
    public void pushMoreThanInitialCapacityTest() {
        Stack stack = new Stack();

        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }

        for (int i = 14; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

    }

    @Test
    public void pushNullValueTest() {
        Stack stack = new Stack();
        stack.push(null);

        thrown.expect(EmptyStackException.class);
        stack.peek();
    }

    @Test
    public void popFromEmptyTest() {
        Stack stack = new Stack();
        thrown.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void peekTest() {
        Stack stack = new Stack();

        stack.push(10);

        assertEquals(10, stack.peek());

        assertEquals(10, stack.pop());

        thrown.expect(EmptyStackException.class);
        stack.pop();
    }
}
