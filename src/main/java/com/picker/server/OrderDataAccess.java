package com.picker.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDataAccess {

	private Connection connection;
	private String sql = "SELECT * FROM orderItemTable WHERE orderStatus = 'Incomplete' ORDER BY orderID ASC;";
	private final String ORDER_ID = "orderID";
	private final String ORDER_TYPE = "orderType";
	private final String ITEM_ID = "itemID";
	private final String CUSTOMER_ID = "customerID";

	public OrderDataAccess(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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

	public List<Order> getOrderList() throws SQLException {

		try (Statement statement = connection.createStatement(); ResultSet rSet = statement.executeQuery(sql);) {
			List<Order> orderList = new ArrayList<>();
			while (rSet.next()) {
				String orderID = rSet.getString(ORDER_ID);
				String orderType = rSet.getString(ORDER_TYPE);
				String itemID = rSet.getString(ITEM_ID);
				String customerID = rSet.getString(CUSTOMER_ID);
				Order order = new Order(orderID, orderType, itemID, customerID);
				orderList.add(order);

			}
			return orderList;
		}

	}

}
