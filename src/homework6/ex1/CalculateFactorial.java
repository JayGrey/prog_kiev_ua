package homework6.ex1;

import java.math.BigInteger;

public class CalculateFactorial implements Runnable {

    private int number;

    public CalculateFactorial(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("argument must be > 1");
        }
        this.number = number;
    }

    public BigInteger calculate() {
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= number; i++) {
            result = result.multiply(new BigInteger("" + i));
        }

        return result;
    }

    @Override
    public void run() {
        System.out.format("Thread %1$d: %1$d! = %2$s%n", number, calculate());
    }
}
