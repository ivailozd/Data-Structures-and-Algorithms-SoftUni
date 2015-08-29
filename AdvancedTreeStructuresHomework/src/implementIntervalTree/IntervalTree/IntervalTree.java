package implementIntervalTree.IntervalTree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class IntervalTree<T extends Comparable<T>> implements Iterable<Interval<T>>{
	
	private final NavigableMap<T, Interval<T>> intervals;
	
	public IntervalTree() {
		intervals = new TreeMap<>();
	}
	
	/**
	 * Method checking if tree is empty
	 * @return true if there is no intervals in the tree
	 */
	public boolean isEmpty() {
		return intervals.isEmpty();
	}
	
	/**
	 * Adds an interval to the tree
	 * @param interval
	 */
	public void add(Interval<T> interval) {
		intervals.put(interval.getStart(), interval);
	}
	
	/**
	 * Removes an interval from tree if exists
	 * @param interval
	 */
	public void remove(Interval<T> interval) {
		intervals.remove(interval);
	}
	
	/**
	 * Returns a sub map of all intervals containing the alement
	 * @param element
	 * @return NavigableMap<T, Interval<T>>
	 */
	public Map<T, Interval<T>> get(T element) {
		Entry<T, Interval<T>> entry = intervals.floorEntry(element);
		
		if (entry == null) {
			return new HashMap<T, Interval<T>>();
		}
		
		Interval<T> interval = entry.getValue();
		if (! interval.contains(element)) {
			return new HashMap<T, Interval<T>>();
		}
		
		return intervals.headMap(entry.getKey(), true);
	}

	@Override
	public Iterator<Interval<T>> iterator() {
		return intervals.values().iterator();
	}
	
}
