package homework3.ex2;

import homework3.ex1.Human;

public class Student extends Human implements Comparable {
    public Student(String firstName, String middleName, String lastName,
                   int age, boolean sex) {

        if (firstName == null || middleName == null || lastName == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (firstName.length() == 0 || middleName.length() == 0
                || lastName.length() == 0) {
            throw new IllegalArgumentException("String argument is empty");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("age < 0");
        }

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
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

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return lastName.compareToIgnoreCase(other.lastName);
    }
}
