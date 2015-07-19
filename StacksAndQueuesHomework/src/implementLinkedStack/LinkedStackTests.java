package implementLinkedStack;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.EmptyStackException;

import org.junit.Test;

public class LinkedStackTests {

	@Test
	public void pushPopElement() {
		LinkedStack<Integer> stack = new LinkedStack<>();
		assertEquals(0, stack.count());
		
		stack.push(33);
		assertEquals(1, stack.count());
		
		int popped = stack.pop();
		assertEquals(33, popped);
		assertEquals(0, stack.count());
	}

	@Test
	public void pushPopThousandElements() {
		LinkedStack<String> stack = new LinkedStack<>();
		assertEquals(0, stack.count());
		
		for (int i = 1; i <= 1000; i++) {
			stack.push(i + "");
			assertEquals(i, stack.count());
		}
		
		String popped = "";
		for (int i = 1000; i >= 1; i--) {
			popped = stack.pop();
			assertEquals(i, Integer.parseInt(popped));
			assertEquals(i - 1, stack.count());
		}
	}
	
	@Test(expected=EmptyStackException.class)
	public void popFromEmptyStack(){
		LinkedStack<String> stack = new LinkedStack<>();
		stack.pop();
	}
	
	@Test
	public void toArrayTest(){
		LinkedStack<Integer> stack = new LinkedStack<>();
		stack.push(11);
		stack.push(-22);
		stack.push(33);
		stack.push(44);
		
		assertArrayEquals(new Integer[]{44, 33, -22, 11}, stack.toArray());
	}
	
	@Test
	public void toArrayEmptyStack(){
		LinkedStack<Date> stack = new LinkedStack<>();
		assertArrayEquals(new Date[]{}, stack.toArray());
	}
}
