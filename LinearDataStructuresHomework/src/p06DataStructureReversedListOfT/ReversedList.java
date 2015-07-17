package p06DataStructureReversedListOfT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversedList<T> implements Iterable<T>, Iterator<T>{

	private int size = 0;
	private static final int DEFAULT_CAPACITY = 3;
	private Object elements[];
	private int current = 0;
	
	public ReversedList() {
	  elements = new Object[DEFAULT_CAPACITY];
	}

	public void add(T e) {
	  if (size == elements.length) {
	    ensureCapacity();
	  }  
	    
	  elements[size++] = e;
	}
	 

	private void ensureCapacity() {
	  int newSize = elements.length * 2;
	  elements = Arrays.copyOf(elements, newSize);
	}
	  
	public int count() {
		return size;
	}
	
	public int capacity() {
		return elements.length;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index + ", Count: " + size);
		}
		
		return (T) elements[size - index - 1];
	}
	
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index + ", Count: " + size);
		}
		
		index = size - index - 1;
		
		System.arraycopy(elements, index + 1, elements, index, elements.length - (index + 1));
		elements[size - 1] = null;
		size--;
	}
	
	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return current < size;
	}

	@Override
	public T next() {
		if (! hasNext()) {
			throw new NoSuchElementException();
		}
		
		return get(current++);
	}
}
