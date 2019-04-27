package com.picker.server.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.picker.server.Item;
import com.picker.server.ItemDataAccess;

@RestController
public class GetItemController {
	private final String dbURL = "jdbc:mysql://localhost:3306/picksDatabase";
	private final String user = "root";
	private final String password = "Mindziukas1234";

	@RequestMapping(method = RequestMethod.GET, value = "/getItems", produces = "application/json")

	@ResponseBody
	public String getItems(@RequestParam(value = "itemID", required = true) String itemID) throws ClassNotFoundException, SQLException {
		ItemDataAccess itemDataAccess = new ItemDataAccess(dbURL, user, password);
		List<Item> itemsList = itemDataAccess.getItem(itemID);
		String json = new Gson().toJson(itemsList);
		return json;
	}
}
