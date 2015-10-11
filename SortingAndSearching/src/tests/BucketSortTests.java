package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import main.SortableCollection;
import main.sorters.BucketSorter;

import org.junit.Test;

public class BucketSortTests {
	private static final int NUMBER_OF_TESTS = 5000;

    private static final int MIN_NUMBER_ELEMENTS_TO_SORT = 1;
    private static final int MAX_NUMBER_ELEMENTS_TO_SORT = 10000;

    private static final Integer MAX_VALUE_CEILING = Integer.MAX_VALUE;

	@Test
	public void testSortWithMultipleRandomElements() {
		for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            int numberOfElements = ThreadLocalRandom.current().nextInt(MIN_NUMBER_ELEMENTS_TO_SORT, MAX_NUMBER_ELEMENTS_TO_SORT + 1);
            int maxValue = ThreadLocalRandom.current().nextInt(MAX_VALUE_CEILING);

            Integer[] elements = new Integer[numberOfElements];

            for (int j = 0; j < numberOfElements; j++) {
                elements[j] = ThreadLocalRandom.current().nextInt(maxValue);
            }

            SortableCollection<Integer> collection = new SortableCollection<>(elements);

            Arrays.sort(elements);
            collection.sort(new BucketSorter<Integer>());

            assertEquals(elements, collection);
        }
	}

}
