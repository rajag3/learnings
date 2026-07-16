package my.java.basics.synchronized_method.bank;

public class ATM {

    /*
        without synchronized key word that the current balance will goes negative.
        Handling Transaction
        Handling Transaction
        Rs.100 withdrawn
        Rs.100 withdrawn
        Current balance:-100
        Current balance:-100
    */
    public synchronized void withdraw(BankAccount account, int amount) {
        int balance = account.getBalance();
        if(balance - amount < 0 ){
            System.out.println("Widthdraw denied");
        } else {
            System.out.println("Handling Transaction");
            account.debit(amount);
            System.out.println("Rs."+amount+" withdrawn");
        }
        System.out.println("Current balance:"+account.getBalance());
    }
}
