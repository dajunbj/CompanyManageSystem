package com.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Utils.PdfUtil;
import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.entity.employee.EmployeeInfoBean;
import com.form.employee.EmployeeForm;
import com.mapper.common.CommonMapper;
import com.service.employee.EmployeeService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/employeelist")
public class EmployeeListController implements ControllerBase {
	private List<EmployeeInfoBean> lst;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CommonMapper commonMapper;

	private String testVal;

	public EmployeeListController() {
		testVal = "hello";
	}

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new EmployeeForm());
		return UrlConst.GOTO_USER_LIST;
	}

	/**
	 * メニュークリック
	 */
	@RequestMapping(value = "/execute", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(@ModelAttribute("employeeForm") EmployeeForm form, Model model,
			@RequestParam String transitionTo) {
		// 画面データ初期化

		return transitionTo;
	}

	/**
	 * メニュー画面初期化
	 */
	@RequestMapping(value = "/execute", params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("employeeForm") EmployeeForm form, Model model) {
		// 画面データ初期化
		// 検索実施
		form = employeeService.select(null);

		model.addAttribute("dataForm", form);
		return UrlConst.GOTO_USER_LIST;
	}

	/**
	 * 新規ボタン
	 */
	@RequestMapping(value = "/execute", params = "add", method = RequestMethod.POST)
	public String add(Model model) {

		return "redirect:" + UrlConst.GOTO_USER_ADD;
	}
	
	/**
	 * 参照ボタン
	 */
	@RequestMapping(value = "/execute", params = "read", method = RequestMethod.POST)
	public String read(Model model) {
		return "redirect:" +UrlConst.GOTO_USER_VIEW;
	}

	/**
	 * 更新ボタン
	 */
	@RequestMapping(value = "/execute", params = "update", method = RequestMethod.POST)
	public String update(Model model) {
		return "redirect:" +UrlConst.GOTO_USER_EIDT;
	}

	/**
	 * 削除ボタン
	 */
	@RequestMapping(value = "/execute", params = "delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("employeeForm") EmployeeForm form, Model model) {
		employeeService.delete(form);
		return UrlConst.GOTO_USER_LIST;
	}

	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(value = "/execute", params = "createInvoice", method = RequestMethod.POST)
	public String createPdf(Model model) {
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");

		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");
		
		return UrlConst.GOTO_USER_LIST;
	}

}