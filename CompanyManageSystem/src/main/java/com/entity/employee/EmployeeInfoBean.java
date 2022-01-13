package com.entity.employee;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "employee_info")
public class EmployeeInfoBean {
	
	@Id
	@Column(name = "employeeId")
	private String employeeId;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "sex")
	private String sex;
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "address")
	private String address;
	@Column(name = "phone")
	private String phone;
	@Column(name = "joiningDate")
	private Date joiningDate;
	@Column(name = "mail")
	private String mail;
	@Column(name = "jobType")
	private String jobType;
	@Column(name = "jobLevel")
	private String jobLevel;
	@Column(name = "loginId")
	private String loginId;
	@Column(name = "password")
	private String password;
	@Column(name = "registrationDate")
	private Date registrationDate;
	@Column(name = "updateDate")
	private Date updateDate;
	@Column(name = "companyId")
	private String companyId;
}
