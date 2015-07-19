package implementArrayBasedStack;

import java.util.Arrays;
import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class ArrayBasedStack<T> {
	
	private static final int INITIAL_CAPACITY = 16;
	private T[] elements;
	private int size;
	
	/**
	 * Constructor for stack with default capacity.
	 */
	public ArrayBasedStack() {
		elements = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Constructor for stack with custom capacity.
	 * @param capacity - the stack's capacity.
	 */
	
	public ArrayBasedStack(int capacity) {
		elements = (T[]) new Object[capacity];
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
	 * Pushes element to the stack.
	 * @param element - the element to be pushed.
	 */
	public void push(T element) {
		if (elements.length == size) {
			grow();
		}
		
		elements[size++] = element;
	}
	
	/**
	 * Pops an element from the stack.
	 * @return the popped element.
	 */
	public T pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}
	
	/**
	 * Returns on array of stack's elements. 
	 * @return array of stack's elements.
	 */
	@SuppressWarnings({ "hiding" })
	public <T> T[] toArray() {
		T[] result = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			result[i] = (T) elements[size - i - 1];
		}
		
		return result;
	}
	
	/**
	 * Ensures internal array capacity.
	 */
	private void grow() {
		int newSize = elements.length * 2;
		elements = Arrays.copyOf(elements, newSize);
	}
}
