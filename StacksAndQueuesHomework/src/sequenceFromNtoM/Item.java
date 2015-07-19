package sequenceFromNtoM;

public class Item {
	public int value;
	public Item prev;
	
	public Item(int value, Item prev) {
		this.value = value;
		this.prev = prev;
	}
}
