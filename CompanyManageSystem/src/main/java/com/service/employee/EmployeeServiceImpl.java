package com.service.employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.Utils.CmsUtils;
import com.Utils.ServiceUtils;
import com.common.Constant;
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
	HttpSession session;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm selectLoginInfo(EmployeeForm form) {

		// ログイン情報を検索する
		EmployeeInfoBean paramBean = new EmployeeInfoBean();
		paramBean.setLoginId(form.getLoginId());

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
		// ログイン情報を検索する
		EmployeeInfoBean bean = new EmployeeInfoBean();
		form.setResults(employeeInfoMapper.selectList(bean));

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm insertInit(EmployeeForm form) {
		// フォーム初期化
		form = initForm();

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public EmployeeForm editInit(EmployeeForm form) {

		// フォーム初期化
		EmployeeForm editForm = initForm();

		// ログイン情報を検索する
		EmployeeInfoBean sqlBean = new EmployeeInfoBean();
		sqlBean.setEmployeeId(form.getEmployeeId());

		List<EmployeeInfoBean> searchResults = employeeInfoMapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			EmployeeInfoBean result = searchResults.get(0);

			editForm.setEmployeeId(result.getEmployeeId()); // 社員ID
			editForm.setName(result.getName()); // 名前
			editForm.setSelectGender(result.getType()); // 社員区分
			editForm.setSelectedSexy(result.getSex()); // 性別
//			editForm.setBirthday(Date.valueOf(LocalDate.now()));    // 生年月日
			editForm.setAddress(result.getAddress()); // 住所
			editForm.setPhone(result.getPhone()); // 携帯
//			editForm.setJoiningDate(Date.valueOf(LocalDate.now())); // 入社年月日
			editForm.setMail(result.getMail()); // メール
			editForm.setJobType(result.getJobType()); // 職種
			editForm.setJobLevel(result.getJobLevel()); // 職種権限
			editForm.setLoginId(result.getMail()); // ログインID
		}

		return editForm;
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public EmployeeForm insert(EmployeeForm form) {

		// ログイン情報を検索する
		EmployeeInfoBean bean = new EmployeeInfoBean();

		List<EmployeeInfoBean> employeeList = employeeInfoMapper.select(bean);
		// 8桁不足の場合、前に０を埋める
		String id = String.format(Constant.FORMAT_EMPLOYEEID, employeeList.size() + 1);
		bean.setEmployeeId(id); // 社員ID
		bean.setName(form.getName()); // 名前
		bean.setType(form.getSelectGender()); // 社員区分
		bean.setSex(form.getSelectedSexy()); // 性別
		bean.setBirthday(Date.valueOf(LocalDate.now())); // 生年月日
		bean.setAddress(form.getAddress()); // 住所
		bean.setPhone(form.getPhone()); // 携帯
		bean.setJoiningDate(Date.valueOf(LocalDate.now()));// 入社年月日
		bean.setMail(form.getMail()); // メール
		bean.setJobType(form.getSelectedJobType()); // 職種
		bean.setJobLevel("職種権限"); // 職種権限
		bean.setLoginId(form.getMail()); // ログインID
		bean.setPassword("ogm000"); // パスワード
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

	/**
	 * ユーザー情報を初期化する
	 * 
	 * @return 社員フォーム
	 */
	private EmployeeForm initForm() {
		EmployeeForm form = new EmployeeForm();

		// 選択リスト{SEXY:性別}
		form.setSexyList(serviceUtils.setSelectList("SEXY"));
		form.setSelectedSexy("M");

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.setSelectList("JOB_TYPE"));
		form.setSelectedJobType("J5");// 項目【職種】のデフォルト値を社員に設定する

		// 社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		// 社員区分（ラジオボタンの選択値）
		form.setSelectGender("社員");

		return form;
	}

	/**
	 * 社員情報を更新する
	 * 
	 * @param form 社員フォーム
	 */
	public void update(EmployeeForm form) {
		// 画面から社員IDを取得する
		String employeeId = form.getEmployeeId();
		// ログイン情報を検索する
		EmployeeInfoBean bean = new EmployeeInfoBean();
		bean.setEmployeeId(employeeId);

		List<EmployeeInfoBean> employeeList = employeeInfoMapper.select(bean);
		EmployeeInfoBean updateBean = employeeList.get(0);

		// 8桁不足の場合、前に０を埋める
		updateBean.setEmployeeId(updateBean.getEmployeeId()); // 社員ID
		updateBean.setName(form.getName()); // 名前
		updateBean.setType(form.getSelectGender()); // 社員区分
		updateBean.setSex(form.getSelectedSexy()); // 性別
		updateBean.setBirthday(Date.valueOf(LocalDate.now())); // 生年月日
		updateBean.setAddress(form.getAddress()); // 住所
		updateBean.setPhone(form.getPhone()); // 携帯
		updateBean.setJoiningDate(Date.valueOf(LocalDate.now()));// 入社年月日
		updateBean.setMail(form.getMail()); // メール
		updateBean.setJobType(form.getSelectedJobType()); // 職種
		updateBean.setJobLevel("職種権限"); // 職種権限
		updateBean.setLoginId(form.getMail()); // ログインID
		updateBean.setPassword("ogm000"); // パスワード
		updateBean.setCompanyId((String) session.getAttribute("companyId"));
		employeeInfoMapper.update(updateBean);
	}

	/**
	 * 社員情報を初期化する
	 * 
	 * @param form 社員フォーム
	 * @return 社員フォーム
	 */
	public EmployeeForm readInit(EmployeeForm form) {
		// フォーム初期化
		EmployeeForm viewForm = initForm();

		// ログイン情報を検索する
		EmployeeInfoBean sqlBean = new EmployeeInfoBean();
		sqlBean.setEmployeeId(form.getEmployeeId());

		List<EmployeeInfoBean> searchResults = employeeInfoMapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			EmployeeInfoBean result = searchResults.get(0);

			viewForm.setEmployeeId(result.getEmployeeId()); // 社員ID
			viewForm.setName(result.getName()); // 名前
			viewForm.setSelectGender(result.getType()); // 社員区分
			viewForm.setSelectedSexy(result.getSex()); // 性別
//			viewForm.setBirthday(Date.valueOf(LocalDate.now()));    // 生年月日
			viewForm.setAddress(result.getAddress()); // 住所
			viewForm.setPhone(result.getPhone()); // 携帯
//			viewForm.setJoiningDate(Date.valueOf(LocalDate.now())); // 入社年月日
			viewForm.setMail(result.getMail()); // メール
			viewForm.setJobType(result.getJobType()); // 職種
			viewForm.setJobLevel(result.getJobLevel()); // 職種権限
			viewForm.setLoginId(result.getMail()); // ログインID
		}

		return viewForm;
	}
}