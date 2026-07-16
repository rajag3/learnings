package my.java.basics.synchronized_method.stock;

public class StockChecker {
    private int stock = 20;
    public int getStock() {
        return stock;
    }

    public void updateStorck(int amount){
        stock -= amount;
    }
}
