package com.souro.SpringJDBCDemo.employeeDao;

import com.souro.SpringJDBCDemo.employee.Employee;

public interface EmployeeDao {
	public void insertEmployee(Employee employee);
	public Employee getEmployeebyEmpId(int empId);
}
