package com.service.employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.Utils.GeneralMstUtils;
import com.entity.employee.EmployeeInfoBean;
import com.form.employee.EmployeeForm;
import com.mapper.common.CommonMapper;
import com.mapper.employee.EmployeeInfoMapper;

/**
 * ユーザー情報 Service
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	/****** Mapper ******/
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;
	@Autowired
	CommonMapper commonMapper;

	@Autowired
	GeneralMstUtils utils;

	@Autowired
	HttpSession session;

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm selectLoginInfo(EmployeeForm form) {

		// ログイン情報を検索する
		EmployeeInfoBean paramBean = new EmployeeInfoBean();
		paramBean.setMail(form.getMail());

		List<EmployeeInfoBean> searchResults = employeeInfoMapper.select(paramBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			EmployeeInfoBean result = searchResults.get(0);

			form.setCompanyId(result.getCompanyId());
			form.setEmployeeId(result.getEmployeeId());
			form.setResults(searchResults);
		}

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm select(EmployeeForm form) {
		form = new EmployeeForm();
		// ログイン情報を検索する
		EmployeeInfoBean bean = new EmployeeInfoBean();

		form.setResults(employeeInfoMapper.select(bean));

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm init(EmployeeForm form) {
		form = new EmployeeForm();

		// 選択リスト{SEXY:性別}
		form.setSexyList(utils.setSelectList("SEXY"));
		form.setSelectedSexy("F");

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(utils.setSelectList("JOB_TYPE"));
		form.setSelectedJobType("J5");

		return form;
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public EmployeeForm add(EmployeeForm form) {

		System.out.println();
		EmployeeInfoBean bean = new EmployeeInfoBean();

		bean.setEmployeeId(form.getEmployeeId());// 社員ID
		bean.setName(form.getName());// 名前
		bean.setType(form.getType());// 社員区分
		bean.setSex(form.getSelectedSexy()); // 性別
		bean.setBirthday(Date.valueOf(LocalDate.now()));// 生年月日
		bean.setAddress(form.getAddress());// 住所
		bean.setPhone(form.getPhone()); // 携帯
		bean.setJoiningDate(Date.valueOf(LocalDate.now()));// 入社年月日
		bean.setMail(form.getMail());// メール
		bean.setJobType(form.getSelectedJobType());// 職種
		bean.setJobLevel(form.getJobLevel());// 職種権限
		bean.setLoginId(form.getMail());// ログインID
		bean.setPassword("ogm000");// パスワード
		bean.setCompanyId((String) session.getAttribute("companyId"));
		employeeInfoMapper.insert(bean);

		return form;
	}

	/**
	 * ユーザー情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public EmployeeForm delete(EmployeeForm form) {

		EmployeeInfoBean bean = new EmployeeInfoBean();
		bean.setEmployeeId(form.getEmployeeId());// 社員ID
		
		employeeInfoMapper.delete(bean);
		
		return selectLoginInfo(form);
	}
}