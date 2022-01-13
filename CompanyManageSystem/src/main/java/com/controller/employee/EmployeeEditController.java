package com.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(value = "/employee/employeeedit")
public class EmployeeEditController implements ControllerBase {
	private List<EmployeeInfoBean> lst;

	@Autowired
	EmployeeService userService;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	private String init(Model model) {

		return UrlConst.GOTO_USER_EIDT;
	}
	
	/**
	 * メニュークリック
	 */
	@RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(@ModelAttribute EmployeeForm form, BindingResult bindingResult, Model model, @RequestParam String transitionTo) {
		// 画面データ初期化
	

		return transitionTo;
	}

	/**
	 * 請求書作成を行う
	 */
	@PostMapping(value = "", params = "createInvoice")
	public String createPdf(Model model) {
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");

		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");
		return UrlConst.GOTO_USER_LIST;
	}

}