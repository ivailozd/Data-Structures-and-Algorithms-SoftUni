package main.sorters;

import java.util.List;

import main.contracts.Sorter;

public class Quicksorter<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(List<T> collection) {
		quicksort(collection, 0, collection.size() - 1);
	}

	private void quicksort(List<T> collection, int lo, int hi) {
		if (lo < hi) {
			int p = partition(collection, lo, hi);
			quicksort(collection, lo, p);
			quicksort(collection, p + 1, hi);
		}
	}

	private int partition(List<T> collection, int lo, int hi) {
		T pivot = collection.get(lo);
		int i = lo;
		int j = hi;
		while (true) {
			while (collection.get(j).compareTo(pivot) > 0) {
				j--;
			}
			
			while (collection.get(i).compareTo(pivot) < 0) {
				i++;
			}
			
			if (i < j) {
				swap(collection, i, j);
			} else {
				return j;
			}
		}
	}

	private void swap(List<T> collection, int i, int j) {
		T temp = collection.get(i);
		collection.set(i, collection.get(j));
		collection.set(j, temp);
	}

}
