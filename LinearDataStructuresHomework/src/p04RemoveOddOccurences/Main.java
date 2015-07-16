package p04RemoveOddOccurences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			
			List<Integer> result = removeOddOccurences(numbers);
			
			System.out.println("Longest subsequence equal numbers:");
			result.forEach(n -> System.out.print(n + " "));
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid input: " + e);
		}
	}

	private static List<Integer> removeOddOccurences(List<Integer> numbers) {
		
		Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
		for (Integer number : numbers)
        {
            if (occurences.containsKey(number))
            {
                occurences.put(number , occurences.get(number) + 1);
            }
            else
            {
                occurences.put(number, 1);
            }

        }
		
		List<Integer> result = new ArrayList<Integer>(numbers);
		for (Map.Entry<Integer, Integer> entry : occurences.entrySet())
		{
		    if (entry.getValue() % 2 != 0) {
				for (int i = 0; i < entry.getValue(); i++) {
					result.remove(entry.getKey());
				}
			}
		}
		
        return result;
	}
}
