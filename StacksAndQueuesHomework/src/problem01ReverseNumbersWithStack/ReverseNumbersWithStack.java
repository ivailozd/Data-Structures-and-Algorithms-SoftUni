package problem01ReverseNumbersWithStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class ReverseNumbersWithStack {

	public static void main(String[] args) {
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter numbers:");
	    String input = "";
		try {
			input = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stack<String> stack = new Stack<>();
		Arrays.asList(input.split("\\s+")).forEach(s -> stack.push(s));
		
		while (! stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

}
