package com.example.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Model {
	private final String TAG = "UserModel";
	private Map<String, User> users;
	
	public UserModel() {
		this.users = new HashMap<String, User>();
		users.put("admin", new User("admin", "pass123"));
	}
	
	@Override
	public boolean isValidUser(String name) {
		User u = users.get(name);
		if (u != null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean acceptCredentials(String name, String password) {
		if (isValidUser(name)) {
			User u = users.get(name);
			String pass = u.getPassword();
			if (password.equals(pass)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User u = users.get(username);
		return u;
	}

	@Override
	public Collection<User> getUsers() {
		return users.values();
	}

	@Override
	public void addUser(String name, String password) {
		users.put(name, new User(name, password));
		
	}

}
