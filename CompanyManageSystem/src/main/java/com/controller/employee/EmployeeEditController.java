package com.controller.employee;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.Gender;
import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.form.employee.EmployeeForm;
import com.mapper.common.CommonMapper;
import com.service.employee.EmployeeService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/employeeedit")
public class EmployeeEditController extends ControllerBase {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedEmployeeId") String selectedEmployeeId) {

		EmployeeForm form = new EmployeeForm();
		form.setEmployeeId(selectedEmployeeId);

		EmployeeForm initForm = employeeService.editInit(form);
		model.addAttribute("employeeForm", initForm);

		return UrlConst.GOTO_USER_EIDT;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("employeeForm") @Valid EmployeeForm form, BindingResult result, Model model) {
		// 選択リスト{SEXY:性別}
		form.setSexyList(serviceUtils.setSelectList("SEXY"));

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.setSelectList("JOB_TYPE"));

		// 社員区分（ラジオボタン）
		List<Gender> genders = new ArrayList<Gender>() {
			{
				add(Gender.of("radioKbn", "社員", "社員"));
				add(Gender.of("radioKbn", "BP", "BP"));
			}
		};
		form.setGenders(genders);

		if (result.hasErrors()) {

			model.addAttribute("employeeForm", form);
			return null;
		}

		employeeService.update(form);

		return UrlConst.GOTO_USER_LIST_REDIRECT;
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