package p03ImplementBinaryHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> {
	
	private List<T> nodes = new ArrayList<>();
	private int size = 0;
	
	/*
	 * The constructor adds null to first position which is never used
	 */
	public PriorityQueue() {
		nodes.add(0, null);
	}
	
	/**
	 * Adds an element at the appropriate position according its natural ordering
	 * @param element
	 */
	public void add(T element) {
		if (size == 0) {
			nodes.add(++size, element);
		} else {
			nodes.add(++size, element);
			T current = element;
			int currentIndex = size;
			int parentIndex = currentIndex / 2;
			T parent = nodes.get(parentIndex);
			while (currentIndex > 1 && current.compareTo(parent) > 0) {
				nodes.set(currentIndex, parent);
				nodes.set(parentIndex, current);
				currentIndex = parentIndex;
				parentIndex = currentIndex / 2;
				current = nodes.get(currentIndex);
				parent = nodes.get(parentIndex);
			}
		}
	}
	
	/**
	 * Removes and returns the element with higher priority according
	 * elements's natural ordering
	 * @return the element with higher priority
	 */
	public T poll() {
		T root = null;
		int currentIndex = 1;
		if (size == 0) {
			throw new NoSuchElementException();
		} else if (size == 1) {
			root = nodes.get(size);
			nodes.remove(size);
			size--;
		} else if (size == 2) {
			root = nodes.get(1);
			nodes.set(1, nodes.get(size));
			nodes.remove(size);
			size--;
		} else if (size == 3) {
			root = nodes.get(1);
			nodes.set(1, nodes.get(size));
			nodes.remove(size);
			size--;
			if (nodes.get(size).compareTo(nodes.get(1)) > 0) {
				T node = nodes.get(1);
				nodes.set(1, nodes.get(size));
				nodes.set(size, node);
			}
		} else {
			root = nodes.get(1);
			nodes.set(1, nodes.get(size));
			nodes.remove(size);
			size--;
			T current = nodes.get(currentIndex);
			T leftChild = nodes.get(currentIndex * 2);
			T rightChild = nodes.get(currentIndex * 2 + 1);
			T biggerChild =  biggerNode(leftChild, rightChild);
			while (current.compareTo(biggerChild) < 0) {
				currentIndex = nodes.indexOf(biggerChild);
				nodes.set(currentIndex, current);
				nodes.set(nodes.indexOf(current), biggerChild);
				current = nodes.get(currentIndex);
				if (currentIndex < size / 2) {
					leftChild = nodes.get(currentIndex * 2);
					rightChild = nodes.get(currentIndex * 2 + 1);
					biggerChild = biggerNode(leftChild, rightChild);
				}
			}
		}
		
		return root;
	}
	
	/*
	 * Helper method
	 */
	private T biggerNode(T first, T second) {
		if (first.compareTo(second) > 0) {
			return first;
		}
		
		return second;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (int i = 1; i < size + 1; i++) {
			sb.append(nodes.get(i));
			sb.append(' ');
		}
		
		return sb.toString().trim() + "}";
	}
}
