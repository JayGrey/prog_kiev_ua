package homework1.ex4;

public class Phone {
    private int number;
    private Network network;

    public Phone(int number) {
        this.number = number;
    }

    public boolean register(Network network) {
        if (network != null && network.register(this)) {
            this.network = network;
            return true;
        } else {
            return false;
        }
    }

    public void call(Phone phone) {
        if (network == null) {
            System.out.println("network not found");
        } else if (!network.contains(phone)) {
            System.out.format("Number %07d is incorrect%n", phone.number);
        } else {
            System.out.println("beep... beep... beep...");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Phone) {
            Phone other = (Phone) obj;
            return other.number == number;
        } else {
            return false;
        }
    }
}
