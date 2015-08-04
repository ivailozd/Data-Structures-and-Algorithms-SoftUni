package implementHashTable;

public class KeyValue<TKey, TValue> {
	
	private TKey key;
	private TValue value;
	
	public KeyValue(TKey key, TValue value) {
		this.setKey(key);
		this.setValue(value);
	}

	public TKey getKey() {
		return key;
	}

	public void setKey(TKey key) {
		this.key = key;
	}

	public TValue getValue() {
		return value;
	}

	public void setValue(TValue value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		KeyValue<TKey, TValue> element = (KeyValue<TKey, TValue>) obj;
		boolean equals = getKey().equals(element.getKey()) && getValue().equals(element.getValue());
		return equals;
	}
	
	@Override
	public int hashCode() {
		return combineHashCode(getKey().hashCode(), getValue().hashCode());
	}

	private int combineHashCode(int h1, int h2) {
		return ((h1 << 5) + h1) ^ h2;
	}
	
	@Override
	public String toString() {
		return "[" + getKey() + " -> " + getValue() + "]";
	}
}
