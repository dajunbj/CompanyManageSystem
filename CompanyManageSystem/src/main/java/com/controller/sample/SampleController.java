package com.controller.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.UrlConst;
import com.controller.base.ControllerBase;
import com.form.batch.SampleForm;
import com.mapper.common.CommonMapper;
import com.service.employee.EmployeeService;

/**
 * ログイン コントローラー
 */
@RestController
@RequestMapping(value = "/sample/sampleexecute")
public class SampleController extends ControllerBase {

	@Autowired
	EmployeeService userService;

	@Autowired
	CommonMapper commonMapper;

	public SampleController() {
	}

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {

		model.addAttribute("form", new SampleForm());
		return UrlConst.GOTO_SAMPLE;
	}

	/**
	 * CSV出力を実施する
	 */
	@RequestMapping(value = "/execute", params = "exportCsv", method = RequestMethod.POST)
	public String exportCsv(@ModelAttribute("form") SampleForm form, Model model) {
		System.out.println("画面入力：" + form.getBatchId());
		return UrlConst.GOTO_SAMPLE;
	}
	
}