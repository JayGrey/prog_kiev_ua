package homework3.ex3;

import homework3.ex2.Student;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;
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

    private Student[] students;
    private int currentPosition;
    private String name;

    public Group() {
        this("");
    }

    public Group(String name) {
        students = new Student[MAX_CAPACITY];
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
        try {
            students[currentPosition++] = student;
        } catch (IndexOutOfBoundsException e) {
            throw new GroupFullException();
        }
    }

    public void deleteStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("argument is null");
        }
        for (int i = 0; i < currentPosition; i++) {
            if (students[i].equals(student)) {
                // копируем хвост массива на место удаляемого элемента
                if (i != (currentPosition - 1)) {
                    System.arraycopy(students, i + 1, students, i,
                            currentPosition - i);
                }
                currentPosition -= 1;
                students[currentPosition] = null;
                break;
            }
        }
    }

    public Student findStudentByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("argument is null");
        }

        Student result = null;
        for (int i = 0; i < currentPosition; i++) {
            if (students[i].getLastName().equals(lastName)) {
                result = students[i];
                break;
            }
        }
        return result;
    }

    private Student[] sortStudentsByLastName() {
        Student[] result = new Student[currentPosition];

        if (currentPosition > 0) {
            System.arraycopy(students, 0, result, 0, currentPosition);
            Arrays.sort(result);
        }

        return result;
    }

    public int getNumberOfStudents() {
        return currentPosition;
    }

    public Student[] getStudents() {
        Student[] result = new Student[currentPosition];

        if (currentPosition > 0) {
            System.arraycopy(students, 0, result, 0, currentPosition);
        }

        return result;
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
                Arrays.sort(students, 0, currentPosition,
                        (a, b) -> a.getLastName()
                                .compareToIgnoreCase(b.getLastName())
                                * order.getOrder());
                break;
            }
            case FIRSTNAME: {
                Arrays.sort(students, 0, currentPosition,
                        (a, b) -> a.getFirstName()
                                .compareToIgnoreCase(b.getFirstName())
                                * order.getOrder());
                break;
            }
            case MIDDLENAME: {
                Arrays.sort(students, 0, currentPosition,
                        (a, b) -> a.getMiddleName()
                                .compareToIgnoreCase(b.getMiddleName())
                                * order.getOrder());
                break;
            }
            case AGE: {
                Arrays.sort(students, 0, currentPosition,
                        (a, b) -> (a.getAge() - b.getAge()) * order.getOrder());
                break;
            }
            case SEX: {
                Arrays.sort(students, 0, currentPosition,
                        (a, b) -> ((!a.isSex() ? 0 : 1) - (!b.isSex() ? 0 : 1))
                                * order.getOrder());
                break;
            }
        }
    }

    @Override
    public Student[] getConscripts() {
        int size = 0;
        for (int i = 0; i < currentPosition; i++) {
            if (students[i].isSex() && students[i].getAge() > 18) {
                size += 1;
            }
        }

        int index = 0;
        Student[] conscripts = new Student[size];
        for (int i = 0; i < currentPosition; i++) {
            if (students[i].isSex() && students[i].getAge() > 18) {
                conscripts[index++] = students[i];
            }
        }

        return conscripts;
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