package homework3.ex2;

import homework3.ex1.Human;

public class Student extends Human {
    public Student(String firstName, String middleName, String lastName,
                   int age, boolean sex) {
        super(firstName, middleName, lastName, age, sex);
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Student) {
            Student s = (Student) other;
            return s.firstName.equals(firstName)
                    && s.middleName.equals(middleName)
                    && s.lastName.equals(lastName)
                    && s.age == age
                    && s.sex == sex;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student " + super.toString();
    }
}
