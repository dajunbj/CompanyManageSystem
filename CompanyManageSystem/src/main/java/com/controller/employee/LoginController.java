package com.controller.employee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.employee.EmployeeInfoBean;
import com.form.employee.EmployeeForm;
import com.service.employee.EmployeeService;

/**
 * ログイン コントローラー
 */
@Controller
public class LoginController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	HttpSession session;

	/**
	 * ユーザー情報検索画面を表示
	 * 
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/login")
	public String init(Model model) {

		EmployeeForm form = new EmployeeForm();
		model.addAttribute("userForm", form);

		return "login";
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param userSearchRequest リクエストデータ
	 * @param model             Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/login", params = "gotoMenu", method = RequestMethod.POST)
	public String gotoMenu(@ModelAttribute EmployeeForm form, Model model) {

		EmployeeInfoBean requestBean = new EmployeeInfoBean();
		requestBean.setLoginId(form.getLoginId());
		requestBean.setPassword(form.getPassword());

		EmployeeForm formChk = employeeService.selectLoginInfo(form);
		if (formChk.getResults() != null && formChk.getResults().size() > 0) {
			session.setAttribute("employeeId", formChk.getEmployeeId());	
			session.setAttribute("companyId", formChk.getCompanyId());
			return "menu";
		} else {
			model.addAttribute("errors", "ユーザまたはパスワードが正しくありません");
			model.addAttribute("userForm", form);

			return "redirect:login";
		}

	}
}