package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream(filename))) {
            writer.println('{');
            writeField(writer, "name", group.getName(), false);

            writer.println("\"students\": [");
            Student[] students = group.getStudents();
            for (int i = 0; i < students.length; i++) {
                Student s = students[i];
                writer.println("{");
                writeField(writer, "lastname", s.getLastName(), false);
                writeField(writer, "firstname", s.getFirstName(), false);
                writeField(writer, "middlename", s.getMiddleName(), false);
                writeField(writer, "age", s.getAge(), false);
                writeField(writer, "sex", s.isSex(), true);
                writer.println((i == students.length - 1) ? "}" : "},");
            }
            writer.println("]}");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeField(PrintWriter writer, String name, String value,
                            boolean lastField) {
        writer.format("\"%s\": \"%s\"%s%n", name, value, lastField ? "" : ",");
    }

    private void writeField(PrintWriter writer, String name, int value,
                            boolean isLastField) {
        writer.format("\"%s\": %d%s%n", name, value, isLastField ? "" : ",");
    }

    private void writeField(PrintWriter writer, String name, boolean value,
                            boolean isLastField) {
        writer.format("\"%s\": %s%s%n", name, value, isLastField ? "" : ",");
    }

    @Override
    public Group loadGroup(String groupName) {
        StringBuilder b = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename))) {
            while (reader.ready()) {
                b.append(reader.readLine().trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String name = getGroupName(b);
        Student[] students = getStudents(b);

        if (!name.equals(groupName)) {
            throw new LoadGroupException("group " + groupName + " not found");
        }

        Group group = new Group(name);
        for (Student student : students) {
            group.addStudent(student);
        }
        return group;
    }

    private String getGroupName(StringBuilder b) {
        String regex = "\"name\"\\s*:\\s*\"([^\"]*)\"\\s*,";
        String result = "";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = p.matcher(b);
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    private Student[] getStudents(StringBuilder b) {
        String regex = "\\\"students\\\"\\s*:\\s*\\[\\s*(.*)\\s*\\]";
        String result = "";
        List<Student> students = new ArrayList<>();

        // get objects
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = p.matcher(b);
        if (matcher.find()) {
            result = matcher.group(1);
        }

        // split them
        regex = "\\{(.*?)\\}";
        p = Pattern.compile(regex, Pattern.DOTALL);
        matcher = p.matcher(result);
        while (matcher.find()) {
            students.add(getStudent(matcher.group(1)));
        }

        return students.toArray(new Student[0]);
    }

    private Student getStudent(String fields) {
        return new Student(
                getField(fields, "firstname"),
                getField(fields, "middlename"),
                getField(fields, "lastname"),
                Integer.valueOf(getField(fields, "age")),
                getField(fields, "sex").equals("true")
        );
    }

    private String getField(String fields, String fieldName) {
        for (String field : fields.split(",")) {
            String[] elements = field.split(":");
            if (elements[0].trim().replace("\"", "").equals(fieldName)) {
                return elements[1].trim().replace("\"", "");
            }
        }
        return "";
    }
}

