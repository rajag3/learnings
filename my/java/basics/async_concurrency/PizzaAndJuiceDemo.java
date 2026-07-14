package my.java.basics.async_concurrency;

import java.util.logging.Logger;
import java.util.concurrent.*;

public class PizzaAndJuiceDemo {

	private static final Logger logger = Logger.getLogger(PizzaAndJuiceDemo.class.getName());
	public static void main(String[] args) throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		// process 1
		CompletableFuture<String> pizzaFuture = CompletableFuture.supplyAsync(()->{
			logger.info("Ordering pizza...");
			sleep(3000);
			return "Pizza is ready...";
		}, executor);

		// process 2
		CompletableFuture<String> juiceFuture = CompletableFuture.supplyAsync(()->{
			logger.info(" Making orance juice...");
			sleep(2000);
			return "juice is ready...";
		}, executor);

		logger.info("Cleaning the table while waiting...");

		String pizza = pizzaFuture.get();
		String juice = juiceFuture.get();

		logger.info(pizza);
		logger.info(juice);
		logger.info("Let's eat and drink...");
		executor.shutdown();

	}

	private static void sleep(long ms) {
		try{ Thread.sleep(ms);}	 catch (InterruptedException e) { e.printStackTrace();}
	}
}
