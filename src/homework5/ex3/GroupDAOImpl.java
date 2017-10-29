package homework5.ex3;

import homework3.ex2.Student;
import homework3.ex3.Group;

import java.io.*;
import java.util.Iterator;

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

        Group group = new Group();

        Iterator<JParser.Element> iter = new JParser(b.toString()).iterator();
        while (iter.hasNext()) {
            JParser.Element e = iter.next();

            if (e.type == JParser.Type.STRING && e.value.equals("name")) {
                iter.next();
                e = iter.next();
                group.setName(e.value);
                continue;
            }

            if (e.type == JParser.Type.STRING && e.value.equals("students")) {
                iter.next();
                e = iter.next();
                if (e.type != JParser.Type.ARRAY_BEGIN) {
                    throw new RuntimeException("error parsing");
                }

                while ((e = iter.next()).type != JParser.Type.ARRAY_END) {
                    if (e.type == JParser.Type.OBJECT_BEGIN) {
                        String firstname = "";
                        String lastname = "";
                        String middlename = "";
                        int age = 0;
                        boolean sex = false;

                        while (true) {
                            e = iter.next();
                            if (e.type == JParser.Type.STRING
                                    && e.value.equals("firstname")) {
                                iter.next();
                                e = iter.next();
                                firstname = e.value;
                                continue;
                            }
                            if (e.type == JParser.Type.STRING
                                    && e.value.equals("lastname")) {
                                iter.next();
                                e = iter.next();
                                lastname = e.value;
                                continue;
                            }
                            if (e.type == JParser.Type.STRING
                                    && e.value.equals("middlename")) {
                                iter.next();
                                e = iter.next();
                                middlename = e.value;
                                continue;
                            }
                            if (e.type == JParser.Type.STRING
                                    && e.value.equals("age")) {
                                iter.next();
                                e = iter.next();
                                age = Integer.valueOf(e.value);
                                continue;
                            }
                            if (e.type == JParser.Type.STRING
                                    && e.value.equals("sex")) {
                                iter.next();
                                e = iter.next();
                                sex = e.value.equals("true");
                                continue;
                            }
                            if (e.type == JParser.Type.OBJECT_END) {
                                group.addStudent(new Student(firstname,
                                        middlename, lastname, age, sex));
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (!group.getName().equals(groupName)) {
            throw new LoadGroupException("group " + groupName + " not found");
        }
        return group;
    }
}

