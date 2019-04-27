package com.picker.server.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.picker.server.Order;
import com.picker.server.OrderDataAccess;

@RestController
public class PicksGetController {
	private final String dbURL = "jdbc:mysql://localhost:3306/picksDatabase";
	private final String user = "root";
	private final String password = "Mindziukas1234";

	@RequestMapping(method = RequestMethod.GET, value = "/getPicks", produces = "application/json")

	@ResponseBody
	public String getPicks() throws ClassNotFoundException, SQLException {
		OrderDataAccess orderDataAccess = new OrderDataAccess(dbURL, user, password);
		List<Order> ordersList = orderDataAccess.getOrderList();
		String json = new Gson().toJson(ordersList);
		return json;
	}
}
