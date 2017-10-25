package homework3.ex3;

import homework3.ex2.Student;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        // test overfill
        System.out.print("test overfill group - ");
        Group group = fillGroup(new Group(), 10);
        try {
            group.addStudent(getRandomFemaleStudent());
            System.out.println("FAIL");
        } catch (GroupFullException e) {
            System.out.println("OK");
        }

        // test add null value
        System.out.print("test add null value - ");
        group = new Group();
        try {
            group.addStudent(null);
            System.out.println("FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println("OK");
        }


        // test delete student by object
        Student student = getRandomMaleStudent();
        group = new Group();
        group.addStudent(getRandomMaleStudent());
        group.addStudent(student);
        group.deleteStudent(student);

        System.out.println("test delete student by object - "
                + (group.getNumberOfStudents() == 1 ? "OK" : "FAIL"));

        // test delete student by object, null argument
        group = new Group();
        group.addStudent(getRandomMaleStudent());
        System.out.print("test delete student by object, null argument - ");
        try {
            group.addStudent(null);
            System.out.println("FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println("OK");
        }

        // find student
        group = new Group();
        student = getRandomMaleStudent();
        group.addStudent(getRandomFemaleStudent());
        group.addStudent(getRandomFemaleStudent());
        group.addStudent(student);
        Student student1 = group.findStudentByLastName(student.getLastName());
        System.out.println("test find student by lastName - "
                + (student.equals(student1) ? "OK" : "FAIL"));

        // find student, null argument
        group = new Group();
        group.addStudent(getRandomMaleStudent());
        System.out.print("test find student by lastName, null argument - ");
        try {
            group.findStudentByLastName(null);
            System.out.println("FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println("OK");
        }

        // find student, non existing lastName
        group = fillGroup(new Group(), 9);
        student = group.findStudentByLastName("");
        System.out.println("test find student, non existing lastName - "
                + (student == null ? "OK" : "FAIL"));

        // display group
        group = fillGroup(new Group(), 9);
        System.out.println(group);

    }

    private static Group fillGroup(Group group, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (i % 2 == 0) {
                group.addStudent(getRandomMaleStudent());
            } else {
                group.addStudent(getRandomFemaleStudent());
            }
        }
        return group;
    }

    private static Student getRandomMaleStudent() {
        String[] lastNames = {"Иванов", "Петров", "Сидоров"};
        String[] firstNames = {"Иван", "Петр", "Андрей", "Алексей", "Олег"};
        String[] middleNames = {"Иванович", "Петрович", "Андреевич",
                "Алексеевич", "Олегович"};

        Random random = new Random();
        return new Student(
                firstNames[random.nextInt(firstNames.length)],
                middleNames[random.nextInt(middleNames.length)],
                lastNames[random.nextInt(lastNames.length)],
                18 + random.nextInt(10),
                true);
    }

    private static Student getRandomFemaleStudent() {
        String[] lastNames = {"Иванова", "Петрова", "Сидорова", "Дмитриева"};
        String[] firstNames = {"Ирина", "Ксения", "Анастасия", "Анна", "Ольга"};
        String[] middleNames = {"Ивановна", "Петровна", "Андреевна",
                "Алексеевна", "Олеговна"};

        Random random = new Random();
        return new Student(
                firstNames[random.nextInt(firstNames.length)],
                middleNames[random.nextInt(middleNames.length)],
                lastNames[random.nextInt(lastNames.length)],
                18 + random.nextInt(10),
                false);
    }
}
