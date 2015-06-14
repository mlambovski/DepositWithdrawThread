package lab13zad3;

import java.util.Random;

public class DepositTask implements Runnable {

    private AccountWithLock acc;
    private Random generator = new Random();

    public DepositTask(AccountWithLock acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        while (true) {
            acc.deposit(generator.nextInt(9) + 1);
        }
    }
}
