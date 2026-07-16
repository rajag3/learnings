package my.java.basics.synchronized_method.bank;

public class BankAccount {
    private int balance = 100;

    void debit(int amount){
        balance -= amount;
    }

    int getBalance(){
        return balance;
    }

}
