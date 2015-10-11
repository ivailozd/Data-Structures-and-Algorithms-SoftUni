package main.sorters;

import java.util.ArrayList;
import java.util.List;

import main.contracts.Sorter;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(List<T> collection) {
		mergeSort(collection, 0, collection.size() - 1);
	}

	private void mergeSort(List<T> collection, int from, int to) {
		if (from >= to)
			return;
		
		int middle = (from + to) / 2;
		mergeSort(collection, from, middle);
		mergeSort(collection, middle + 1, to);
		merge(collection, from, middle, to);
		
	}

	private void merge(List<T> collection, int from, int middle, int to) {
		int n = to - from + 1;
	    List<T> values = new ArrayList<>(n);
	    int fromValue = from;
	    int middleValue = middle + 1;
	    while (fromValue <= middle && middleValue <= to) {
	      if (collection.get(fromValue).compareTo(collection.get(middleValue)) < 0) {
	        values.add(collection.get(fromValue));
	        fromValue++;
	      } else {
	        values.add(collection.get(middleValue));
	        middleValue++;
	      }
	    }

	    while (fromValue <= middle) {
	      values.add(collection.get(fromValue));
	      fromValue++;
	    }
	    
	    while (middleValue <= to) {
	      values.add(collection.get(middleValue));
	      middleValue++;
	    }

	    for (int index = 0; index < n; index++) {
	    	collection.set(from + index, values.get(index));
	    }
		
	}

}
