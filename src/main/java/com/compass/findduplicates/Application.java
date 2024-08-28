package com.compass.findduplicates;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import com.compass.findduplicates.finder.DuplicateFinder;
import com.compass.findduplicates.finder.FindDuplicateResult;
import com.compass.findduplicates.finder.impl.ContactDuplicateFinder;
import com.compass.findduplicates.model.Contact;
import com.compass.findduplicates.reader.InputFileReader;
import com.compass.findduplicates.reader.impl.ExcelFileReader;

/**
 * Application to read contacts from an Excel file and return a list of
 * duplicate contact Id's and the matching accuracy.
 */
public class Application {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("ERROR: Missing input file name.");
			System.exit(1);
		}

		String fileName = args[0];
		boolean debug = (args.length == 2 && args[1].equalsIgnoreCase("debug"));

		DuplicateFinder<Contact> finder = new ContactDuplicateFinder(debug);
		InputFileReader<Contact> reader = new ExcelFileReader(debug);

		int total = 0;
		Instant start = Instant.now();

		try {
			List<Contact> contacts = reader.read(fileName);
			List<FindDuplicateResult> results = finder.find(contacts);
			results.stream().forEach(System.out::println);
			total = results.size();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			System.exit(1);
		}
		
		Duration timeElapsed = Duration.between(start, Instant.now());
		System.out.println("Found " + total + " duplicates in " + timeElapsed.toMillis() + " milliseconds.");
	}
}
