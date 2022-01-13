package com.form.employee;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.entity.common.MCodeMstBean;
import com.entity.employee.EmployeeInfoBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
@Data
public class EmployeeForm {

	private static final long serialVersionUID = 1L;
	/* 検索欄の項目 */
	private List<MCodeMstBean> codeList;

	/* 検索条件＆登録情報 */
	private EmployeeInfoBean record;

	/* 検索結果 */
	private List<EmployeeInfoBean> results;

	@NotEmpty
	private String employeeId;

	@NotEmpty
	private String name;
	private String type;

	//職種選択リスト
	@NotEmpty
	private String selectedJobType;
	private Map<String, String> jobTypeList;

	//性別選択リスト
	@NotEmpty
	private String selectedSexy;
	private Map<String, String> sexyList;
	
	@NotEmpty
	private String birthday;

	@NotEmpty
	private String address;
	private String phone;

	@NotEmpty
	private String joiningDate;

	@Email
	private String mail;
	private String jobType;
	private String jobLevel;
	private String loginId;
	private String password;
	private Date registrationDate;
	private Date updateDate;
	private String companyId;

}