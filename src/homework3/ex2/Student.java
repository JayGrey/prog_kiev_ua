package homework3.ex2;

import homework3.ex1.Human;

public class Student extends Human {
    public Student(String firstName, String middleName, String lastName,
                   int age, boolean sex) {
        super(firstName, middleName, lastName, age, sex);
    }

    @Override
    public String toString() {
        return "Student " + super.toString();
    }
}
