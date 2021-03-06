package implementOrderedSet;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class OrderedSet<T extends Comparable<T>> implements Iterable<T>{
	private Node<T> root;
	private int count;
	
	public OrderedSet() {
		this.root = null;
		count = 0;
	}
	
	/**
	 * Returns actual number of elements
	 * @return elements' count
	 */
	public int count() {
		return count;
	}
	
	/**
	 * Adds element if not presents, else leaves table untouched.
	 * @param value - the element to be added
	 * @return true if added, false if already presents
	 */
	public boolean add(T value) {
		Node<T> newNode = new Node<T>(value);
		if (root == null) {
			root = newNode;
			count++;
		} else {
			Node<T> current = root;
			Node<T> parent;
			while (true) {
				parent = current;
				if (value.equals(current.getValue())) {
					return false;
				} else if (value.compareTo(current.getValue()) < 0) {
					current = current.getLeftChild();
					if (current == null) {
						parent.setLeftChild(newNode);
						count++;
						break;
					}
				} else {
					current = current.getRightChild();
					if (current == null) {
						parent.setRightChild(newNode);
						count++;
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if given element presents
	 * @param value - searched element
	 * @return true if presents, else if not
	 */
	public boolean contains(T value) {
		Node<T> current = root;
		while (! current.getValue().equals(value)) {
			if (value.compareTo(current.getValue()) < 0) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild();
			}
			
			if (current == null) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Removes given element if presents. If the element has children finds
	 * appropriate its successor to take its place
	 * @param value - element to be removed
	 * @return true - if removed, false - if does not present
	 */
	public boolean remove(T value) {
		Node<T> current = root;
		Node<T> parent = root;
		boolean isLeftChild = true;
		
		while (! current.getValue().equals(value)) {
			parent = current;
			if (! value.equals(current.getValue())) {
				isLeftChild = true;
				current = current.getLeftChild();
			} else {
				isLeftChild = false;
				current = current.getRightChild();
			}
			
			if (current == null) {
				return false;
			}
		}
		
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);
			}
		} else if (current.getRightChild() == null) {
			if (current == root) {
				root = current.getLeftChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getLeftChild());
			} else {
				parent.setRightChild(current.getLeftChild());
			}
		} else if (current.getLeftChild() == null) {
			if (current == root) {
				root = current.getRightChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getRightChild());
			} else {
				parent.setRightChild(current.getRightChild());
			}
		} else {
			Node<T> successor = findSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.setLeftChild(successor);
			} else {
				parent.setRightChild(successor);
			}
			
			successor.setLeftChild(current.getLeftChild());
		}
		
		count--;
		return true;
	}

	/**
	 * Helper method for finding appropriate successor for removing element
	 * @param delNode - removing element
	 * @return successor
	 */
	private Node<T> findSuccessor(Node<T> delNode) {
		
		Node<T> successorParent = delNode;
		Node<T> successor = delNode;
		Node<T> current = delNode.getRightChild();
		
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.getLeftChild();
		}
		
		if (successor != delNode.getRightChild()) {
			successorParent.setLeftChild(successor.getRightChild());
			successor.setRightChild(delNode.getRightChild());
		}
		
		return successor;
	}

	/**
	 * Returns custom iterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return new OrderedSetIterator();
	}
	
	/**
	 * Custom iterator implementation.
	 */
	private class OrderedSetIterator implements Iterator<T>{
		
		private Stack<Node<T>> stack = new Stack<>();
		
		public OrderedSetIterator() {
			pushLeftChildren(root);
		}

		private void pushLeftChildren(Node<T> current) {
			while (current != null) {
				stack.push(current);
				current = current.getLeftChild();
			}
			
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			Node<T> current = stack.pop();
			pushLeftChildren(current.getRightChild());
			
			return current.getValue();
		}
	}
}
