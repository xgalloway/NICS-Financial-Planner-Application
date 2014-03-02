package com.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Model {
	private final String TAG = "UserModel";
	private static Map<String, User> users = new HashMap<String, User>();
	static {
		users.put("admin", new User("admin", "pass123".hashCode()));
	}
	
	private static User current;
	
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
	public boolean acceptCredentials(String name, int password) {
		if (isValidUser(name)) {
			User u = users.get(name);
			int pass = u.getPassword();
			if (password == pass) {
				current = u;
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
	public void addUser(String name, int password) {
		users.put(name, new User(name, password));
		
	}

	public void setCurrentUser(User u) {
		current = u;
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
