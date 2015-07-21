package implementATree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Tree<T> {
	
	private T value;
	private List<Tree<T>> children;
	
	/**
	 * Constructs empty tree.
	 */
	public Tree() {
		setChildren(new LinkedList<Tree<T>>());
	}
	
	/**
	 * Constructs tree with given value and with optional given children.
	 * @param value - tree's value.
	 * @param children - tree's children.
	 */
	@SafeVarargs
	public Tree(T value, Tree<T>... children) {
		this();
		setValue(value);
		
		for (Tree<T> tree : children) {
			getChildren().add(tree);
		}
	}
	
	/**
	 * Prints each tree node on new line with offset
	 * according the depth.
	 * @param depth - tree's depth.
	 */
	public void print(int depth) {
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
	 * Getters and setters for tree's value and children.
	 */
	public T getValue() {
		return value;
	}

	private void setValue(T value) {
		this.value = value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	private void setChildren(List<Tree<T>> children) {
		this.children = children;
	}
}
