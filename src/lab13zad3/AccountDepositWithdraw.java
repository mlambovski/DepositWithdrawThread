package lab13zad3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountDepositWithdraw {

    public static void main(String[] args) {
        AccountWithLock account = new AccountWithLock();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new DepositTask(account));
        pool.execute(new WithdrawalTask(account));
        pool.shutdown();
        while (!pool.isTerminated()) {

        }
        System.out.println(account.getBalance());
    }
}
