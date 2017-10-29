package homework5.ex2;

import java.lang.reflect.Array;
import java.util.Iterator;

public class Container<T> implements Iterable<T> {

    private Class<T> type;
    private T[] elements;
    private int index;

    public Container(Class<T> type) {
        @SuppressWarnings("unchecked")
        T[] e = (T[]) Array.newInstance(type, 10);
        elements = e;
        this.type = type;
    }

    public void add(T s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (index == elements.length) {
            resize();
        }
        elements[index++] = s;

    }

    private void resize() {
        @SuppressWarnings("unchecked")
        T[] tmp = (T[]) Array.newInstance(type, index + 10);
        System.arraycopy(elements, 0, tmp, 0, index);
        elements = tmp;
    }

    public boolean contains(T element) {
        for (int i = 0; i < index; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index != Container.this.index;
            }

            @Override
            public T next() {
                return elements[index++];
            }
        };
    }
}
