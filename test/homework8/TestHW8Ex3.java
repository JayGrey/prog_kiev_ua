package homework8;

import homework8.ex2.Stack;
import homework8.ex3.BlackList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class TestHW8Ex3 {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInBlackList() {
        BlackList blackList = new BlackList();
        blackList.add(Integer.class);

        Stack stack = new Stack();
        stack.setBlackList(blackList);

        stack.push(10);

        thrown.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void testNotInBlackList() {
        BlackList blackList = new BlackList();
        blackList.add(Integer.class);

        Stack stack = new Stack();
        stack.setBlackList(blackList);

        stack.push("string");

        assertEquals("string", stack.peek());
    }
}
