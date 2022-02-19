package com.batch.readcsv;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
        System.out.println("*****Process 開始*****");
		return data.toUpperCase();
	}

}