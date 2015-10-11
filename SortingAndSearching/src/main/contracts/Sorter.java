package main.contracts;

import java.util.List;

public interface Sorter<T> {
	void sort(List<T> collection);
}
