package com.controller.base;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 新規コントローラ
 */
@Component
public class ControllerBase {

	/**
	 * メニューをクリックする
	 */
	@RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(Model model, @RequestParam String transitionTo) {
		// 画面データ初期化
		return "redirect:" + transitionTo;
	}

}