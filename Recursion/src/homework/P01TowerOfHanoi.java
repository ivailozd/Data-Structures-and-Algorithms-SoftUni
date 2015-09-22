package homework;

import java.util.Scanner;
import java.util.Stack;

public class P01TowerOfHanoi {
	private static int steps = 0;

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.println("Size of bottom disk:");
			int bottomDisk = scanner.nextInt();
			Stack<Integer> source = new Stack<>();
			Stack<Integer> destination = new Stack<>();
			Stack<Integer> spare = new Stack<>();
			for (int i = 1; i <= bottomDisk; i++) {
				source.push(i);
			}
			
			moveDisks(bottomDisk, source, destination, spare);
			System.out.println("Steps taken: " + steps);
		}
	}

	private static void moveDisks(int bottomDisk, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> spare) {
		steps++;
		if (bottomDisk == 1) {
			destination.push(source.pop());
		} else {
			moveDisks(bottomDisk - 1, source, spare, destination);
			destination.push(source.pop());
			moveDisks(bottomDisk - 1, spare, destination, source);
		}
	}

}
