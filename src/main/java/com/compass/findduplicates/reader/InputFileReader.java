package com.compass.findduplicates.reader;

import java.util.List;

/**
 * A generic input file reader that returns the file content as a list of <T>
 * elements.
 * 
 * @param <T> the type of the elements to return.
 */
public interface InputFileReader<T> {

	/**
	 * Reads the file indicated by fileName, and return the content as a list of <T>
	 * elements.
	 * 
	 * @param fileName
	 * @return Returns the list of <T> elements.
	 * @throws Exception if the file cannot be opened or it doesn't contain valid
	 *                   <T> elements.
	 */
	List<T> read(String fileName) throws Exception;
}
