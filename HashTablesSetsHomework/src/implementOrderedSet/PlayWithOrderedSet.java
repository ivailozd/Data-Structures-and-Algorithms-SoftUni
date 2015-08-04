package implementOrderedSet;

public class PlayWithOrderedSet {

	public static void main(String[] args) {
		OrderedSet<Integer> set = new OrderedSet<>();
		set.add(17);
		System.out.println("Added 9?: " + set.add(9));
		System.out.println("Added second 9?: " + set.add(9));
		set.add(12);
		set.add(19);
		set.add(6);
		set.add(25);
		
		System.out.println("Contains 9?: " + set.contains(9));
		System.out.println("Set's count: " + set.count());
		System.out.println("Removed 9?: " + set.remove(9));
		System.out.println("Contains 9?: " + set.contains(9));
		System.out.println("Set's count: " + set.count());
		
		for (Integer integer : set) {
			System.out.print(integer + " ");
		}
	}

}
