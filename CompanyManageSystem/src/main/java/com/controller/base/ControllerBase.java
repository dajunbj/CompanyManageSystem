package com.controller.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.temp.TestLog;

/**
 * 新規コントローラ
 */
@Component
public class ControllerBase {

	public static final Logger logger = LogManager.getLogger(TestLog.class);

	/**
	 * メニューをクリックする
	 */
	@RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(Model model, @RequestParam String transitionTo) {
		// 画面データ初期化
		return "redirect:" + transitionTo;
	}

}