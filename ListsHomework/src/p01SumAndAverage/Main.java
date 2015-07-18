package p01SumAndAverage;

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
		long sum = 0;
		try {
			numbers = Arrays.stream(input.split("\\s+"))
			  .map(s->Integer.valueOf(s))
			  .collect(Collectors.toList());
			
			sum = numbers.stream().mapToLong(value -> value).sum();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + e);
		}
		
	    float avg = 0;
	    if (numbers.size() > 0) {
	    	avg = (float)sum / numbers.size();
		}
	    
	    System.out.println("Sum = " + sum + "; Avg = " + avg);
	}
}
