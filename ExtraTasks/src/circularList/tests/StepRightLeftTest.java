package circularList.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import circularList.CircularList;

public class StepRightLeftTest {

	@Test
	public void StepsRight() {
		CircularList<String> list = new CircularList<>();
		list.add("This");
		list.add("is");
		list.add("toString");
		list.add("test");
		
		list.stepRight();
		assertEquals("This", list.getCurrent());
		
		list.stepRight();
		assertEquals("is", list.getCurrent());
		
		list.stepRight();
		assertEquals("toString", list.getCurrent());
		
		list.stepRight();
		assertEquals("test", list.getCurrent());
		
		list.stepRight();
		assertEquals("This", list.getCurrent());
		
		list.stepRight();
		assertEquals("is", list.getCurrent());
	}
	
	@Test
	public void StepsLeft() {
		CircularList<String> list = new CircularList<>();
		list.add("This");
		list.add("is");
		list.add("toString");
		list.add("test");
		
		list.stepLeft();
		assertEquals("toString", list.getCurrent());
		
		list.stepLeft();
		assertEquals("is", list.getCurrent());
		
		list.stepLeft();
		assertEquals("This", list.getCurrent());
		
		list.stepLeft();
		assertEquals("test", list.getCurrent());
		
		list.stepLeft();
		assertEquals("toString", list.getCurrent());
		
		list.stepLeft();
		assertEquals("is", list.getCurrent());
	}

}
