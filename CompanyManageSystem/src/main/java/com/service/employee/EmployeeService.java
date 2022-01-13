package com.service.employee;

import com.form.employee.EmployeeForm;

public interface EmployeeService {

	public EmployeeForm selectLoginInfo(EmployeeForm form);

	public EmployeeForm select(EmployeeForm form);

	public EmployeeForm init(EmployeeForm form);

	public EmployeeForm add(EmployeeForm form);

	public EmployeeForm delete(EmployeeForm form);
}
