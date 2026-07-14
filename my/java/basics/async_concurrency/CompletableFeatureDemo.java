package my.java.basics.async_concurrency;

//Asynchronous and Concurrent Programming
import java.util.concurrent.CompletableFuture;

/*
    Coffee shop demo
*/
public class CompletableFeatureDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("[1] You have ordered Caramel Coffee");
        
        // Start an asynchronous task (Making coffee in the background)
        CompletableFuture coffeeOrder = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[Barsita] grinding bean and steaming milk");
                Thread.sleep(3000); // Simulates 3 seconds of coffee makin
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return "Caremel Macchiato"; // The promised result
        });

        // Define what to do automatically WHEN the coffee is ready
        coffeeOrder.thenAccept(coffee ->{
            System.out.println("[3] Buzzer rings! you pick you hot "+ coffee);
        });

        System.out.println("[2] While waiting you scroll through social media..");
        Thread.sleep(4000); // Keep the program alive just to see the background tag finish.
    }
}