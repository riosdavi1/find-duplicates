package com.compass.findduplicates.finder.impl;

import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.compass.findduplicates.finder.Accuracy;
import com.compass.findduplicates.finder.FindDuplicateResult;
import com.compass.findduplicates.model.Contact;

public class ContactDuplicateFinderTest {

	ContactDuplicateFinder finder;
	
	@BeforeEach
	public void setUp() {
		finder = new ContactDuplicateFinder(false);
	}
	
	@Test
	public void testFindWithEmptyList() {
		List<Contact> contacts = Lists.newArrayList();
		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(0)));
	}

	@Test
	public void testFindWithSingleContact() {
		List<Contact> contacts = Lists.newArrayList();
		contacts.add(new Contact());
		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(0)));
	}

	@Test
	public void testFindWithDuplicateContactsSameEmailLowAccuracy() {
		List<Contact> contacts = Lists.newArrayList();

		Contact contact = new Contact();
		contact.setId(1);
		contact.setFirstName("C");
		contact.setLastName("Smith");
		contact.setEmail("csmith@gmail.com");
		contact.setZipCode(0);
		contact.setAddress("");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(2);
		contact.setFirstName("John");
		contact.setLastName("Black");
		contact.setEmail("john.black@mail.com");
		contact.setZipCode(53662);
		contact.setAddress("P.O. Box 697, 6982 Nunc Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(3);
		contact.setFirstName("Conrad");
		contact.setLastName("S");
		contact.setEmail("csmith@hotmail.com");
		contact.setZipCode(37248);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(1)));
		
		FindDuplicateResult result = results.get(0);
		assertThat("Source Id", result.getSourceId(), is(equalTo(1)));
		assertThat("Matching Id", result.getMatchingId(), is(equalTo(3)));
		assertThat("Accuracy", result.getAccuracy(), is(equalTo(Accuracy.LOW)));
	}

	@Test
	public void testFindWithDuplicateContactsSameEmailAndZipCodeHighAccuracy() {
		List<Contact> contacts = Lists.newArrayList();

		Contact contact = new Contact();
		contact.setId(1);
		contact.setFirstName("C");
		contact.setLastName("Smith");
		contact.setEmail("csmith@gmail.com");
		contact.setZipCode(37248);
		contact.setAddress("");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(2);
		contact.setFirstName("John");
		contact.setLastName("Black");
		contact.setEmail("john.black@mail.com");
		contact.setZipCode(53662);
		contact.setAddress("P.O. Box 697, 6982 Nunc Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(3);
		contact.setFirstName("Conrad");
		contact.setLastName("S");
		contact.setEmail("csmith@hotmail.com");
		contact.setZipCode(37248);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(1)));
		
		FindDuplicateResult result = results.get(0);
		assertThat("Source Id", result.getSourceId(), is(equalTo(1)));
		assertThat("Matching Id", result.getMatchingId(), is(equalTo(3)));
		assertThat("Accuracy", result.getAccuracy(), is(equalTo(Accuracy.HIGH)));
	}

	@Test
	public void testFindWithDuplicateContactsSameEmailAndAddressHighAccuracy() {
		List<Contact> contacts = Lists.newArrayList();

		Contact contact = new Contact();
		contact.setId(1);
		contact.setFirstName("C");
		contact.setLastName("Smith");
		contact.setEmail("csmith@gmail.com");
		contact.setZipCode(0);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(2);
		contact.setFirstName("John");
		contact.setLastName("Black");
		contact.setEmail("john.black@mail.com");
		contact.setZipCode(53662);
		contact.setAddress("P.O. Box 697, 6982 Nunc Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(3);
		contact.setFirstName("Conrad");
		contact.setLastName("S");
		contact.setEmail("csmith@hotmail.com");
		contact.setZipCode(37248);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(1)));
		
		FindDuplicateResult result = results.get(0);
		assertThat("Source Id", result.getSourceId(), is(equalTo(1)));
		assertThat("Matching Id", result.getMatchingId(), is(equalTo(3)));
		assertThat("Accuracy", result.getAccuracy(), is(equalTo(Accuracy.HIGH)));
	}

	@Test
	public void testFindWithDuplicateContactsSameZipCodeAndAddressHighAccuracy() {
		List<Contact> contacts = Lists.newArrayList();

		Contact contact = new Contact();
		contact.setId(1);
		contact.setFirstName("C");
		contact.setLastName("Smith");
		contact.setEmail("csmith@gmail.com");
		contact.setZipCode(37248);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(2);
		contact.setFirstName("John");
		contact.setLastName("Black");
		contact.setEmail("john.black@mail.com");
		contact.setZipCode(53662);
		contact.setAddress("P.O. Box 697, 6982 Nunc Rd.");
		contacts.add(contact);

		contact = new Contact();
		contact.setId(3);
		contact.setFirstName("Conrad");
		contact.setLastName("S");
		contact.setEmail("conrad.smith@hotmail.com");
		contact.setZipCode(37248);
		contact.setAddress("555-2685 Scelerisque Rd.");
		contacts.add(contact);

		List<FindDuplicateResult> results = finder.find(contacts);
		assertThat("Results len", results.size(), is(equalTo(1)));
		
		FindDuplicateResult result = results.get(0);
		assertThat("Source Id", result.getSourceId(), is(equalTo(1)));
		assertThat("Matching Id", result.getMatchingId(), is(equalTo(3)));
		assertThat("Accuracy", result.getAccuracy(), is(equalTo(Accuracy.HIGH)));
	}
}
