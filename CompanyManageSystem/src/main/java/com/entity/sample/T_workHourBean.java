package com.entity.sample;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "t_workHour")
public class T_workHourBean {

	@Id
	@Column(name = "employeeId")
	private String employeeId;


	@Id
	@Column(name = "workMonth")
	private String workMonth;


	@Id
	@Column(name = "workDay")
	private Timestamp workDay;
	
	@Column(name = "startTime")
	private Timestamp startTime;
	
	@Column(name = "endTime")
	private Timestamp endTime;
	
	@Column(name = "workHous")
	private int workHous;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "registrationDate")
	private Timestamp registrationDate;
	
	@Column(name = "updateDate")
	private Timestamp updateDate;

}
