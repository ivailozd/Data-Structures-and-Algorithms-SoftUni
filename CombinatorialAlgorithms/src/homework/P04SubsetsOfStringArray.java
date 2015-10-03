package homework;

import java.util.Scanner;

public class P04SubsetsOfStringArray {
	private static String[] loops;
	private static String[] strings;
	private static int totalPermutations;
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("s = ");
			strings = scanner.nextLine().split(",\\s+");
			System.out.print("k = ");
			int k = scanner.nextInt();
			loops = new String[k];
			generateSubsets(0, 0, strings.length, k);
			System.out.println("Total permutations: " + totalPermutations);
		}
	}

	public static void generateSubsets(int index, int start, int n, int k) {
		if (index == loops.length) {
			print();
			totalPermutations++;
			return;
		}
		
		for (int i = start; i <= n; i++) {
			if (i == n) {
				break;
			}
			
			loops[index] = strings[i];
			generateSubsets(index + 1, i + 1, n, k - 1);
		}
	}

	public static void print() {
		for (String string : loops) {
			System.out.print(string + " ");
		}
		
		System.out.println();
	}

}
