package com.example.database;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;

public interface DatabaseInterface {
	void createUser(User u);
	User getUser(String name);
	List<User> getUsers();
	void updateUser(User u);
	void deleteUser(User u);
	
	void createUserAccount(UserAccount account);
	UserAccount getUserAccount(String name);
	List<UserAccount> getAccounts(String name);
	void updateUserAccount(UserAccount account);
	void deleteAccount(UserAccount account);
	void deleteAccounts(String parent);
	
	void makeTransaction(Transaction t);
	List<Transaction> getTransactions(String parent) throws ParseException;
	List<Transaction> getDeposits();
	List<Transaction> getWithdrawals();
	void deleteTransactions(String parent);
}
