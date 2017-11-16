package homework8.ex1;

import homework3.ex3.Group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Department implements Serializable {
    private String name;
    private List<Group> groups;

    public Department(String name) {
        this.name = requireNonNull(name);
        groups = new ArrayList<>();
    }

    public void add(Group group) {
        if (groups.contains(requireNonNull(group))) {
            throw new DuplicateGroupException();
        }
        groups.add(group);
    }

    public void delete(Group group) {
        groups.remove(requireNonNull(group));
    }

    public Group get(String name) {
        for (Group group : groups) {
            if (group.getName().equals(requireNonNull(name))) {
                return group;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNonNull(name);
    }
}
