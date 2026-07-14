package my.java.basics.collections_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class RaceConditionDemo {
	public static void main(String args[]) throws InterruptedException{
		// Standard ArrayList (Not Thread-Safe)
		//List<Integer> list = new ArrayList<>();

		//To make your code finish cleanly with a size of exactly 2000, 
		// you can swap the initialization line to use a thread-safe wrapper.
		List<Integer> list = Collections.synchronizedList(new ArrayList<>());

		Thread t1 = new Thread(()-> addElements(list));
		Thread t2 = new Thread(()-> addElements(list));

		// Create two threads adding elements concurrently
		t1.start();
		t2.start();

		// Wait for both threads to finish
		t1.join();
		t2.join();

		System.out.println("Final list size: "+list.size());
	}

	private static void addElements(List<Integer> list){
		for(int i =0; i <= 1000; i++){
			list.add(i);
		}
	}

/*
	//you can replace that entire loops structure using Java Streams and a lambda expression.
	private static addElements(List<Integer> list){
		java.util.stream.IntStream.range(0..1000).forEach(i -> list.add(i));
	}

	//Alternative: Method Reference (Even Cleaner)
	private static addElements(List<Integer> list){
		java.util.stream.IntStream.range(0..1000).forEach(list::add);
	}
*/
}

