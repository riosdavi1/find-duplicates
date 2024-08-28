# Find-Duplicates


## Getting started

This application reads contacts from an Excel file and returns a list of duplicate contact Id's and the matching accuracy.
The accuracy is based on the following criteria:
* If the firstNane, lastName and one of email, zipCode or address are matched, the accuracy is LOW.
* If the firstNane, lastName and two of email, zipCode or address are matched, the accuracy is HIGH. 
 
> **Important**: the complexity is O(n^2) because contacts cannot be sorted on any combination of fields that can guarantee that duplicate contacts are adjacent (zipCode and address can be empty and email can be different for duplicate contacts).


## Build application

```sh
./gradlew clean build
```

## Run tests

```sh
./gradlew clean test
```

Sample output:

```
ContactDuplicateFinderTest > testFindWithSingleContact() PASSED
ContactDuplicateFinderTest > testFindWithDuplicateContactsSameEmailAndAddressHighAccuracy() PASSED
ContactDuplicateFinderTest > testFindWithEmptyList() PASSED
ContactDuplicateFinderTest > testFindWithDuplicateContactsSameEmailAndZipCodeHighAccuracy() PASSED
ContactDuplicateFinderTest > testFindWithDuplicateContactsSameZipCodeAndAddressHighAccuracy() PASSED
ContactDuplicateFinderTest > testFindWithDuplicateContactsSameEmailLowAccuracy() PASSED
...
BUILD SUCCESSFUL in 4s
```

## Run application

To execute the application, pass the location and name of the input Excel file. Example:

```sh
./gradlew run --args='"C:\Users\riosd\mydev\compass\Code Assessment - Find Duplicates Input (2).xlsx"'
```

Sample output:

```
FindResult [sourceId=1, matchingId=501, accuracy=HIGH]
FindResult [sourceId=2, matchingId=502, accuracy=HIGH]
FindResult [sourceId=3, matchingId=503, accuracy=HIGH]
FindResult [sourceId=4, matchingId=504, accuracy=HIGH]
```

To see the progress, pass `debug` as second argument:

```sh
./gradlew run --args='"C:\Users\riosd\mydev\compass\Code Assessment - Find Duplicates Input (2).xlsx" debug'
```

Sample debug output:

```
Adding contact Id: 1
Adding contact Id: 2
Adding contact Id: 3
Adding contact Id: 4
Adding contact Id: 5
Adding contact Id: 6
...
Comparing: Contact [id=1, firstName=Ciara, lastName=French, email=mollis.lectus.pede@outlook.net, zipCode=39746, address=449-6990 Tellus. Rd.] to Contact [id=2, firstName=Charles, lastName=Pacheco, email=nulla.eget@protonmail.couk, zipCode=76837, address=Ap #312-8611 Lacus. Ave]
Comparing: Contact [id=1, firstName=Ciara, lastName=French, email=mollis.lectus.pede@outlook.net, zipCode=39746, address=449-6990 Tellus. Rd.] to Contact [id=3, firstName=Victor, lastName=Savage, email=orci@protonmail.net, zipCode=82025, address=P.O. Box 775, 8910 Arcu. Road]
Comparing: Contact [id=1, firstName=Ciara, lastName=French, email=mollis.lectus.pede@outlook.net, zipCode=39746, address=449-6990 Tellus. Rd.] to Contact [id=4, firstName=Paul, lastName=Gaines, email=quis.diam@aol.couk, zipCode=95904, address=735-3498 Magna. Street]
...
```
