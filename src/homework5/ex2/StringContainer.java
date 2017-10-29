package homework5.ex2;

import java.util.Iterator;

public class StringContainer implements Iterable<String> {

    private String[] strings;
    private int index;

    public StringContainer() {
        strings = new String[10];
    }

    public void add(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (index == strings.length) {
            resize();
        }
        strings[index++] = s;

    }

    private void resize() {
        String[] tmp = new String[index + 10];
        System.arraycopy(strings, 0, tmp, 0, index);
        strings = tmp;
    }

    public boolean contains(String s) {
        for (int i = 0; i < index; i++) {
            if (strings[i].compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return index;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index != StringContainer.this.index;
            }

            @Override
            public String next() {
                return strings[index++];
            }
        };
    }
}
