package com.temp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {

	private static final Logger logger = LogManager.getLogger(TestLog.class);

	public static void main(String[] args) {

//		logger.debug("debugメッセージ");
//		logger.info("infoメッセージ");
//		logger.error("errorメッセージ");
//		logger.warn("warnメッセージ");
//		logger.trace("traceメッセージ");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String dateInString = "1988-12-12";
		try {
			Date date = formatter.parse(dateInString);
			System.out.println(date);
			System.out.println(formatter.format(date));
		} catch (Exception e) {

		}
	}

}
