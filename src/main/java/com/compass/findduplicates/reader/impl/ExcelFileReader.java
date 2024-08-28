package com.compass.findduplicates.reader.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.compress.utils.Lists;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.compass.findduplicates.model.Contact;
import com.compass.findduplicates.reader.InputFileReader;

/**
 * An Excel file reader that returns the file content as a list of
 * {@code Contact} elements.
 */
public class ExcelFileReader implements InputFileReader<Contact> {

	private static final int LAST_COL_NBR = 6;
	private boolean debug;

	public ExcelFileReader(boolean debug) {
		super();
		this.debug = debug;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contact> read(String fileName) throws Exception {
		List<Contact> contacts = Lists.newArrayList();

		try (InputStream is = new FileInputStream(fileName);
				XSSFWorkbook workBook = new XSSFWorkbook(OPCPackage.open(is))) {
			Sheet sheet = workBook.getSheetAt(0);

			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum < 0) {
				throw new Exception("Couldn't get contacts from Excel file: Input file is empty.");
			}

			// Row #0 contains headers so it must be skipped.
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				if (row.getLastCellNum() < LAST_COL_NBR) {
					throw new Exception("Couldn't get contacts from Excel file: Invalid file content.");
				}

				int id = this.getNumericValue(row, 0, "contactId");
				if (id == 0) {
					break;
				}

				Contact contact = new Contact();
				// contactId is guaranteed to be not empty.
				contact.setId(id);
				contact.setFirstName(row.getCell(1).getStringCellValue());
				contact.setLastName(row.getCell(2).getStringCellValue());
				contact.setEmail(row.getCell(3).getStringCellValue());
				contact.setEmail(row.getCell(3).getStringCellValue());
				// if zipCode is empty, 0 is assigned.
				contact.setZipCode(this.getNumericValue(row, 4, "zipCode"));
				contact.setAddress(row.getCell(5).getStringCellValue());

				if (debug) {
					System.out.println("Adding contact Id: " + contact.getId());
				}

				contacts.add(contact);
			}
		}
		return contacts;
	}

	private int getNumericValue(Row row, int colIndex, String colName) throws Exception {
		try {
			Double d = row.getCell(colIndex).getNumericCellValue();
			return d.intValue();
		} catch (NumberFormatException e) {
			throw new Exception(colName + " is not a number.");
		}
	}
}
