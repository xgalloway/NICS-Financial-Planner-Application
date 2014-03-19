package com.example.database;

import java.util.Date;
import java.util.List;

import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;

public interface DatabaseInterface {
	User createUser(String name, String password, String accounts);
	List<User> getUsers();
	
	UserAccount createUserAccount(String name, double balance, double interestRate, String transactions);
	List<UserAccount> getAccounts();
	
	Transaction makeTransaction(double amount, Date date, String type, String comments);
	List<Transaction> getTransactions();
}
