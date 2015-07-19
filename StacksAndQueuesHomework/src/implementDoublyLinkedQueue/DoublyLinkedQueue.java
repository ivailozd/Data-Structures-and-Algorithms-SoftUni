package implementDoublyLinkedQueue;

import java.util.NoSuchElementException;

public class DoublyLinkedQueue<T> {
	
	private int size;
	private QueueNode<T> head;
	private QueueNode<T> tail;
	
	public DoublyLinkedQueue() {
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * Returns the actual size of the queue.
	 * @return the queue's size.
	 */
	public int count() {
		return size;
	}
	
	/**
	 * Adds given element to the queue.
	 * @param element - the element to be added.
	 */
    public void enqueue(T element) {
    	QueueNode<T> newNode = new QueueNode<T>(element);
    	if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.nextNode = newNode;
			newNode.prevNode = tail;
			tail = newNode;
		}
    	
    	size++;
    }
    
    /**
     * Removes and returns the first added element.
     * @return the first element.
     */
    public T dequeue() {
    	if (size == 0) {
			throw new NoSuchElementException("Queue is empty!");
		}
    	
    	T dequeued = head.value;
    	head = head.nextNode;
    	
    	// if it is not the last element
    	if (head != null) {
			head.prevNode = null;
		}
    	
    	size--;
    	
    	return dequeued;
    }
    
    /**
     * Returns array representation of the queue.
     * @return array of the queue's elements.
     */
    @SuppressWarnings({ "hiding", "unchecked" })
	public <T> T[] toArray() {
    	T[] result = (T[]) new Object[size];
    	QueueNode<T> current = (QueueNode<T>) head;
		for (int i = 0; i < size; i++) {
			result[i] = current.value;
			current = current.nextNode;
		}
    	
		return result;
    }

    /**
     * Inner class for the queue's node
     * @param <T>
     */
    @SuppressWarnings("hiding")
	private class QueueNode<T>
    {
        public T value;
        public QueueNode<T> nextNode;
        @SuppressWarnings("unused")
		public QueueNode<T> prevNode;
        
        public QueueNode(T value) {
			this.value = value;
			nextNode = null;
			prevNode = null;
		}
    }
}
