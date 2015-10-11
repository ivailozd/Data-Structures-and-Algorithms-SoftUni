package main.sorters;

import java.util.List;

import main.contracts.Sorter;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {
	private int n;

	@Override
	public void sort(List<T> collection) {
		heapify(collection);        
        for (int i = n; i > 0; i--) {
            swap(collection, 0, i);
            n = n-1;
            maxheap(collection, 0);
        }
	}

	private void maxheap(List<T> collection, int i) {
		int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= n && collection.get(left).compareTo(collection.get(i)) > 0)
            max = left;
        if (right <= n && collection.get(right).compareTo(collection.get(max)) > 0)        
            max = right;
 
        if (max != i)        {
            swap(collection, i, max);
            maxheap(collection, max);
        }
		
	}

	private void swap(List<T> collection, int i, int j) {
		T temp = collection.get(i);
		collection.set(i, collection.get(j));
		collection.set(j, temp);
	}

	private void heapify(List<T> collection) {
		n = collection.size() - 1;
        for (int i = n / 2; i >= 0; i--){
        	maxheap(collection, i);
        }
	}

}
