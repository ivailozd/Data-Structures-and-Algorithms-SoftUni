import implementHashTable.HashTable;

import java.util.Scanner;

public class CountSymbols {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		
		char[] charArr = input.toCharArray();
		HashTable<Character, Integer> charTable = new HashTable<>();
		for (Character ch : charArr) {
			if (charTable.containsKey(ch)) {
				int value = charTable.get(ch);
				charTable.set(ch, value + 1);
			} else {
				charTable.add(ch, 1);
			}
		}
		
		charTable.toList().stream()
			.sorted((e, o) -> e.getKey().compareTo(o.getKey()))
			.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
	}
}
