package homework3.ex3;

import homework3.ex2.Student;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Group implements Voenkom, Serializable {


    public enum SortField {
        LASTNAME, FIRSTNAME, MIDDLENAME, AGE, SEX
    }

    public enum SortOrder {
        ASCENDING(1), DESCENDING(-1);

        private final int order;

        SortOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }

    }

    private static final int MAX_CAPACITY = 10;

    private List<Student> students;

    private String name;

    public Group() {
        this("");
    }

    public Group(String name) {
        students = new ArrayList<>(MAX_CAPACITY);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // interactive

    public void addStudent() {
        while (true) {
            try {
                String lastName = JOptionPane.showInputDialog(null, "LastName:",
                        "Add student", JOptionPane.PLAIN_MESSAGE);

                String firstName = JOptionPane.showInputDialog(null,
                        "FirstName:", "Add student", JOptionPane.PLAIN_MESSAGE);

                String middleName = JOptionPane.showInputDialog(null,
                        "MiddleName:", "Add student",
                        JOptionPane.PLAIN_MESSAGE);

                int age = Integer.valueOf(JOptionPane.showInputDialog(null,
                        "Age:", "Add student", JOptionPane.PLAIN_MESSAGE));

                String sex = (String) JOptionPane.showInputDialog(null, "Sex:",
                        "Add student", JOptionPane.PLAIN_MESSAGE, null,
                        new String[]{"Male", "Female"}, "Male");

                addStudent(new Student(firstName, middleName, lastName, age,
                        sex.equals("Male")));
                break;
            } catch (IllegalArgumentException | NullPointerException e) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "Error adding student to group, try again ?", "Message",
                        JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.NO_OPTION) {
                    break;
                }
            }
        }

    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (students.size() == MAX_CAPACITY) {
            throw new GroupFullException();
        }

        students.add(student);
    }

    public void deleteStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("argument is null");
        }

        students.remove(student);
    }

    public void deleteStudent(int id) {
        students.removeIf(s -> (s.getId() == id));
    }

    public Student findStudentByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("argument is null");
        }

        return students.stream()
                .filter(s -> s.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    private Student[] sortStudentsByLastName() {
        Student[] result = students.toArray(new Student[0]);
        Arrays.sort(result);

        return result;
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public Student[] getStudents() {
        return students.toArray(new Student[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group consist of:").append(System.lineSeparator());

        for (Student s : sortStudentsByLastName()) {
            sb.append(s).append(System.lineSeparator());
        }
        return sb.toString();
    }


    public void sortStudentsBy(SortField field, SortOrder order) {
        if (field == null || order == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case LASTNAME: {
                students.sort((a, b) -> a.getLastName()
                        .compareToIgnoreCase(b.getLastName())
                        * order.getOrder());
                break;
            }
            case FIRSTNAME: {
                students.sort((a, b) -> a.getFirstName()
                        .compareToIgnoreCase(b.getFirstName())
                        * order.getOrder());
                break;
            }
            case MIDDLENAME: {
                students.sort((a, b) -> a.getMiddleName()
                        .compareToIgnoreCase(b.getMiddleName())
                        * order.getOrder());
                break;
            }
            case AGE: {
                students.sort((a, b) -> (a.getAge() - b.getAge())
                        * order.getOrder());
                break;
            }
            case SEX: {
                students.sort((a, b) ->
                        ((!a.isSex() ? 0 : 1) - (!b.isSex() ? 0 : 1))
                                * order.getOrder());
                break;
            }
        }
    }

    @Override
    public Student[] getConscripts() {
        return students.stream()
                .filter(s -> s.isSex() && s.getAge() > 18)
                .toArray(Student[]::new);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}