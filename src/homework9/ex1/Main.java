package homework9.ex1;

import java.util.LinkedList;
import java.util.List;

public class Main {
    private static void method() {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        System.out.println("before mod: " + list);

        list.remove(0);
        list.remove(0);
        list.remove(list.size() - 1);

        System.out.println("after mod:  " + list);

    }

    public static void main(String[] args) {
        method();
    }
}
