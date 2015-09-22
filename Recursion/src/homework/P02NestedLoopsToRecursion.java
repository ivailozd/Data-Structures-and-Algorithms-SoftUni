package homework;

import java.util.Scanner;

public class P02NestedLoopsToRecursion {
	private static int n;

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.print("n = ");
			n = scanner.nextInt();
			int[] vector = new int[n];
			printCombinations(n - 1, vector);
		}

	}

	private static void printCombinations(int index, int[] arr) {
		if (index < 0) {
			print(arr);
		} else {
			for (int i = 1; i <= n; i++) {
				arr[index] = i;
				printCombinations(index - 1, arr);
			}
		}
	}

	private static void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i);
		}
		
		System.out.println();
	}

}
