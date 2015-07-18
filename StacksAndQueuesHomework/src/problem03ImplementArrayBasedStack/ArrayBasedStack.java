package problem03ImplementArrayBasedStack;

import java.util.Arrays;
import java.util.EmptyStackException;

import javax.naming.spi.DirStateFactory.Result;

public class ArrayBasedStack<T> {
	
	private static final int INITIAL_CAPACITY = 16;
	private T[] elements;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayBasedStack() {
		elements = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayBasedStack(int capacity) {
		elements = (T[]) new Object[capacity];
		size = 0;
	}
	
	public int count() {
		return size;
	}
	
	public void push(T element) {
		if (elements.length == size) {
			grow();
		}
		
		elements[size++] = element;
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	public <T> T pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return (T) elements[--size];
	}
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray() {
		T[] result = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			result[i] = (T) elements[size - i - 1];
		}
		
		return result;
	}
	
	private void grow() {
		int newSize = elements.length * 2;
		elements = Arrays.copyOf(elements, newSize);
	}
}
