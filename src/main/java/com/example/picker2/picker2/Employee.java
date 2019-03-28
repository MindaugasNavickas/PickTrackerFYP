package com.example.picker2.picker2;


public class Employee {

	private String usernameLogin;
	private String userPasswordLogin;
	private String userID;
	private String userRights;
	
	public String getUserRights() {
		return userRights;
	}

	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUsernameLogin() {
		System.out.println("returning username from Employee class " + usernameLogin);
		return usernameLogin;
	}

	public String getUserPasswordLogin() {
		return userPasswordLogin;
	}

	public void setUsernameLogin(String userNameLogin) {
		this.usernameLogin = userNameLogin;
	}

	public void setUserPasswordLogin(String userPasswordLogin) {
		this.userPasswordLogin = userPasswordLogin;
	}

	public Employee() {
	}

	public Employee(String userID, String userNameLogin, String userPasswordLogin, String userRights) {
		this.userID = userID;
		this.usernameLogin = userNameLogin;
		this.userPasswordLogin = userPasswordLogin;
		this.userRights = userRights;
	}
	public Employee(String userID, String userNameLogin, String userPasswordLogin) {
		this.userID = userID;
		this.usernameLogin = userNameLogin;
		this.userPasswordLogin = userPasswordLogin;
	}

}
