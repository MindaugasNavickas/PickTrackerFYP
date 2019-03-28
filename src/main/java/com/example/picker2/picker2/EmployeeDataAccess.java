package com.example.picker2.picker2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataAccess {
//	 worker_password
	private Connection connection;
	private String sql = "SELECT * FROM workersLogin WHERE worker_login = '";
	private String sql2 = "' AND worker_password = '";

	public EmployeeDataAccess(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(dbURL, user, password);
				System.out.println("trying to connecto to the server");

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void shutdown() throws SQLException {
		if (connection != null) {
			System.out.println("closing connection");
			connection.close();
		}
	}

	public List<Employee> getEmployeeList(String workerLogin, String workerPassword) throws SQLException {
		System.out.println(sql + workerLogin + sql2 + workerPassword + "';");
		try (Statement statement = connection.createStatement();
				ResultSet rSet = statement.executeQuery(sql + workerLogin + sql2 + workerPassword + "';");) {
			List<Employee> employeeList = new ArrayList<>();
			while (rSet.next()) {
				String userName = rSet.getString("worker_login");
				String password = rSet.getString("worker_password");
				String userID = rSet.getString("worker_ID");
				String userRights = rSet.getString("worker_Access_Rights");
				Employee emp = new Employee(userID, userName, password, userRights);
				employeeList.add(emp);
//				System.out.println(employeeList);
			}
			return employeeList;

		}
	}

}
