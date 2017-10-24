package homework1.ex4;

public class Phone {
    private int number;
    private Network network;
    private String model;

    public Phone(String model, int number) {
        this.model = model;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean register(Network network) {
        if (network != null && network.register(number)) {
            this.network = network;
            return true;
        } else {
            return false;
        }
    }

    public void call(int phoneNumber) {
        if (network == null) {
            System.out.println("network not found");
        } else if (!network.contains(phoneNumber)) {
            System.out.format("Number %07d is incorrect%n", phoneNumber);
        } else {
            System.out.println("beep... beep... beep...");
        }
    }
}
