package implementLinkedStack;

import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class LinkedStack<T> {

	private Node<T> firstNode;
	private int size;
	
	public LinkedStack() {
		firstNode = null;
		size = 0;
	}
	
	/**
	 * Returns the actual size of the stack.
	 * @return the stack's size.
	 */
    public int count() {
		return size;
	}
    
    /**
     * Adds an element on top of the stack.
     * @param element - element to be added.
     */
	public void push(T element) { 
    	Node<T> newNode = new Node<T>(element);
    	if (size == 0) {
			firstNode = newNode;
		} else {
			newNode.nextNode = firstNode;
			firstNode = newNode;
		}
    	
    	size++;
    }
    
	/**
	 * Removes the first element from the stack.
	 * @return the removed element.
	 */
    public T pop() {
    	if (size == 0) {
			throw new EmptyStackException();
		}
    	
    	T popped = firstNode.value;
    	firstNode = firstNode.nextNode;
    	size--;
    	
		return popped;
    	
    }
    
    /**
     * Returns array representation of the stack.
     * @return array of the stack's elements.
     */
    @SuppressWarnings("hiding")
	public <T> T[] toArray() {
		T[] result = (T[]) new Object[size];
		Node<T> current = (Node<T>) firstNode;
		for (int i = 0; i < size; i++) {
			result[i] = current.value;
			current = current.nextNode;
		}
    	
		return result;
    }
    
    /**
     * Inner class for the stack's node
     * @param <T>
     */
    @SuppressWarnings("hiding")
	private class Node<T>
    {
        private T value;
        public Node<T> nextNode;
        public Node(T value) {
        	this.value = value;
        	nextNode = null;
        }
    }
}
