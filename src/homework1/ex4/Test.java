package homework1.ex4;

public class Test {
    public static void main(String[] args) {
        Network network = new Network(678);
        Phone phone1 = new Phone(555_12_03);
        Phone phone2 = new Phone(555_10_23);
        Phone phone3 = new Phone(123_66_77);

        phone1.register(network);
        phone2.register(network);
        phone2.register(network);

        phone1.call(phone2);
        phone1.call(phone3);


    }
}
