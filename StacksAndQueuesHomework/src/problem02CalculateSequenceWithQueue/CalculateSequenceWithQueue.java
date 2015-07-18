package problem02CalculateSequenceWithQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CalculateSequenceWithQueue {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number: ");
		int input = scanner.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(input);
		int current;
		for (int i = 1; i <= 50; i++) {
			current = queue.poll();
			System.out.print(current + " ");
			queue.add(current + 1);
			queue.add(current + current + 1);
			queue.add(current + 2);
		}
		
		scanner.close();

	}

}
