package homework1.ex1;

public class Main {
    private static void playWithCat(Cat cat) {
        System.out.println(cat.getName() + " say: " + cat.say());
    }

    public static void main(String[] args) {
        Cat cat = new Cat("cat1", "white", 1);
        System.out.println(cat);

        playWithCat(cat);
        cat.sleep();
        playWithCat(cat);
        cat.wakeUp();
        playWithCat(cat);

        //
        cat = new Cat("cat2", "black", 2);
        playWithCat(cat);

    }
}
