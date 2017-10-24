package homework1.ex4;

public class Network {
    private int number;
    private int position;
    private int[] registeredPhones;

    public Network(int number) {
        this(number, 10);
    }

    public Network(int number, int capacity) {
        this.number = number;
        registeredPhones = new int[capacity];
    }

    public boolean register(int phoneNumber) {
        if (position == registeredPhones.length) {
            System.out.println("network is full, unable to register new numbers");
            return false;
        } else if (contains(phoneNumber)) {
            System.out.println("phone number is already registered");
            return false;
        } else {
            registeredPhones[position++] = phoneNumber;
            return true;
        }
    }

    public boolean contains(int phoneNumber) {
        for (int i = 0; i < position; i++) {
            if (phoneNumber == registeredPhones[i]) {
                return true;
            }
        }
        return false;
    }
}
