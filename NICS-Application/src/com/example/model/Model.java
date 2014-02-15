package com.example.model;

import java.util.Collection;

public interface Model {
	void addUser(final String name, final String password);
	boolean isValidUser(final String name);
	boolean acceptCredentials(final String name, final String password);
	User getUserByUsername(final String username);
	Collection<User> getUsers();
}
