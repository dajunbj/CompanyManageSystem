package com.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Utils.CmsUtils;
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
public class EmployeeListController extends ControllerBase {
	private List<EmployeeInfoBean> lst;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CommonMapper commonMapper;

	public EmployeeListController() {
	}

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		EmployeeForm form = new EmployeeForm();

		// 社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		// 社員区分の初期値を設定する
		form.setSelectGender("社員");

		model.addAttribute("form", form);

		return UrlConst.GOTO_USER_LIST;
	}

	/**
	 * メニュー画面初期化
	 */
	@RequestMapping(params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("employeeForm") EmployeeForm form, Model model) {
		// 画面データ初期化
		// 検索実施
		EmployeeForm searchResultform = employeeService.select(form);

		// 社員区分（ラジオボタン）
		searchResultform.setGenders(CmsUtils.createGenders());

		model.addAttribute("form", searchResultform);
		return UrlConst.GOTO_USER_LIST;
	}

	/**
	 * 新規ボタン
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	public String add(Model model) {

		return UrlConst.GOTO_USER_ADD_REDIRECT;
	}

	/**
	 * 参照ボタン
	 */
	@RequestMapping(params = "read", method = RequestMethod.POST)
	public String read(RedirectAttributes redirectAttributes, @RequestParam String read) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", read);
		return UrlConst.GOTO_USER_VIEW_REDIRECT;

	}

	/**
	 * 更新ボタン
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @RequestParam String update) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", update);
		return UrlConst.GOTO_USER_EIDT_REDIRECT;

	}

	/**
	 * 削除ボタン
	 */
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public String delete(RedirectAttributes redirectAttributes, @RequestParam String delete) {

		EmployeeForm form = new EmployeeForm();
		form.setEmployeeId(delete);
		employeeService.delete(form);

		return UrlConst.GOTO_USER_LIST_REDIRECT;

	}

	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(params = "createInvoice", method = RequestMethod.POST)
	public String createPdf(Model model) {
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");

		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");

		return UrlConst.GOTO_USER_LIST;
	}

}