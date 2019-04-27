package com.picker.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDataAccess {
	private Connection connection;
	private String sql = "SELECT * FROM ItemsTable WHERE itemID = ";
	private final String ITEM_ID = "itemID";
	private final String ITEM_NAME = "itemName";
	private final String ITEM_DESCRIPTION = "itemDescription";
	private final String ITEM_PRICE = "itemPrice";

	public ItemDataAccess(String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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

	public List<Item> getItem(String sentItemID) throws SQLException {

		try (Statement statement = connection.createStatement(); ResultSet rSet = statement.executeQuery(sql+sentItemID+";");) {
			List<Item> itemList = new ArrayList<>();
			while (rSet.next()) {
				String itemID = rSet.getString(ITEM_ID);
				String itemName = rSet.getString(ITEM_NAME);
				String itemDescription = rSet.getString(ITEM_DESCRIPTION);
				String itemPrice = rSet.getString(ITEM_PRICE);
				Item item = new Item(itemID, itemName, itemDescription, itemPrice);
				itemList.add(item);

			}
			return itemList;
		}

	}

}
