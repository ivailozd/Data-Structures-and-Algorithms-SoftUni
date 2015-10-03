package homework;

import java.util.Arrays;

public class P05PermutationsWithRepetition {

	public static void main(String[] args) {
//		int[] arr = { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		int[] arr = { 1, 3, 5, 5 };
		generatePermutations(arr);
	}
	
	public static void generatePermutations(int[] arr) {
		Arrays.sort(arr);
		permutate(arr, 0, arr.length);

	}

	public static void permutate(int[] arr, int start, int n) {
		print(arr);
		int temp = 0;
		if (start < n) {
			for (int i = n - 2; i >= start; i--) {
				for (int j = i + 1; j < n; j++) {
					if (arr[i] != arr[j]) {
						temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
						permutate(arr, i + 1, n);
					}
				}

				temp = arr[i];
				for (int k = i; k < n - 1;) {
					arr[k] = arr[++k];
				}
				
				arr[n - 1] = temp;
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
