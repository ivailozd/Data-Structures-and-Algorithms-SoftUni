package homework;

import java.util.Scanner;
import java.util.stream.IntStream;

public class P02PermutationsIteratively {
	private static int countOfPermutations = 0;
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("n = ");
			int n = scanner.nextInt();
			int[] a = IntStream.range(1, n + 1).toArray();
			int[] p = IntStream.range(0, n + 1).toArray();
			permute(p, a);
			
			System.out.println("Total permutations: " + countOfPermutations);
		}
	}
	
	private static void permute(int[] p, int[] a) {
		int i = 1;
		int j = 0;
		print(a);
		countOfPermutations++;
		while (i < a.length) {
			p[i]--;
			if (i % 2 != 0) {
				j = p[i];
			} else {
				j = 0;
			}
			
			swap(a, j, i);
			i = 1;
			while (p[i] == 0) {
				p[i] = i;
				i++;
			}
			
			print(a);
			countOfPermutations++;
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
