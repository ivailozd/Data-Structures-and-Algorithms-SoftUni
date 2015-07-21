package implementATree;

import java.util.LinkedList;
import java.util.List;

public class PlayWithTree {

	public static void main(String[] args) {
		
		Tree<Integer> tree =
				new Tree<Integer>(7,
					new Tree<Integer>(19,
						new Tree<Integer>(1),
						new Tree<Integer>(12),
						new Tree<Integer>(31)),
					new Tree<Integer>(21),
					new Tree<Integer>(14,
						new Tree<Integer>(23),
						new Tree<Integer>(6)));
		
		tree.print(0);
		
		List<Integer> nodes = new LinkedList<Integer>();
		tree.each(i -> nodes.add(i));
		for (Integer integer : nodes) {
			System.out.print(integer + " ");
		}
	}

}
