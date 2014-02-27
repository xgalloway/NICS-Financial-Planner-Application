package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class ListOfUsers{
	private static List<User> userList = new ArrayList<User>();
	
	public static void add(User user) {
		userList.add(user);
	}

	public static User getUser(int i) {
		return userList.get(i);
	}
}