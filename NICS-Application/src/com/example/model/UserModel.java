package com.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Model {
	private final String TAG = "UserModel";
	private static Map<String, User> users = new HashMap<String, User>();
	static {
		users.put("admin", new User("admin", "pass123"));
	}
	public UserModel() {
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

//	@Override
//	public void mergeModel(Model m) {
//		ArrayList<User> list = new ArrayList<User>(m.getUsers());
//		for (int i = 0; i < m.getUsers().size(); ++i) {
//			User u = (User) list.get(i);
//			if (getUserByUsername(u.getUsername()) == null) {
//				addUser(u.getUsername(), u.getPassword());
//			}
//		}
//		
//	}

}
