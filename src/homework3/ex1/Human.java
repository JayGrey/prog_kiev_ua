package homework3.ex1;

public class Human {
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private boolean sex;

    public Human(String firstName, String middleName, String lastName, int age,
                 boolean sex) {
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("[%s %s %s, age= %d, sex=%s]", firstName,
                middleName, lastName, age, (sex ? "male" : "female"));
    }
}
