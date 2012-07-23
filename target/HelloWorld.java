package target;

import java.util.Random;

public class HelloWorld implements Runnable {
	static Random rand = new Random ();
	void func1() {
		System.out.print(Thread.currentThread().getId() + " ");
		System.out.println("In func1");
		
		try {
			Thread.sleep(rand.nextInt(500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		func2();
	}
	void func2() {
		System.out.println("In func2");
		try {
			Thread.sleep(rand.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread (new HelloWorld()).start();
		}
		try {
                        Thread.sleep(rand.nextInt(2000));
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

	}
	
	@Override
	public void run() {
		func1();		
	}
}
