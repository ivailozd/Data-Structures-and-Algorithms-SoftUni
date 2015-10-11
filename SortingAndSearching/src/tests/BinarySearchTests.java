package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import main.SortableCollection;

import org.junit.Test;

public class BinarySearchTests {
	
	@Test
	public void testWithEmptyCollectionShouldReturnMissingElement() {
        SortableCollection<Integer> collection = new SortableCollection<>();

        int result = collection.binarySearch(0);
        int expected = Arrays.binarySearch(collection.toArray(), 0);

        assertEquals(expected, result);
    }
	
	@Test
	public void testWithMissingElement() {
        SortableCollection<Integer> collection = new SortableCollection<>(-1, 1, 5, 12, 50);

        int result = collection.binarySearch(0);
        int expected = -1;

        assertEquals(expected, result);
    }
	
	@Test
	public void testWithItemAtMidpoint() {
        SortableCollection<Integer> collection = new SortableCollection<>(1, 2, 3, 4, 5);

        int result = collection.binarySearch(3);
        int expected = Arrays.binarySearch(collection.toArray(), 3);

        assertEquals(expected, result);
    }
	
	@Test
	public void testWithItemToTheLeftOfMidpoint() {
        SortableCollection<Integer> collection = new SortableCollection<>(1, 2, 3, 4, 5);

        int result = collection.binarySearch(2);
        int expected = Arrays.binarySearch(collection.toArray(), 2);

        assertEquals(expected, result);
    }
	
	@Test
	public void testWithItemToTheRightOfMidpoint() {
        SortableCollection<Integer> collection = new SortableCollection<>(1, 2, 3, 4, 5);

        int result = collection.binarySearch(4);
        int expected = Arrays.binarySearch(collection.toArray(), 4);

        assertEquals(expected, result);
    }
	
	@Test
	public void testWithMultipleMissingKeysSmallerThanMinimum() {
        final int numberOfChecks = 10000;
        final int numberOfElements = 1000;

        Integer[] elements = new Integer[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            elements[i] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<>(elements);

        for (int i = 0; i < numberOfChecks; i++) {
            int item = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, collection.getItems().get(0));

            int result = collection.binarySearch(item);

            assertEquals(-1, result);
        }
    }
	
	@Test
	public void testWithMultipleMissingKeysLargerThanMaximum() {
        final int numberOfChecks = 10000;
        final int numberOfElements = 1000;

        Integer[] elements = new Integer[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            elements[i] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<>(elements);

        for (int i = 0; i < numberOfChecks; i++) {
            int item = ThreadLocalRandom.current().nextInt(collection.getItems().get(collection.size() - 1), Integer.MAX_VALUE);

            int result = collection.binarySearch(item);

            assertEquals(-1, result);
        }
    }
	
	@Test
	public void testWithMultipleKeys() {
        final int numberOfElements = 10000;

        Integer[] elements = new Integer[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            elements[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<>(elements);

        for (Integer element : elements) {
            int expected = Arrays.binarySearch(elements, element);
            int result = collection.binarySearch(element);

            assertEquals(expected, result);
        }
    }
	
	@Test
	public void testWithRepeatingItemShouldReturnFirstDiscoveredIndex() {
        SortableCollection<Integer> collection = new SortableCollection<>(0, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7);
        int result = collection.binarySearch(3);

        assertEquals(2, result);
    }

}
