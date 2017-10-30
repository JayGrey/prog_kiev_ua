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
        String firstname = "";
        String lastname = "";
        String middlename = "";
        int age = 0;
        boolean sex = false;

        for (String field : fields.split(",")) {
            String[] elements = field.split(":");
            switch (elements[0].trim().replaceAll("\"", "")) {
                case "lastname": {
                    lastname = elements[1].trim().replaceAll("\"", "");
                    break;
                }
                case "firstname": {
                    firstname = elements[1].trim().replaceAll("\"", "");
                    break;
                }
                case "middlename": {
                    middlename = elements[1].trim().replaceAll("\"", "");
                    break;
                }
                case "age": {
                    age = Integer.valueOf(elements[1].trim());
                    break;
                }
                case "sex": {
                    sex = elements[1].trim().equals("true");
                    break;
                }
            }
        }
        return new Student(firstname, middlename, lastname, age, sex);
    }
}

