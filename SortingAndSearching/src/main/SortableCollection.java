package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import main.contracts.Sorter;

public class SortableCollection<T extends Comparable<T>> {
	private List<T> items;
	
	public SortableCollection() {
		items = new ArrayList<>();
    }

    public SortableCollection(Collection<T> items) {
        this.items = (List<T>) items;
    }

    @SafeVarargs
	public SortableCollection(T... items) {
    	this(new ArrayList<>(Arrays.asList(items)));
    }

    public int size() {
		return items.size();
	}

    public void sort(Sorter<T> sorter) {
        sorter.sort(items);
    }

    public int binarySearch(T item) {
    	throw new UnsupportedOperationException();
    }

    public int interpolationSearch(Integer item) {
    	return find(item, 0, items.size() - 1);
    }

    private int find(Integer item, int from, int to) {
    	if (items.get(from).equals(item)){
	    	return from;
	    } else if (from >= to || items.get(from).equals(items.get(to))) {
	    	return -1;
	    }

	    int index = from + ((to - from)/( (Integer)items.get(to) - (Integer)items.get(from))) * (item - (Integer)items.get(from));
	    
	    if (items.get(index).equals(item)) {
	    	return index;
	    } else if (((Integer)items.get(index)).compareTo(item) < 0) {
	    	return find(item, index + 1, to);
	    } else {
	    	return find(item, from, index - 1);
	    }
	}

	public void shuffle() {
		int upperBound = items.size();
		int j;
		T temp;
		for (int i = 0; i < upperBound - 2; i++) {
			j = ThreadLocalRandom.current().nextInt(i, upperBound);
			temp = items.get(i);
			items.set(i, items.get(j));
			items.set(j, temp);
		}
    }

	public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (T item : items) {
			sb.append(item + ", ");
		}
    	
    	sb.deleteCharAt(sb.length() - 2);
        return sb.toString().trim();
    }

	public List<T> getItems() {
		return items;
	}  
}
