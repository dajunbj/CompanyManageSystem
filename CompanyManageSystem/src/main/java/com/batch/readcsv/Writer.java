package com.batch.readcsv;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> messages) throws Exception {
        System.out.println("*****Writer 開始*****");
		for (String msg : messages) {
			System.out.println("Writing the data " + msg);
		}
        System.out.println("*****Writer 終了*****");
	}

}