package com.compass.findduplicates.finder.impl;

import java.util.ArrayList;
import java.util.List;

import com.compass.findduplicates.finder.Accuracy;
import com.compass.findduplicates.finder.DuplicateFinder;
import com.compass.findduplicates.finder.FindDuplicateResult;
import com.compass.findduplicates.model.Contact;

/**
 * Implementation class of {@code DuplicateFinder} interface, specialized in
 * {@code Contact} elements.
 */
public class ContactDuplicateFinder implements DuplicateFinder<Contact> {

	private boolean debug;
	
	public ContactDuplicateFinder(boolean debug) {
		super();
		this.debug = debug;
	}

	/**
	 * Returns a list of {@code FindDuplicateResult} elements containing the
	 * matching Id's and the accuracy. If the firstNane, lastName and one of email,
	 * zipCode or address are matched, the accuracy is LOW.
	 * <p>
	 * If the firstNane, lastName and two of email, zipCode or address are matched,
	 * the accuracy is HIGH.
	 * 
	 * @param list the list of contacts containing the possible duplicates.
	 * @return a list of {@code FindDuplicateResult} elements
	 */
	public List<FindDuplicateResult> find(List<Contact> contacts) {
		List<FindDuplicateResult> results = new ArrayList<>();

		if (contacts.size() > 1) {
			for (int i = 0; i < contacts.size() - 1; i++) {
				Contact source = contacts.get(i);

				for (int j = i + 1; j < contacts.size(); j++) {
					Contact other = contacts.get(j);
					int accuracy = 0;

					if (debug) {
						System.out.println(String.format("Comparing: %s to %s", source, other));
					}

					String sourceFirstName = source.getFirstName();
					String otherFirstName = other.getFirstName();
					String sourceLastName = source.getLastName();
					String otherLastName = other.getLastName();

					boolean match = sourceFirstName.equalsIgnoreCase(otherFirstName)
							|| (sourceFirstName.length() == 1 && otherFirstName.contains(sourceFirstName))
							|| (otherFirstName.length() == 1 && sourceFirstName.contains(otherFirstName));

					match = match && (sourceLastName.equalsIgnoreCase(otherLastName)
							|| (sourceLastName.length() == 1 && otherLastName.contains(sourceLastName))
							|| (otherLastName.length() == 1 && sourceLastName.contains(otherLastName)));

					// If there is a match, we need at least to match also one of email, postalZip
					// or address to be a Low accuracy match
					if (match) {
						String sourceEmail = source.getEmail();

						if (!sourceEmail.equals("")) {
							String otherEmail = other.getEmail();

							if (sourceEmail.equalsIgnoreCase(otherEmail)) {
								accuracy++;
							} else {
								String sourceUser = sourceEmail.split("@")[0];
								String otherUser = otherEmail.split("@")[0];

								if (sourceUser.equalsIgnoreCase(otherUser)) {
									accuracy++;
								}
							}
						}

						Integer sourceZipCode = source.getZipCode();
						if (sourceZipCode != 0 && sourceZipCode.equals(other.getZipCode())) {
							accuracy++;
						}

						String sourceAddress = source.getAddress();
						String otherAddress = other.getAddress();
						boolean matchingAddress = !sourceAddress.equals("")
								&& !otherAddress.equals("")
								&& (sourceAddress.equalsIgnoreCase(otherAddress) || sourceAddress.contains(otherAddress)
										|| otherAddress.contains(sourceAddress));

						if (matchingAddress) {
							accuracy++;
						}

						if (accuracy > 0) {
							FindDuplicateResult result = new FindDuplicateResult();
							result.setSourceId(source.getId());
							result.setMatchingId(other.getId());

							if (accuracy > 1) {
								result.setAccuracy(Accuracy.HIGH);
							} else {
								result.setAccuracy(Accuracy.LOW);
							}

							if (debug) {
								System.out.println("Found duplicate: " + result.toString());
							}

							results.add(result);
						}
					}
				}
			}
		}

		return results;
	}
}
