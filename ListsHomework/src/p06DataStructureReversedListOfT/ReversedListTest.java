package p06DataStructureReversedListOfT;

public class ReversedListTest {

	public static void main(String[] args) {
		
		ReversedList<String> stringList = new ReversedList<>();
		stringList.add("test");
		stringList.add("ReversedList");
		stringList.add("is");
		stringList.add("This");
		
		System.out.println("List count: " + stringList.count());
		System.out.println("List capacity: " + stringList.capacity());
		
		System.out.println("Element at index 2: \n" + stringList.get(2));
		stringList.remove(2);
		System.out.println("Element at index 2 removed:");
		for (String string : stringList) {
			System.out.print(string + " ");
		}
		
		System.out.println();
		
	}

}
