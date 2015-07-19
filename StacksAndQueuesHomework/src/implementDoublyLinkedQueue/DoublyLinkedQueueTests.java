package implementDoublyLinkedQueue;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DoublyLinkedQueueTests {

	@Test
	public void enqueueDequeueElement() {
		DoublyLinkedQueue<Integer> stack = new DoublyLinkedQueue<>();
		assertEquals(0, stack.count());
		
		stack.enqueue(33);
		assertEquals(1, stack.count());
		
		int dequeued = stack.dequeue();
		assertEquals(33, dequeued);
		assertEquals(0, stack.count());
	}
	
	@Test
	public void pushPopThousandElements() {
		DoublyLinkedQueue<String> stack = new DoublyLinkedQueue<>();
		assertEquals(0, stack.count());
		
		for (int i = 1; i <= 1000; i++) {
			stack.enqueue(i + "");
			assertEquals(i, stack.count());
		}
		
		String dequeued = "";
		for (int i = 1; i <= 1000; i++) {
			dequeued = stack.dequeue();
			assertEquals(i, Integer.parseInt(dequeued));
			assertEquals(1000 - i, stack.count());
		}
	}

	@Test(expected=NoSuchElementException.class)
	public void popFromEmptyStack(){
		DoublyLinkedQueue<String> stack = new DoublyLinkedQueue<>();
		stack.dequeue();
	}
	
	@Test
	public void toArrayTest(){
		DoublyLinkedQueue<Integer> stack = new DoublyLinkedQueue<>();
		stack.enqueue(11);
		stack.enqueue(-22);
		stack.enqueue(33);
		stack.enqueue(44);
		
		assertArrayEquals(new Integer[]{11, -22, 33, 44}, stack.toArray());
	}
	
	@Test
	public void toArrayEmptyStack(){
		DoublyLinkedQueue<Date> stack = new DoublyLinkedQueue<>();
		assertArrayEquals(new Date[]{}, stack.toArray());
	}
}
