package p07LinkedList.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import p07LinkedList.LinkedList;

public class AddRemoveTest {

	@Test
	public void addThreeElements() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(11);
		list.add(22);
		list.add(33);
		assertEquals(3, list.count());
	}
	
	@Test
	public void removeLastElement() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(11);
		list.remove(0);
		assertEquals(0, list.count());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeElementFromEmptyList() {
		LinkedList<Integer> list = new LinkedList<>();
		list.remove(0);
	}
	
}
