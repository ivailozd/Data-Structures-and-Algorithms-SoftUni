package main.sorters;

import java.util.List;

import main.contracts.Sorter;

public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(List<T> collection) {
		int j;
		for (int i = 0; i < collection.size(); i++) {
			j = i;
			while (j > 0 && collection.get(j - 1).compareTo(collection.get(j)) > 0) {
				swap(collection, j, j - 1);
				j--;
			}
		}
	}
	
	private void swap(List<T> collection, int i, int j) {
		T temp = collection.get(i);
		collection.set(i, collection.get(j));
		collection.set(j, temp);
	}

}
