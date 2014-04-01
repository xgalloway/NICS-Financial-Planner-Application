package com.example.model;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Team 16
 * 
 * Creates interface for the model
 *
 */

public interface Model {
	void addUser(final String name, final int password);
	boolean isValidUser(final String name);
	boolean acceptCredentials(final String name, final int password);
	User getUserByUsername(final String username);
	Collection<User> getUsers();
	User getCurrent();
	void setCurrentUser(User u);
	void updateUser(User u);
	void deleteUser(User u);
	
	
	void addUserAccount(String name, double balance, double interest, String parent);
	UserAccount getAccount(String name);
	List<UserAccount> getUserAccounts(String parent);
	UserAccount getCurrentAccount();
	void setCurrentAccount(UserAccount account);
	void updateUserAccount(UserAccount account);
	void deleteAccount(UserAccount account);
	
	
	void makeTransaction(Transaction t);
	List<Transaction> getTransactions(String parent) throws ParseException;
	List<Transaction> getDeposits() throws ParseException;
	List<Transaction> getWithdrawals() throws ParseException;
	void deleteTransactions(String parent);
	
	void setStartAndEndDates(String start, String end);
	
	
}
