package implementHashTable;

public class PlayWithHashTable {

	public static void main(String[] args) {
		
		HashTable<String, Integer> ht = new HashTable<>(3);
		ht.add("Peter", 5);
		ht.add("Suzi", 6);
		ht.add("Renie", 4);
		ht.add("Mille", 5);
		ht.add("Wii", 6);
		
		for (KeyValue<String, Integer> element : ht) {
			System.out.print(element.getKey() + " -> " + element.getValue() + "; ");
		}
		
		System.out.println();
		
		System.out.println("Find key 'Suzi': " + ht.find("Suzi"));
		System.out.println("Find key 'Lilo': " + ht.find("Lilo"));
		System.out.println("Contains key 'Mille'? : " + ht.containsKey("Mille"));
		System.out.println("Get key 'Renie': " + ht.get("Renie"));
		ht.addOrReplace("Renie", 2);
		System.out.println("Get key 'Renie' (replaced): " + ht.get("Renie"));
		ht.remove("Suzi");
		System.out.println("Contains key 'Suzi'? (removed): " + ht.containsKey("Suzi"));
		
		System.out.println("Keys:");
		for (String key : ht.getKeys()) {
			System.out.print(key + " "); 
		}
		
		System.out.println();
		System.out.println("Values:");
		for (Integer value : ht.getValues()) {
			System.out.print(value + " "); 
		}
	}

}
