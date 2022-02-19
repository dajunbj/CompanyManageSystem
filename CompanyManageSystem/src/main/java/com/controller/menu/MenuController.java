package com.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controller.base.ControllerBase;
import com.form.menu.MenuForm;
import com.service.employee.EmployeeService;

/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends ControllerBase {

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

}