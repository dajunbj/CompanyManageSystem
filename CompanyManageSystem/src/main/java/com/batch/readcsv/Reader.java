package com.batch.readcsv;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

	private String[] messages = { "javainuse.com", "Welcome to Spring Batch Example",
			"We use H2 Database for this example" };

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("*****Read 開始*****");

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		System.out.println("*****Read 終了*****");
		return null;
	}

}