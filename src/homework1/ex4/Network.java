package homework1.ex4;

public class Network {
    private int number;
    private int position;
    private Phone[] registeredPhones;

    public Network(int number) {
        this(number, 10);
    }

    public Network(int number, int capacity) {
        this.number = number;
        registeredPhones = new Phone[capacity];
    }

    public boolean register(Phone phone) {
        if (position == registeredPhones.length) {
            System.out.println("network is full, unable to register new numbers");
            return false;
        } else if (contains(phone)) {
            System.out.println("phone number is already registered");
            return false;
        } else {
            registeredPhones[position++] = phone;
            return true;
        }
    }

    public boolean contains(Phone phone) {
        for (int i = 0; i < position; i++) {
            if (phone.equals(registeredPhones[i])) {
                return true;
            }
        }
        return false;
    }
}
