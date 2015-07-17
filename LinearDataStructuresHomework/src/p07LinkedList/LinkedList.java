package p07LinkedList;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
	
	private int count;
	private Link<?> first;
	private Link<?> last;
	private Link<?> currentLink;
	private Link<?> prevLink;
	
	public LinkedList() {
		first = null;
		last = null;
		count = 0;
	}
	
	
	/**
	 * Adds element at the end of the list
	 * @param element- the element to be added
	 */
	public void add(T element) {
		Link<T> newLink = new Link<T>(element);
		if (first == null) {
			first = newLink;
			last = newLink;
		}
		else {
			last.next = newLink;
			last = newLink;
		}
		
		count++;
	}
	
	
	/**
	 * Removes and returns element on the specific index
	 * @param index - the index of the element to be removed
	 * @return the removed element
	 * @exception IndexOutOfBoundsException - when index is invalid
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}
		
		int currentIndex = 0;
		currentLink = first;
		prevLink = null;
		while (currentIndex < index) {
			prevLink = currentLink;
			currentLink = currentLink.next;
			currentIndex++;
		}
		
		count--;
		if (count == 0) {
			first = null;
			last = null;
		} else if (prevLink == null) {
			first = currentLink.next;
		} else {
			prevLink.next = currentLink.next;
		}
		
		return (T) currentLink.element;
	}
	
	/**
	 * Returns the list's length
	 * @return the element's count
	 */
	public int count() {
		return count;
	}
	
	/**
	 * Returns the index of the first occurrence of the element
	 * @param element - the searched element
	 * @return element's index
	 */
	public int firstIndexOf(T element) {
		int index = 0;
		currentLink = first;
		while (currentLink != null) {
			if (element == null && currentLink.element == null || 
					element != null && element.equals(currentLink.element)) {
				return index;
			}
			
			index++;
			currentLink = currentLink.next;
		}
		
		return -1;
	}
	
	/**
	 * Returns the index of the last occurrence of the element
	 * @param element - the searched element
	 * @return element's index
	 */
	public int lastIndexOf(T element) {
		int index = 0;
		int found = -1;
		currentLink = first;
		while (index < count) {
			if (element == null && currentLink.element == null || 
					element != null && element.equals(currentLink.element)) {
				found = index;
			}
			
			index++;
			currentLink = currentLink.next;
		}
		
		return found;
	}

	/**
	 * Returns an iterator over the list
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(this);
	}

	/**
	 * Returns the first list's element
	 * @return first element
	 */
	@SuppressWarnings("unchecked")
	public T getFirst() {
		return (T) first;
	}
}
