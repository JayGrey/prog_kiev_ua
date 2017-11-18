package homework8.ex2;

import homework8.ex3.BlackList;

import java.util.EmptyStackException;

public class Stack {

    private Object[] objects;
    private int index;
    private BlackList blackList;

    public Stack() {
        objects = new Object[10];
    }

    public void setBlackList(BlackList blackList) {
        this.blackList = blackList;
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

        if (blackList != null && !blackList.isBanned(o)) {
            objects[index++] = o;
        }
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
