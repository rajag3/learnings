import java.lang.Thread;

class MyThread extends Thread {
	public void run(){
		System.out.println("Thread started running...");
	}
	
}

public class Main {
	public static void main(String[] args) {
		System.out.println("In main class..");
		MyThread t1 = new MyThread();
		t1.start();
		System.out.println("Thread name:"+Thread.currentThread().getName());
		System.out.println("Active thread count:"+Thread.activeCount());
		System.out.println("After thread start..");
	}
}	
