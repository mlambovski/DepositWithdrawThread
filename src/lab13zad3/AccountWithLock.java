package lab13zad3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithLock extends Account {

    private final Lock lock = new ReentrantLock();
    private final Condition canWithdraw = lock.newCondition();
    private final Condition canDeposit = lock.newCondition();
    private boolean occupied = false;

    public void withdraw(int amount) {
        lock.lock();
        try {
            while (occupied) {
                canWithdraw.await();
            }
            occupied = true;
            setBalance(getBalance() - amount < 0 ? 0 : getBalance() - amount);
            System.out.println("Current balance after withdrawal: " + getBalance());
            canDeposit.signal();
        } catch (InterruptedException ie) {
            System.err.println(ie);
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            while (!occupied) {
                canDeposit.await();
            }
            occupied = false;
            setBalance(getBalance() + amount);
            System.out.println("Current balance after deposit: " + getBalance());
            canWithdraw.signal();
        } catch (InterruptedException ie) {
            System.err.println(ie);
        } finally {
            lock.unlock();
        }
    }
}
