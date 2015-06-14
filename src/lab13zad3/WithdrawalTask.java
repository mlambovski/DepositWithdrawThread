package lab13zad3;

import java.util.Random;

public class WithdrawalTask implements Runnable {

    private AccountWithLock acc;
    private Random generator = new Random();

    public WithdrawalTask(AccountWithLock acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        while (true) {
            acc.withdraw(generator.nextInt(9) + 1);
        }
    }
}
