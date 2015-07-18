package p02SortWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter words:");
	    String input = "";
		try {
			input = bufferRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> words = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
		words.sort((a, b) -> a.compareTo(b));
		
		System.out.println("Sorted words:");
		System.out.println(String.join(", ", words));
	}
}
