package homework3.ex1;

public class Human {
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected int age;
    protected boolean sex;

    public Human(String firstName, String middleName, String lastName, int age,
                 boolean sex) {
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    protected Human() {
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
        return String.format("[%s %s %s, age= %d, sex=%s]", lastName,
                firstName, middleName, age, (sex ? "male" : "female"));
    }
}
