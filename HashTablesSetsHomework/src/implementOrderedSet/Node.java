package implementOrderedSet;

public class Node<T extends Comparable<T>> {
	private Node<T> leftChild = null;
	private Node<T> rightChild = null;
	private T value;
	
	public Node(T value) {
		this.value = value;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> leftNode) {
		this.leftChild = leftNode;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightNode) {
		this.rightChild = rightNode;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
