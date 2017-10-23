package homework1.ex1;

public class Cat {
    private String color;
    private String name;
    private int age;
    private boolean sleep;

    public Cat(String name, String color, int age) {
        this.color = color;
        this.name = name;
        this.age = age;
    }

    public String say() {
        if (!sleep) {
            return "meow";
        } else  {
            return "[sleeping]";
        }
    }

    public void sleep() {
        sleep = true;
    }

    public  void wakeUp() {
        sleep = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
