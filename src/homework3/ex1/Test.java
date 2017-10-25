package homework3.ex1;

public class Test {
    public static void main(String[] args) {
        Human human1 = new Human("Иван", "Иванович", "Иванов", 20, true);
        Human human2 = new Human("Анна", "Ивановна", "Иванова", 30, false);

        System.out.println(human1);
        System.out.println(human2);
    }
}
