package homework;

import java.util.Scanner;

public class P03CombinationsIteratively {
	private static int[] arr;
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			arr = new int[n];
			generateCombinations(n);
		}
	}

	private static void generateCombinations(int n) {
		boolean stropLoop = true;
		while (stropLoop) {
			stropLoop = iterate(n);
			print();
		}
	}

	private static boolean iterate(int n) {
		int index = arr.length - 1;
		while (index >= 0) {
			int temp = arr[index];
			arr[index] = ++temp;
			if (arr[index] == n) {
				arr[index] = 1;
				index--;
			} else {
				break;
			}
		}
		
		if (index >= 0) {
			return true;
		}
		
		return false;
	}
	
	private static void print() {
		for (int i : arr) {
			System.out.print(i);
		}
		
		System.out.println();
	}
}
