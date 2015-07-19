package sequenceFromNtoM;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FindSeuence {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number [start end]: ");
		int start = scanner.nextInt();
		int end = scanner.nextInt();
		scanner.close();
		
		Queue<Item> items = new LinkedList<>();
		items.add(new Item(start, null));
		
		Item current;
		while (! items.isEmpty()) {
			current = items.poll();
			if ((current.value < end && end >= 0 ) || (current.value < 0 && end < 0)) {
				items.add(new Item(current.value + 1, current));
				items.add(new Item(current.value + 2, current));
				items.add(new Item(current.value * 2, current));
			}
			
			if (current.value == end) {
				printSolution(current);
				break;
			}
			
			if (items.isEmpty()) {
				System.out.println("No solution found.");
			}
		}
	}

	private static void printSolution(Item item) {
		Stack<Item> stack = new Stack<>();
		while (item != null) {
			stack.push(item);
			item = item.prev;
		}
		
		while (! stack.isEmpty()) {
			System.out.print(stack.pop().value + " ");
		}
	}
	
}
