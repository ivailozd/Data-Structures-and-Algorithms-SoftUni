package p07LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<Integer> integersList = new LinkedList<>();
		integersList.add(0);
		integersList.add(1);
		integersList.add(2);
		integersList.add(22);
		integersList.add(33);
		integersList.add(2);
		integersList.add(null);
		System.out.println("List count: " + integersList.count());
		
		for (Integer integer : integersList) {
			System.out.print(integer + " ");
		}
		
		System.out.println();
		
		integersList.remove(0);
		for (Integer integer : integersList) {
			System.out.print(integer + " ");
		}
		
		System.out.println();
		
		System.out.println("First index of NULL: " + integersList.firstIndexOf(null));
		System.out.println("Last index of 2: " + integersList.lastIndexOf(2));
	}

}
