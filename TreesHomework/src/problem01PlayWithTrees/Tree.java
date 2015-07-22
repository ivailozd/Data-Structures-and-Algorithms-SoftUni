package problem01PlayWithTrees;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Tree<T> {
	private T value;
	private Tree<T> parent;
	private List<Tree<T>> children;
	
	/**
	 * Constructs an empty tree.
	 */
	public Tree() {
		setChildren(new LinkedList<Tree<T>>());
	}
	
	/**
	 * Constructs a tree with given value and with optional given children.
	 * @param value - the tree's value.
	 * @param children -the tree's children.
	 */
	@SafeVarargs
	public Tree(T value, Tree<T>... children) {
		this();
		setValue(value);
		
		for (Tree<T> tree : children) {
			getChildren().add(tree);
			tree.setParent(this);
		}
	}
	
	/**
	 * Simply call private(int depth = 0).
	 */
	public void print() {
		print(0);
	}
	
	/**
	 * Prints each tree node on new line with offset
	 * according the depth.
	 * @param depth - tree's depth.
	 */
	private void print(int depth) {
		String offset = "  ";
		for (int i = 0; i < depth; i++) {
			offset += "  ";
		}
		
		System.out.print(offset);
		System.out.println(getValue());
		
		for (Tree<T> tree : getChildren()) {
			tree.print(depth + 1);
		}
	}
	
	/**
	 * Performs action to the tree and its children recursively.
	 * @param action - action to be performed.
	 */
	public void each(Consumer<T> action) {
		action.accept(value);
		
		for (Tree<T> tree : getChildren()) {
			tree.each(action);
		}
	}
	
	/**
	 * Getters and setters for tree's value, children and parent.
	 */
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	private void setChildren(List<Tree<T>> children) {
		this.children = children;
	}

	public Tree<T> getParent() {
		return parent;
	}

	public void setParent(Tree<T> parrent) {
		this.parent = parrent;
	}
}
