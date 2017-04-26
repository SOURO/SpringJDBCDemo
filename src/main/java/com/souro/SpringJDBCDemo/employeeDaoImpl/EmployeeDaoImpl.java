package com.souro.SpringJDBCDemo.employeeDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.souro.SpringJDBCDemo.employee.Employee;
import com.souro.SpringJDBCDemo.employeeDao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insertEmployee(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB (EMPID, NAME) VALUES (?, ?)";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement prepstmt = connection
					.prepareStatement(insertQuery);
			prepstmt.setInt(1, employee.getEmpId());
			prepstmt.setString(2, employee.getName());
			prepstmt.executeUpdate();
			prepstmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	public Employee getEmployeebyEmpId(int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";
		Connection connection = null;
		Employee employee = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement prepstmt = connection
					.prepareStatement(retrieveQuery);
			prepstmt.setInt(1, empId);
			ResultSet rs = prepstmt.executeQuery();
			if (rs.next()) {
				employee = new Employee();
				employee.setEmpId(rs.getInt("EMPID"));
				employee.setName(rs.getString("NAME"));
			}
			rs.close();
			prepstmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		return employee;
	}

}
