package lab;

import java.util.Scanner;

public class VariationsWithoutRepetition {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			System.out.print("k = ");
			int k = scanner.nextInt();
			int[] arr = new int[k];
			boolean[] used = new boolean[n + 1];
			generateVariations(arr, n, used, 0);
		}
	}

	private static void generateVariations(int[] arr, int sizeOfSet, boolean[] used, int index) {
		if (index >= arr.length) {
			print(arr);
		} else {
			for (int i = 1; i <= sizeOfSet; i++) {
				if (! used[i]) {
					used[i] = true;
					arr[index] = i;
					generateVariations(arr, sizeOfSet, used, index + 1);
					used[i] = false;
				}
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
