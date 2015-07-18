package circularList.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import circularList.CircularList;

public class ToStringTest {

	@Test
	public void toStringList() {
		CircularList<String> list = new CircularList<>();
		list.add("This");
		list.add("is");
		list.add("toString");
		list.add("test");
		list.stepRight();
		
		String result = list.toString();
		
		assertEquals("[This is toString test]", result);
	}

}
