package homework8.ex2;

import java.util.EmptyStackException;

public class Stack {

    private Object[] objects;
    private int index;

    public Stack() {
        objects = new Object[10];
    }

    public void push(Object o) {
        if (o == null) {
            return;
        }
        if (index == objects.length) {
            Object[] tmp = new Object[objects.length + 10];
            System.arraycopy(objects, 0, tmp, 0, index);
            objects = tmp;
        }
        objects[index++] = o;
    }

    public Object pop() {
        if (index < 1) {
            throw new EmptyStackException();
        }
        return objects[--index];
    }

    public Object peek() {
        if (index < 1) {
            throw new EmptyStackException();
        }
        return objects[index - 1];
    }
}
