package implementAATree;

import implementAATree.AATree.AATree;

public class PlayWithAATree {

	public static void main(String[] args) {
		AATree<Integer> tree = new AATree<>();
		tree.insert(0);
		tree.insert(11);
		tree.insert(5);
		tree.insert(44);
		tree.insert(31);
		tree.insert(12);
		tree.insert(18);
		tree.insert(17);
		tree.insert(28);
		tree.insert(49);
		
		for (Integer integer : tree) {
			System.out.print(integer + " ");
		}
		
		System.out.println();
		tree.remove(17);
		for (Integer integer : tree) {
			System.out.print(integer + " ");
		}
	}

}
