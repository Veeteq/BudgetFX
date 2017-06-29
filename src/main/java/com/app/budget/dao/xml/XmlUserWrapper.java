package com.app.budget.dao.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.budget.model.User;

@XmlRootElement(name="users")
public class XmlUserWrapper {

	private List<User> users;
	
	@XmlElement(name="user")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users){
		this.users = users;
	}
}
