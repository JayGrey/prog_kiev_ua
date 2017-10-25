package homework3.ex3;

import homework3.ex2.Student;

public class Group {
    private static final int MAX_CAPACITY = 10;

    private Student[] students;
    private int currentPosition;

    public Group() {
        students = new Student[MAX_CAPACITY];
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
        for (int i=0; i < currentPosition; i++) {
            if (students[i].getLastName().equals(lastName)) {
                result = students[i];
                break;
            }
        }
        return result;
    }

    private Student[] sortStudentsByLastName() {
        Student[] result = new Student[currentPosition];
        int index = 0;
        for (Student student : students) {
            if (student != null) {
                result[index++] = student;
            }
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i].getLastName()
                        .compareTo(result[j].getLastName()) > 0) {
                    Student tmp = result[i];
                    result[i] = result[j];
                    result[j] = tmp;
                }
            }
        }

        return result;
    }

    public int getNumberOfStudents() {
        return currentPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group consist of:").append(System.lineSeparator());

        for (Student s : sortStudentsByLastName()) {
            sb.append("student: ").append(s).append(System.lineSeparator());
        }
        return sb.toString();
    }


}
