package com.temp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {

    private static final Logger logger = LogManager.getLogger(TestLog.class);
	public static void main(String[] args) {

		logger.debug("debugメッセージ");
		logger.info("infoメッセージ");
		logger.error("errorメッセージ");
		logger.warn("warnメッセージ");
		logger.trace("traceメッセージ");
	}

}
