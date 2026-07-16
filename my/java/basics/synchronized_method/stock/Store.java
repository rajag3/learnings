package my.java.basics.synchronized_method.stock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Store {
   
    //static void purchase(StockChecker stockChecker, int amount){
    static synchronized void purchase(StockChecker stockChecker, int amount){
        
        if(stockChecker.getStock() - amount < 0 ){
            System.out.println("Out of stock");
        } else {
            System.out.println("Item is in stock");
            stockChecker.updateStorck(amount);
            System.out.println(amount+" stock purchased");
        }
    }
    public static void main(String[] args) {
        StockChecker stockChecker = new StockChecker();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(() -> purchase(stockChecker, 10));
        executorService.submit(() -> purchase(stockChecker, 10));
        executorService.submit(() -> purchase(stockChecker, 10));
        executorService.submit(() -> purchase(stockChecker, 10));

        executorService.shutdown();
    }
}
