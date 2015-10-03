package lab;

import java.util.Scanner;

public class VariationsWithRepetition {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			System.out.print("k = ");
			int k = scanner.nextInt();
			int[] arr = new int[k];
			generateVariations(arr, n, 0);
		}
 	}
	
	private static void generateVariations(int[] arr, int sizeOfSet, int index) {
		if (index >= arr.length) {
			print(arr);
		} else {
			for (int i = 1; i <= sizeOfSet; i++) {
				arr[index] = i;
				generateVariations(arr, sizeOfSet, index + 1);
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
