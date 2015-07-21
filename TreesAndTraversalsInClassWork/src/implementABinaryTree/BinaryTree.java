package implementABinaryTree;

import java.util.function.Consumer;

public class BinaryTree<T> {

	private T value;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;
	
	/**
	 * Constructs tree without children.
	 * @param value - tree's value.
	 */
	public BinaryTree(T value) {
		this.value = value;
	}
	
	/**
	 * Constructs tree with children.
	 * @param value - tree's value.
	 * @param leftChild - tree's left child
	 * @param rightChild - tree's right child.
	 */
	public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
		this(value);
		setLeftChild(leftChild);
		setRightChild(rightChild);
	}
	
	/**
	 * Simply calls printIndentedPreOrder(int depth = 0).
	 */
	public void printIndentedPreOrder() {
		printIndentedPreOrder(0);
	}
	
	/**
	 * Prints each tree node on new line with offset
	 * according the depth.
	 * @param depth - tree's depth.
	 */
	private void printIndentedPreOrder(int depth) {
		//Pre-order = root node, left child, right child
		String offset = "  ";
		for (int i = 0; i < depth; i++) {
			offset += "  ";
		}
		
		System.out.print(offset);
		System.out.println(getValue());
		
		if (getLeftChild() != null) {
			getLeftChild().printIndentedPreOrder(depth + 1);
		}
		
		if (getRightChild() != null) {
			getRightChild().printIndentedPreOrder(depth + 1);
		}
		
	}
	
	/**
	 * Performs an action to the tree and its children recursively in-order.
	 * @param action - action to be performed.
	 */
	public void eachInOrder(Consumer<T> action) {
		//In-order = left child, root node, right child
		if (getLeftChild() != null) {
			getLeftChild().eachInOrder(action);
		}
		
		action.accept(value);
		
		if (getRightChild() != null) {
			getRightChild().eachInOrder(action);
		}
	}
	
	/**
	 * Performs an action to the tree and its children recursively post-order.
	 * @param action - action to be performed.
	 */
	public void eachPostOrder(Consumer<T> action) {
		//Post-order = left child, right child, root node
		if (getLeftChild() != null) {
			getLeftChild().eachPostOrder(action);
		}
		
		if (getRightChild() != null) {
			getRightChild().eachPostOrder(action);
		}
		
		action.accept(value);
	}

	/*
	 *Getters and setters for tree's value and children. 
	 */
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTree<T> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTree<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTree<T> rightChild) {
		this.rightChild = rightChild;
	}
}
