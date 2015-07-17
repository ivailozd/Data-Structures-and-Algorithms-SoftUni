package p07LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<T> implements Iterator<T>{

	@SuppressWarnings("unused")
	private LinkedList<T> list;
	private Link<T> currentLink;
	private Link<T> nextLink;
	
	@SuppressWarnings("unchecked")
	public ListIterator(LinkedList<T> list) {
		this.list = list;
		nextLink = (Link<T>) list.getFirst();
	}
	
	@Override
	public boolean hasNext() {
		return nextLink != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		if (! hasNext()) {
			throw new NoSuchElementException();
		}
		
		currentLink = nextLink;
		nextLink = (Link<T>) nextLink.next;
		
		return currentLink.element;
	}

}
