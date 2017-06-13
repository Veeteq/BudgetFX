package com.app.budget.model;

public class User {

	private long userId;
	private String userName;
	private String userType;
	private String userDescription;

	public long getUserId(){
		return userId;
	}
	
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	@Override
	public String toString() {
		return userName;
	}
}
