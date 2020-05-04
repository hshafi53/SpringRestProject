package com.websystique.springmvc.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class GenerateTxnDtls {
	public void processProcessToCots(Map<String, Object> cotsMap) throws Exception {

		System.out.println("Inside processProcessToCots()");

		boolean cFlag = false;
		GregorianCalendar xmlDate = new GregorianCalendar();
		xmlDate.setTime(new Date());
		XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(xmlDate.get(Calendar.YEAR),
				xmlDate.get(Calendar.MONTH) + 1, xmlDate.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

		System.out.println("XMLGregorianCalendar in processcots " + cal + "----");

		StringBuilder sb = new StringBuilder();
		String headerLine = "NTXN" + cotsMap.get("custid") + cotsMap.get("custname") + cal.toString().replace("-", "");

		Long citiRefNo = (Long) cotsMap.get("transref");

		String invoiceLine = citiRefNo + "NEWTXN" + cotsMap.get("itemname") + "     " + cotsMap.get("quantity")
				+ "     " + cotsMap.get("price") + "     ";
		sb.append(headerLine);
		sb.append("\n");
		sb.append(invoiceLine);
		FileInputStream fis = null;
		System.out.println("sb after writing--------------" + sb);

		System.out.println("invoiceline----------------" + invoiceLine);
		try {
			File cotsFile = createFileUsingFileClass(sb, invoiceLine);

			System.out.println("Generated file name" + cotsFile.getName());
		} catch (IOException e) {
			e.getMessage();

		} finally {
			if (fis != null)
				fis.close();
		}

	}

	private static File createFileUsingFileClass(StringBuilder sb, String invoiceLine) throws Exception {

		StringBuffer buf = new StringBuffer();

		String filetimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
		String backupPath = "E:\\Oracle\\Project_FSGBU\\Payment_HDFC";

		buf.append(backupPath);
		buf.append("cots");
		buf.append("-");
		buf.append("NewTxnDTLS");
		buf.append(".txt");
		buf.append("_");
		buf.append(filetimeStamp);
		String actualfile = buf.toString().replace(".", "_");
		File file = new File(actualfile);

		if (file.createNewFile()) {

			System.out.println("File is created!" + file.getName());

		} else {
			System.out.println("File already exists.");

			file.delete();
			if (file.createNewFile()) {
				System.out.println("File deleted and created!");

			}

		}

		FileWriter writer = new FileWriter(file);
		int count = 1;
		try {

			sb.append(invoiceLine);
			sb.append("\n");

			count++;

			writer.write(sb.toString());

		} catch (Exception e) {
			e.getMessage();
		} finally {
			writer.close();
		}
		return file;
	}

}
