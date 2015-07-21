package implementABinaryTree;

import java.util.function.Consumer;

public class BinaryTree<T> {

	private T value;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;
	
	public BinaryTree(T value) {
		this.value = value;
	}
	
	public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
		this(value);
		setLeftChild(leftChild);
		setRightChild(rightChild);
	}
	
	public void printIndentedPreOrder() {
		printIndentedPreOrder(0);
	}
	
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
	
	public void eachPostOrder(Consumer<T> action) {
		//In-order = left child, right child, root node
		if (getLeftChild() != null) {
			getLeftChild().eachPostOrder(action);
		}
		
		if (getRightChild() != null) {
			getRightChild().eachPostOrder(action);
		}
		
		action.accept(value);
	}

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
