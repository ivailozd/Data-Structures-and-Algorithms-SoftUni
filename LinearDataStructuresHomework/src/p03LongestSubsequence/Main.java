package p03LongestSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter numbers:");
	    String input = "";
		try {
			input = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Integer> numbers = new ArrayList<Integer>();
		try {
			numbers = Arrays.stream(input.split("\\s+"))
			  .map(s->Integer.valueOf(s))
			  .collect(Collectors.toList());
			
			List<Integer> result = longestSubsequenceEqualNumbers(numbers);
			
			System.out.println("Longest subsequence equal numbers:");
			result.forEach(n -> System.out.print(n + " "));
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + e);
		}
	}

	private static List<Integer> longestSubsequenceEqualNumbers(List<Integer> numbers) {
		int count = 1;
		int max = 1;
		int maxIndexStart = 0;

		for (int i = 1; i < numbers.size(); i++) {
		    if (numbers.get(i) == numbers.get(i - 1)) {
		        count++;
		    } else {
		        count = 1;
		    }
		    
		    if (count > max) {
	            max = count;
	            maxIndexStart = i - max + 1;
	        }
		}
		
		List<Integer> result = new ArrayList<>();
		for (int i = maxIndexStart; i < max + maxIndexStart; i++) {
			result.add(numbers.get(i));
		}
		
		return result;
	}
}
