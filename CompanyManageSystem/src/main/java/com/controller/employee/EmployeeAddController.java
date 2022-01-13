package com.controller.employee;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Utils.GeneralMstUtils;
import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.form.employee.EmployeeForm;
import com.service.employee.EmployeeService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/employeeadd")
public class EmployeeAddController implements ControllerBase {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	GeneralMstUtils utils;

	@RequestMapping(value = "", method = RequestMethod.GET)
	private String init(Model model) {

		EmployeeForm initForm = employeeService.init(new EmployeeForm());
		model.addAttribute("employeeForm", initForm);

		return UrlConst.GOTO_USER_ADD;
	}

	/**
	 * メニュークリック
	 */
	@RequestMapping(value = "/execute", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(Model model, @RequestParam String transitionTo) {
		// 画面データ初期化
		return transitionTo;
	}

	@RequestMapping(value = "/execute", params = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("employeeForm") @Valid EmployeeForm form, BindingResult result, Model model) {
		// 選択リスト{SEXY:性別}
		form.setSexyList(utils.setSelectList("SEXY"));

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(utils.setSelectList("JOB_TYPE"));

		if (result.hasErrors()) {
			return UrlConst.GOTO_USER_ADD;
		}

		employeeService.add(form);

		return UrlConst.GOTO_USER_LIST;
	}

}