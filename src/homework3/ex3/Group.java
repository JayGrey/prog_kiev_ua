package homework3.ex3;

import homework3.ex2.Student;

import javax.swing.*;
import java.util.Arrays;

public class Group {
    public static final int SORT_BY_LASTNAME = 0;
    public static final int SORT_BY_FIRSTNAME = 1;
    public static final int SORT_BY_MIDDLENAME = 2;
    public static final int SORT_BY_AGE = 3;
    public static final int SORT_BY_SEX = 4;

    public static final int ACSENDING_ORDER = 0;
    public static final int DESCENDING_ORDER = 1;

    private static final int MAX_CAPACITY = 10;

    private Student[] students;
    private int currentPosition;

    public Group() {
        students = new Student[MAX_CAPACITY];
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


    public void sortStudentsBy(int sortingField, int sortingOrder) {
        Arrays.sort(students, 0, currentPosition,
                new StudentComparator(sortingField, sortingOrder));
    }
}