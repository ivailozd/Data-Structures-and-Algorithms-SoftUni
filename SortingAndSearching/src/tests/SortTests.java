package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import main.SortableCollection;
import main.contracts.Sorter;
import main.sorters.*;

import org.junit.Test;

public class SortTests {
//	private static final Sorter<Integer> TEST_SORTER = new InsertionSorter<>();
//	private static final Sorter<Integer> TEST_SORTER = new Quicksorter<>();
//	private static final Sorter<Integer> TEST_SORTER = new MergeSorter<>();
	private static final Sorter<Integer> TEST_SORTER = new HeapSorter<>();

	@Test
	public void testSortWithNoElements() {
		SortableCollection<Integer> emptyCollection = new SortableCollection<>();
        emptyCollection.sort(TEST_SORTER);

        assertArrayEquals(new Integer[0], emptyCollection.toArray());
	}
	
	@Test
	public void testSortWithOneElement() {
        SortableCollection<Integer> collection = new SortableCollection<Integer>(1);
        collection.sort(TEST_SORTER);

        assertArrayEquals(new Integer[] { 1 }, collection.toArray());
    }
	
	@Test
	public void testSortWithTwoElements() {
        SortableCollection<Integer> collection = new SortableCollection<>(1, -5);
        collection.sort(TEST_SORTER);

        assertArrayEquals(new Integer[] { -5, 1 }, collection.toArray());
    }
	
	@Test
	public void testSortWithMultipleElements() {
        SortableCollection<Integer> collection = new SortableCollection<>(3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48);
        Integer[] copy = new Integer[] { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };

        collection.sort(TEST_SORTER);
        Arrays.sort(copy);

        assertArrayEquals(copy, collection.toArray());
    }
	
	@Test
	public void testSortWithMultipleElementsMultipleTimes() {
        final int numberOfAttempts = 10000;
        final int maxNumberOfElements = 1000;

        for (int i = 0; i < numberOfAttempts; i++) {
            int numberOfElements = ThreadLocalRandom.current().nextInt(0, maxNumberOfElements + 1);

            List<Integer> originalElements = new ArrayList<>(maxNumberOfElements);

            for (int j = 0; j < numberOfElements; j++) {
                originalElements.add(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
            }

            SortableCollection<Integer> collection = new SortableCollection<>(originalElements);

            Collections.sort(originalElements);
            collection.sort(TEST_SORTER);

            assertArrayEquals(originalElements.toArray(), collection.toArray());
        }
    }

}
