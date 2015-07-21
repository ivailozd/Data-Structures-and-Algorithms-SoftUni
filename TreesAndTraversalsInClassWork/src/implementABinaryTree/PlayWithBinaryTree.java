package implementABinaryTree;

import java.util.LinkedList;
import java.util.List;

public class PlayWithBinaryTree {

	public static void main(String[] args) {
		
		BinaryTree<String> binaryTree =
				new BinaryTree<String>("*",
					new BinaryTree<String>("+",
						new BinaryTree<String>("3"),
						new BinaryTree<String>("2")),
					new BinaryTree<String>("-",
						new BinaryTree<String>("9"),
						new BinaryTree<String>("6")));
		
		binaryTree.printIndentedPreOrder();
		
		List<String> nodes = new LinkedList<>();
		
		binaryTree.eachInOrder(s -> nodes.add(s));
		nodes.forEach(s -> System.out.print(s + " "));
		
		nodes.clear();
		System.out.println();
		
		binaryTree.eachPostOrder(s -> nodes.add(s));
		nodes.forEach(s -> System.out.print(s + " "));
	}

}
