package com.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.form.employee.EmployeeForm;
import com.mapper.common.CommonMapper;
import com.service.employee.EmployeeService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/employeeview")
public class EmployeeViewController extends ControllerBase {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeService userService;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedEmployeeId") String selectedEmployeeId) {

		EmployeeForm form = new EmployeeForm();
		form.setEmployeeId(selectedEmployeeId);

		EmployeeForm initForm = employeeService.readInit(form);
		model.addAttribute("employeeForm", initForm);

		return UrlConst.GOTO_USER_VIEW;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("employeeForm") EmployeeForm form, BindingResult result,
			Model model) {

		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
}