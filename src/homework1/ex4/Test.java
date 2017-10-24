package homework1.ex4;

public class Test {
    public static void main(String[] args) {
        Network network = new Network(678);
        Phone phone1 = new Phone("Samsung", 555_12_03);
        Phone phone2 = new Phone("Nokia", 555_10_23);
        Phone phone3 = new Phone("Phillips", 123_66_77);

        phone1.register(network);
        phone2.register(network);
        phone2.register(network);

        phone1.call(phone2.getNumber());
        phone1.call(phone3.getNumber());


    }
}
