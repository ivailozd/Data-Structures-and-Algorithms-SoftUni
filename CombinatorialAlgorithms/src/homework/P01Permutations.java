package homework;

import java.util.Scanner;
import java.util.stream.IntStream;

public class P01Permutations {
	private static int countOfPermutations = 0;
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			int[] array = IntStream.range(1, n + 1).toArray();
			permute(array, 0);
			
			System.out.println("Total permutations: " + countOfPermutations);
		}
	}
	
	private static void permute(int[] array, int startIndex) {
		if (startIndex >= array.length) {
			print(array);
			countOfPermutations++;
		} else {
			permute(array, startIndex + 1);
			for (int i = startIndex + 1; i < array.length; i++) {
				swap(array, startIndex, i);
				permute(array, startIndex + 1);
				swap(array, startIndex, i);
			}
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		if (i == j) {
			return;
		}
		
		array[i] ^= array[j];
		array[j] ^= array[i];
		array[i] ^= array[j];
	}

	private static void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i);
		}
		
		System.out.println();
	}
}
