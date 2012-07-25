package target;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConQueue implements Runnable {

	private ConcurrentLinkedQueue<Integer> q = new ConcurrentLinkedQueue<>();
	private Random rand = new Random();
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (rand.nextBoolean()) {
				q.add(rand.nextInt(1000));				
			} else {
				q.poll();
			}
		}		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ConQueue());
		Thread t2 = new Thread(new ConQueue());
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
