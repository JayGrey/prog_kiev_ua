package homework3.ex1;

public class Human {
    protected int age;
    protected boolean sex;
    protected String firstName;
    protected String middleName;
    protected String lastName;

    public Human(String firstName, String middleName, String lastName, int age,
                 boolean sex) {
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Human [%s %s %s, age= %d, sex=%s]", firstName,
                middleName, lastName, age, (sex ? "male" : "female"));
    }
}
