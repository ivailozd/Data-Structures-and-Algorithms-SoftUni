package p03ImplementBinaryHeap;

import java.util.Random;

public class PlayWithPriorityQueue {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		Random random = new Random();
		int newInteger = 0;
		for (int i = 0; i < 10; i++) {
			newInteger = random.nextInt(500);
			queue.add(newInteger);
		}
		
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue);
	}

}
