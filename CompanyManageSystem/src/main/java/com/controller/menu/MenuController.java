package com.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.menu.MenuForm;
import com.service.employee.EmployeeService;

/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	EmployeeService userService;

	/**
	 * ユーザー情報検索
	 * 
	 * @param userSearchRequest リクエストデータ
	 * @param model             Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "")
	public String init(Model model) {
		MenuForm form = new MenuForm();
		model.addAttribute("form", form);

		return "redirect:menu";
	}

	@RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(@RequestParam String transitionTo, Model model) {

		return "redirect:"+transitionTo;
	}

}