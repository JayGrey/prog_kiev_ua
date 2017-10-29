package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

import java.io.*;

public class GroupDAOImpl implements GroupDAO {

    private String filename;

    public GroupDAOImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveGroup(Group group) {
        if (group == null) {
            throw new IllegalArgumentException();
        }

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            writer.println('{');
            writer.format("\t\"name\": \"%s\",%n", group.getName());

            writer.format("\t\"students\": [%n");
            Student[] students = group.getStudents();
            for (int i = 0; i < students.length; i++) {
                Student student = students[i];
                writer.format("\t\t{%n");

                writer.format("\t\t\t\"lastname\": \"%s\",%n",
                        student.getLastName());

                writer.format("\t\t\t\"firstname\": \"%s\",%n",
                        student.getFirstName());

                writer.format("\t\t\t\"middlename\": \"%s\",%n",
                        student.getMiddleName());

                writer.format("\t\t\t\"age\": %d,%n", student.getAge());

                writer.format("\t\t\t\"sex\": %s%n", student.isSex());

                writer.format("\t\t%s%n",
                        (i == students.length - 1) ? "}" : "},");
            }
            writer.format("\t]%n");
            writer.println('}');
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group loadGroup(String groupName) {
        return null;
    }
}
