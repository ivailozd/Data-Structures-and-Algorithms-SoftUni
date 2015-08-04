package implementHashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.management.openmbean.InvalidKeyException;

@SuppressWarnings("unchecked")
public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {
	
	private static final int INITIAL_CAPACITY = 16;
	private static final float LOAD_FACTOR = 0.75f;
	private List<? super KeyValue<TKey, TValue>>[] slots;
	private int count;
	
	public HashTable() {
		slots = (List<? super KeyValue<TKey, TValue>>[]) new LinkedList<?>[INITIAL_CAPACITY];
		count = 0;
	}
	
	public HashTable(int capacity) {
		slots = (List<? super KeyValue<TKey, TValue>>[]) new LinkedList<?>[capacity];
		count = 0;
	}
	
	/**
	 * Finds element by given key
	 * @param key - searched key
	 * @return - the element matches the key or null if doesn't exist
	 */
	public KeyValue<TKey, TValue> find(TKey key) {
		int slotNumber = findSlotNumber(key);
		List<? super KeyValue<TKey, TValue>> elements = slots[slotNumber];
		if (elements != null) {
			for (Object object : elements) {
				if ( ((KeyValue<TKey, TValue>) object).getKey().equals(key) ) {
					return (KeyValue<TKey, TValue>) object;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the value by given key or throws exception
	 * @param key - searched key
	 * @exception - InvalidKeyException if key doesn't exist
	 * @return - the element matches the key 
	 */
	public TValue get(TKey key) {
		KeyValue<TKey, TValue> element = find(key);
		if (element == null) {
			throw new InvalidKeyException();
		}
		
		return element.getValue();
	}
	
	/**
	 * Checks if HashTable contains given key
	 * @param key - searched key
	 * @return true if exists otherwise false
	 */
	public boolean containsKey(TKey key) {
		KeyValue<TKey, TValue> element = find(key);
		return element != null;
	}
	
	/**
	 * Adds an element by given key and value
	 * @param key - the element's key
	 * @param value - the element's value
	 */
	public void add(TKey key, TValue value) {
		growIfNeeded();
		int slotNumber = findSlotNumber(key);
		if (slots[slotNumber] == null) {
			slots[slotNumber] = new LinkedList<KeyValue<TKey, TValue>>();
		}
		
		for (Object element : slots[slotNumber]) {
			if (((KeyValue<TKey, TValue>)element).getKey().equals(key)) {
				throw new IllegalArgumentException("Key already exists: " + key);
			}
		}
		
		KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
		slots[slotNumber].add(newElement);
		count++;
	}
	
	/**
	 * Adds an elements or updates its value by given key and value
	 * @param key - the element's key
	 * @param value - the element's value
	 * @return - true if added, false if replaced
	 */
	public boolean addOrReplace(TKey key, TValue value) {
		growIfNeeded();
		int slotNumber = findSlotNumber(key);
		if (slots[slotNumber] == null) {
			slots[slotNumber] = new LinkedList<KeyValue<TKey, TValue>>();
		}
		
		for (Object element : slots[slotNumber]) {
			if (((KeyValue<TKey, TValue>)element).getKey().equals(key)) {
				((KeyValue<TKey, TValue>)element).setValue(value);
				return false;
			}
		}
		
		KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
		slots[slotNumber].add(newElement);
		count++;
		
		return true;
	}
	
	/**
	 * Works like addOrReplace, but doesn't return result.
	 * @param key - the element's key
	 * @param value - the element's value
	 */
	public void set(TKey key, TValue value) {
		addOrReplace(key, value);
	}
	
	/**
	 * Removes an element by given key
	 * @param key - the element's key
	 * @return true if removed or false if doesn't exist
	 */
	public boolean remove(TKey key) {
		int slotNumber = findSlotNumber(key);
		List<? super KeyValue<TKey, TValue>> elements = slots[slotNumber];
		if (elements != null) {
			for (Object object : elements) {
				if (((KeyValue<TKey, TValue>) object).getKey().equals(key)) {
					elements.remove((KeyValue<TKey, TValue>) object);
					count--;
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Gets list of HashTable's keys
	 * @return list of HashTable's keys
	 */
	public Iterable<TKey> getKeys() {
		List<TKey> keys = new LinkedList<>();
		this.forEach(e -> keys.add(e.getKey()));
		return keys;
	}
	
	/**
	 * Gets list of HashTable's values
	 * @return list of HashTable's values
	 */
	public Iterable<TValue> getValues() {
		List<TValue> values = new LinkedList<>();
		this.forEach(e -> values.add(e.getValue()));
		return values;
	}
	
	/**
	 * Helper method. Finds slot's index be given key
	 * @param key - searched key
	 * @return the slot's index
	 */
	private int findSlotNumber(TKey key) {
		int slotNumber = Math.abs(key.hashCode()) % slots.length;
		return slotNumber;
	}

	/**
	 * Checks if HashTable's load factor is reached and
	 * calls grow() if needed.
	 */
	private void growIfNeeded() {
		if ((float)(count + 1) / getCapacity() > LOAD_FACTOR) {
			grow();
		}
	}

	/**
	 * Expands double capacity and rehashes the elements.
	 */
	private void grow() {
		HashTable<TKey, TValue> newHashTable = new HashTable<>(2 * getCapacity());
		for (KeyValue<TKey, TValue> element : this) {
			newHashTable.add(element.getKey(), element.getValue());
		}
		
		slots = newHashTable.slots;
		count = newHashTable.getCount();
	}

	/**
	 * Returns the actual count of the elements.
	 * @return the elements' count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Returns the actual capacity of the HashMap.
	 * @return the HashMap's capacity
	 */
	public int getCapacity() {
		return slots.length;
	}
	
	/**
	 * Returns Iterator<KeyValue<TKey, TValue>>
	 */
	@Override
	public Iterator<KeyValue<TKey, TValue>> iterator() {
		return new HashTableIterator();
		
	}
	
	/**
	 * Custom iterator implementation
	 */
	private class HashTableIterator implements Iterator<KeyValue<TKey, TValue>>{
		
		private List<? super KeyValue<TKey, TValue>> currentSlot = null;
		private KeyValue<TKey, TValue> currentPair= null;
		private int currentIndex = 0;
		private int currentSlotIndex;
		private int currentPairIndex;
		
		public HashTableIterator() {
			currentSlot = slots[0];
			currentSlotIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < count;
		}

		@Override
		public KeyValue<TKey, TValue> next() {
			
			if (currentSlot == null || currentPairIndex == currentSlot.size()) {
				currentSlotIndex++;
				currentSlot = slots[currentSlotIndex];
				while (currentSlot == null) {
					currentSlotIndex++;
					currentSlot = slots[currentSlotIndex];
				}
				
				currentPairIndex = 0;
				currentPair = (KeyValue<TKey, TValue>) currentSlot.get(currentPairIndex);
				currentPairIndex++;
				
			} else if (currentPairIndex < currentSlot.size()) {
				currentPair = (KeyValue<TKey, TValue>) currentSlot.get(currentPairIndex);
				currentPairIndex++;
			}
			
			currentIndex++;
			return currentPair;
		}
	}
	
	@Override
	public String toString() {
		String result = "[";
		
		for (List<? super KeyValue<TKey, TValue>> list : slots) {
			if (list != null) {
				for (Object object : list) {
					result += object + " ";
				}
			} else {
				result += "null ";
			}
		}
		
		return result.trim() + "]";
	}
	
	public List<KeyValue<TKey, TValue>> toList() {
		List<KeyValue<TKey, TValue>> result = new LinkedList<>();
		for (KeyValue<TKey, TValue> element : this) {
			result.add(element);
		}
		
		return result;
	}
	
}
