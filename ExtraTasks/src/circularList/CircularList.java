package circularList;

import java.util.NoSuchElementException;

public class CircularList<T> {
	
	private int count;
	private Link<?> current;
	
	public CircularList() {
		count = 0;
		current = null;
	}
	
	/**
	 * Adds new item on the right of the current and
	 * moves current to the new item.
	 * @param item - the item to be added
	 */
	public void add(T item) {
		Link<T> newLink = new Link<T>(item);
		if (current == null) {
			current = newLink;
			current.right = newLink;
			current.left = newLink;
		} else {
			newLink.right = current.right;
			newLink.left = current;
			current.right.left = newLink;
			current.right = newLink;
			current = newLink;
		}
		
		count++;
	}

	@SuppressWarnings("unchecked")
	public T getCurrent() {
		return (T) current.data;
	}
	
	@SuppressWarnings("unchecked")
	public T removeCurrent() {
		Link<?> temp;
		if (current == null) {
			throw new NoSuchElementException();
		} else {
			temp = current.left;
			current.left = current.right;
			current.right = temp;
			temp = current;
			current = current.left;
		}
		
		count--;
		
		return (T) temp.data;
	}

	public int count() {
		return count;
	}
	
	@Override
	public String toString() {
		String result = "[";
		int index = 0;
		while (index < count) {
			result += current.data.toString() + " ";
			stepRight();
			index++;
		}
		
		return result.trim() + "]";
	}

	public void stepRight() {
		current = current.right;
	}
	
	public void stepLeft() {
		current = current.left;
	}
}
