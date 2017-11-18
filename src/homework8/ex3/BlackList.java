package homework8.ex3;

import java.util.HashSet;
import java.util.Set;

public class BlackList {

    private Set<Class> classes = new HashSet<>();

    public void add(Class clazz) {
        if (clazz != null) {
            classes.add(clazz);
        }
    }

    public boolean isBanned(Object o) {
        return o != null && classes.contains(o.getClass());
    }
}
