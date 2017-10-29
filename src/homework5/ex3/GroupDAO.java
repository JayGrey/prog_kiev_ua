package homework5.ex3;

import homework3.ex3.Group;

public interface GroupDAO {
    void saveGroup(Group group);
    Group loadGroup(String groupName);
}
