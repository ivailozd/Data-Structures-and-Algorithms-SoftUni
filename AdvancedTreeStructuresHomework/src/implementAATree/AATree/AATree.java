package implementAATree.AATree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class AATree<T extends Comparable<T>> implements Iterable<T>{
	private AANode<T> root;
	
	public AATree() {
		root = null;
	}
	
	
	/**
	 * Method checking if tree is empty
	 * @return true if there is no elements in the tree
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Inserts an element to the tree
	 * @param element
	 */
	public void insert(T element) {
		root = insert(element, root);
	}
	
	/**
	 * Removes an element from the tree
	 * @param element
	 */
	public void remove(T element) {
		root = remove(element, root);
	}

	private AANode<T> remove(T element, AANode<T> node) {
		if (node == null) {
			return node;
		} else if (element.compareTo(node.getValue()) > 0) {
			node.setRight(remove(element, node.getRight()));
		} else if (element.compareTo(node.getValue()) < 0) {
			node.setLeft(remove(element, node.getLeft()));
		} else {
			if (node.getLevel() == 1) {
				return null;
			} else if (node.getLeft() == null) {
				AANode<T> left = getSuccessor(node);
				node.setRight(remove(left.getValue(), node.getRight()));
				node.setValue(left.getValue());
			} else {
				AANode<T> left = getPredecessor(node);
				node.setLeft(remove(left.getValue(), node.getLeft()));
				node.setValue(left.getValue());
			}
		}
		
		node = decreaseLevel(node);
		node = skew(node);
		node.setRight(skew(node.getRight()));
		if (node.getRight() != null) {
			node.getRight().setRight(skew(node.getRight().getRight()));
		}
		
		node = split(node);
		node.setRight(split(node).getRight());
		
		return node;
	}

	private AANode<T> decreaseLevel(AANode<T> node) {
		int shouldBe = 0;
		if (node.getLeft() != null && node.getRight() != null) {
			shouldBe = Math.min(node.getLeft().getLevel(), node.getRight().getLevel()) + 1;
		} else if (node.getLeft() != null) {
			shouldBe = node.getLeft().getLevel() + 1;
		} else  if (node.getRight() != null){
			shouldBe = node.getRight().getLevel() + 1;
		} else {
			return node;
		}
		
		if (shouldBe < node.getLevel()) {
			node.setLevel(shouldBe);
			if (shouldBe < node.getRight().getLevel()) {
				node.getRight().setLevel(shouldBe);
			}
		}
		
		return node;
	}

	private AANode<T> getPredecessor(AANode<T> node) {
		AANode<T> predecessor = node.getLeft();
		while (predecessor.getRight() != null) {
			predecessor = predecessor.getRight();
		}
		
		return predecessor;
	}

	private AANode<T> getSuccessor(AANode<T> node) {
		AANode<T> successor = node.getRight();
		while (successor.getLeft() != null) {
			successor = successor.getLeft();
		}
		
		return successor;
	}

	private AANode<T> insert(T element, AANode<T> node) {
		if (node == null) {
			node = new AANode<>(element);
		} else if (element.compareTo(node.getValue()) < 0) {
			node.setLeft(insert(element, node.getLeft()));
		} else if (element.compareTo(node.getValue()) > 0) {
			node.setRight(insert(element, node.getRight()));
		} else {
			return node;
		}
		
		node = skew(node);
		node = split(node);
			
		return node;
	}

	private AANode<T> split(AANode<T> node) {
		if (node == null) {
			return null;
		} else if (node.getLeft() == null) {
			return node;
		} else if (node.getLeft().getLevel() == node.getLevel()) {
			AANode<T> left = node.getLeft();
			node.setLeft(left.getRight());
			left.setRight(node);
			return left;
		} else {
			return node;
		}
	}

	private AANode<T> skew(AANode<T> node) {
		if (node == null) {
			return null;
		} else if (node.getRight() == null || node.getRight().getRight() == null) {
			return node;
		} else if (node.getLevel() == node.getRight().getRight().getLevel()) {
			AANode<T> right = node.getRight();
			node.setRight(right.getLeft());
			right.setLeft(node);
			right.setLevel(right.getLevel() + 1);
			return right;
		} else {
			return node;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new AATreeIterator();
	}
	
	private class AATreeIterator implements Iterator<T> {
		private Stack<AANode<T>> stack = new Stack<>();
		
		public AATreeIterator() {
			pushLeftChildren(root);
		}
		
		private void pushLeftChildren(AANode<T> current) {
			while (current != null) {
				stack.push(current);
				current = current.getLeft();
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public T next() {
			if (! hasNext()) {
				throw new NoSuchElementException();
			}
			
			AANode<T> current = stack.pop();
			pushLeftChildren(current.getRight());
			
			return current.getValue();
		}
	}
}
