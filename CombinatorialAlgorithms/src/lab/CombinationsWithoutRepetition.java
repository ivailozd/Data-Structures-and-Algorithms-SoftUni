package lab;

import java.util.Scanner;

public class CombinationsWithoutRepetition {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			System.out.print("k = ");
			int k = scanner.nextInt();
			int[] arr = new int[k];
			generateCombinations(arr, n, 0, 1);
		}
 	}
	
	private static void generateCombinations(int[] arr, int sizeOfSet, int index, int start) {
		if (index >= arr.length) {
			print(arr);
		} else {
			for (int i = start; i <= sizeOfSet; i++) {
				arr[index] = i;
				generateCombinations(arr, sizeOfSet, index + 1, i + 1);
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
