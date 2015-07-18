package circularList.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import circularList.CircularList;

public class AddRemoveTest {

	@Test
	public void AddSeveralItems() {
		CircularList<String> list = new CircularList<>();
		list.add("This");
		list.add("is");
		list.add("add");
		list.add("test");
		
		assertEquals(4, list.count());
		assertEquals("test", list.getCurrent());
	}

	@Test
	public void RemoveSeveralItems(){
		CircularList<String> list = new CircularList<>();
		list.add("This");
		list.add("is");
		list.add("remove");
		list.add("test");
		
		String removed = list.removeCurrent();
		
		assertEquals(3, list.count());
		assertEquals("test", removed.toString());
		assertEquals("This", list.getCurrent());
		
		removed = list.removeCurrent();
		
		assertEquals(2, list.count());
		assertEquals("This", removed.toString());
		assertEquals("is", list.getCurrent());
	}
}
