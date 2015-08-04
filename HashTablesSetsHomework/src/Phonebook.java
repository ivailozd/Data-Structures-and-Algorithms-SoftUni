import implementHashTable.HashTable;
import implementHashTable.KeyValue;

import java.util.Scanner;


public class Phonebook {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		HashTable<String, String> phonebook = new HashTable<>();
		
		System.out.println("Name and phone separated by a space:");
		String input = scanner.nextLine();
		String[] entry;
		while (!input.equals("search")) {
			entry = input.split("\\s+");
			try {
				phonebook.add(entry[0], entry[1]);
			} catch (IllegalArgumentException e) {
				System.out.println("Name already exists!");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Enter a name and a phone separated by a space!");
			}
			
			input = scanner.nextLine();
		}
		
		System.out.println("Search for name:");
		input = scanner.nextLine();
		while (! input.equals("END")) {
			KeyValue<String, String> contact = phonebook.find(input);
			if (contact == null) {
				System.out.println("Contact " + input + " does not exist.");
			} else {
				System.out.println(contact.getKey() + " -> " + contact.getValue());
			}
			
			input = scanner.nextLine();
		}
		
		scanner.close();
	}

}
