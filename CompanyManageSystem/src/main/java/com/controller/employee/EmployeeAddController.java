package com.controller.employee;

import javax.validation.Valid;

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
import com.service.employee.EmployeeService;
import com.utils.CmsUtils;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/employeeadd")
public class EmployeeAddController extends ControllerBase {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * 社員登録画面を初期化する
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {

		EmployeeForm initForm = employeeService.insertInit(new EmployeeForm());
		model.addAttribute("employeeForm", initForm);

		return UrlConst.GOTO_USER_ADD;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("employeeForm") @Valid EmployeeForm form, BindingResult result, Model model) {
		// 選択リスト{SEXY:性別}
		form.setSexyList(serviceUtils.setSelectList("SEXY"));

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.setSelectList("JOB_TYPE"));
		
		//社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		
		if (result.hasErrors()) {

			model.addAttribute("employeeForm", form);
			return null;
		}

		employeeService.insert(form);

		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("employeeForm") EmployeeForm form, BindingResult result, Model model) {

		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
	
}