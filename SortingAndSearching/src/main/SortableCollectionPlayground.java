package main;

import java.util.Random;

import main.contracts.Sorter;
import main.sorters.*;

public class SortableCollectionPlayground {
	private static Random random = new Random();

	public static void main(String[] args) {
		final int numberOfElementsToSort = 22;
        final int maxValue = 150;

        Integer[] array = new Integer[numberOfElementsToSort];

        for (int i = 0; i < numberOfElementsToSort; i++) {
            array[i] = random.nextInt(maxValue);
        }

        Sorter<Integer> sorter = new InsertionSorter<>();
        SortableCollection<Integer> collectionToSort = new SortableCollection<>(array);
        collectionToSort.sort(sorter);

        System.out.println(collectionToSort);

        SortableCollection<Integer> collection = new SortableCollection<>(2, -1, 5, 0, -3);
        System.out.println(collection);

        collection.sort(sorter);
        System.out.println(collection);
		
		int index = collectionToSort.interpolationSearch(collectionToSort.getItems().get(7).intValue());
		System.out.println(index);
		
		collection.shuffle();
		System.out.println(collection);
	}

}
