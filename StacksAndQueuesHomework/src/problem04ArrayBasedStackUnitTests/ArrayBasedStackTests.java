package problem04ArrayBasedStackUnitTests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.EmptyStackException;

import org.junit.Test;

import problem03ImplementArrayBasedStack.ArrayBasedStack;

public class ArrayBasedStackTests {

	@Test
	public void pushPopElement() {
		ArrayBasedStack<Integer> stack = new ArrayBasedStack<>();
		assertEquals(0, stack.count());
		
		stack.push(33);
		assertEquals(1, stack.count());
		
		int popped = stack.pop();
		assertEquals(33, popped);
		assertEquals(0, stack.count());
	}
	
	@Test
	public void pushPopThousandElements() {
		ArrayBasedStack<String> stack = new ArrayBasedStack<>();
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
		ArrayBasedStack<String> stack = new ArrayBasedStack<>();
		stack.pop();
	}
	
	@Test
	public void pushPopWithInitialCapacity(){
		ArrayBasedStack<Integer> stack = new ArrayBasedStack<>(1);
		assertEquals(0, stack.count());
		
		stack.push(33);
		assertEquals(1, stack.count());
		
		stack.push(55);
		assertEquals(2, stack.count());
		
		int popped = stack.pop();
		assertEquals(55, popped);
		assertEquals(1, stack.count());
		
		popped = stack.pop();
		assertEquals(33, popped);
		assertEquals(0, stack.count());
	}
	
	@Test
	public void toArrayTest(){
		ArrayBasedStack<Integer> stack = new ArrayBasedStack<>();
		stack.push(11);
		stack.push(-22);
		stack.push(33);
		stack.push(44);
		
		assertArrayEquals(new Integer[]{44, 33, -22, 11}, stack.toArray());
		
	}
	
	@Test
	public void toArrayEmptyStack(){
		ArrayBasedStack<Date> stack = new ArrayBasedStack<>();
		assertArrayEquals(new Date[]{}, stack.toArray());
	}
}
