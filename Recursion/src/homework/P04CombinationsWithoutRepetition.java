package homework;

import java.util.Scanner;
import java.util.stream.IntStream;

public class P04CombinationsWithoutRepetition {
	private static int n;
	private static int k;
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			System.out.print("n = ");
			n = scanner.nextInt();
			System.out.print("k = ");
			k = scanner.nextInt();
			
			int[] vector = new int[k];
			int[] elements = IntStream.range(1, n + 1).toArray();
			printCombinations(0, vector, 0, elements);
		}

	}

	private static void printCombinations(int vectorIndex, int[] vector, 
			int elementIndex, int[] elements) {
		if (vectorIndex == k) {
			print(vector, elements);
		} else {
			for (int i = elementIndex; i < n; i++) {
				vector[vectorIndex] = i;
				printCombinations(vectorIndex + 1, vector, i + 1, elements);
			}
		}
		
	}

	private static void print(int[] vector, int[] elements) {
		for (int i = 0; i < vector.length; i++) {
			System.out.print(elements[vector[i]]);
		}
		
		System.out.println();
	}

}
