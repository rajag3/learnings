package my.java.basics.synchronized_method.bank;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Customer {
    public static void main(String[] args) {
        ATM atm = new ATM();
        BankAccount bankAccount = new BankAccount();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> atm.withdraw(bankAccount, 100));
        executorService.submit(() -> atm.withdraw(bankAccount, 100));

        executorService.shutdown();
    }
}
