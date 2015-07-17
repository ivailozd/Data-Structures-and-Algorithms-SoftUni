package p07LinkedList.tests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import p07LinkedList.LinkedList;

public class IteratorTest {

	@Test
	public void hasNextOnEmptyList() {
		LinkedList<Integer> list = new LinkedList<>();
		Iterator<Integer> it = list.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void nextOnEmptyList(){
		LinkedList<Integer> list = new LinkedList<>();
		Iterator<Integer> it = list.iterator();
		it.next();
	}
	
	@Test
	public void hasNextOnListWithOneItem(){
		LinkedList<Integer> list = new LinkedList<>();
		list.add(123);
		Iterator<Integer> it = list.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
	}
	
	@Test
	public void hasNextAndNextOnListWithOneItem(){
		LinkedList<Integer> list = new LinkedList<>();
		list.add(123);
		Iterator<Integer> it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals(new Integer(123), it.next());
		assertFalse(it.hasNext());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void IterateInCorrectOrder(){
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Integer> it = list.iterator();
		
		assertEquals(new Integer(1), it.next());
		assertEquals(new Integer(2), it.next());
		assertEquals(new Integer(3), it.next());
		assertEquals(new Integer(4), it.next());
	}
}
