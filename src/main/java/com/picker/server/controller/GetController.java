package com.picker.server.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.picker.server.Employee;
import com.picker.server.EmployeeDataAccess;

@RestController
public class GetController {
	private final String dbURL = "jdbc:mysql://localhost:3306/picksDatabase";
	private final String user = "root";
	private final String password = "Mindziukas1234";

	@RequestMapping(method = RequestMethod.GET, value = "/userLogin", produces = "application/json")	//requests mapping, which means values have to be passed in, in the URL
																										
	@ResponseBody																						//accesses the database and returns information as a JSON object.
	public String userLogin(@RequestParam(value = "worker_login", required = true) String worker_login,
			@RequestParam(value = "worker_password", required = true) String worker_password)
			throws ClassNotFoundException, SQLException {
		System.out.println("workerLogin: " + worker_login + "\tworkerPassword: " + worker_password);
		EmployeeDataAccess employeeDataAccess = new EmployeeDataAccess(dbURL, user, password);
		List<Employee> empList = employeeDataAccess.getEmployeeList(worker_login, worker_password);
		String json = new Gson().toJson(empList);
		return json;
	}

}