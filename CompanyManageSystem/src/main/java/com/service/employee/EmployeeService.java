package com.service.employee;

import com.form.employee.EmployeeForm;

public interface EmployeeService {

	public EmployeeForm selectLoginInfo(EmployeeForm form);

	/**
	 * 一覧画面
	 */	
	//一覧画面検索
	public EmployeeForm select(EmployeeForm form);
	//一覧画面【削除】
	public EmployeeForm delete(EmployeeForm form);
	
	/**
	 * 新規画面
	 */
	//新規画面【初期化】
	public EmployeeForm insertInit(EmployeeForm form);
	//新規画面【データ保存】
	public EmployeeForm insert(EmployeeForm form);
	
	/**
	 * 更新画面
	 */	
	//更新画面【初期化】
	public EmployeeForm editInit(EmployeeForm form);
	//更新画面【データ保存】	
	public void update(EmployeeForm form);

	/**
	 * 参照画面
	 */	
	//参照画面【初期化】
	public EmployeeForm readInit(EmployeeForm form);
}
