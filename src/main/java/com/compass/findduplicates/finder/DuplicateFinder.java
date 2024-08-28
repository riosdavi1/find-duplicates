package com.compass.findduplicates.finder;

import java.util.List;

/**
 * A generic finder of duplicated elements in a list.
 * 
 * @param <T> the type of elements in the list
 */
public interface DuplicateFinder<T> {

	/**
	 * Returns a list of {@code FindDuplicateResult} elements containing the matching Id's
	 * and the accuracy.
	 * 
	 * @param list the list of elements containing the possible duplicates.
	 * @return a list of {@code FindDuplicateResult} elements
	 */
	List<FindDuplicateResult> find(List<T> list);
}
