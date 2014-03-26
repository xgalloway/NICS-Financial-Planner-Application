package com.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.example.database.Database;

public class UserModel implements Model {
	private final String TAG = "UserModel";
	
	private static Map<String, User> users = new HashMap<String, User>();
	
	
	private static User current;
	private static String currentUser;
	private static String currentAccount;
	private static String startDate;
	private static String endDate;
	private Database database;
	
	public UserModel() {
		
	}
	
	public UserModel(Context context) {
		this.database = new Database(context);
	}
	
	public void initialize() {
		if (getUserByUsername("admin") == null) {
			addUser("admin", "pass123".hashCode());
		} 
		
	}
	
	@Override
	public boolean isValidUser(String name) {
		/*User u = users.get(name);
		if (u != null) {
			return true;
		}
		return false;
		*/
		User u = database.getUser(name);
		if (u != null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean acceptCredentials(String name, int password) {
		/*if (isValidUser(name)) {
			User u = users.get(name);
			int pass = u.getPassword();
			if (password == pass) {
				current = u;
				return true;
			}
		}
		return false;
		*/
		if (isValidUser(name)) {
			User u = database.getUser(name);
			int pass = u.getPassword();
			if (password == pass) {
				currentUser = name;
				return true;
			}
		} 
		return false;
	}
	
	@Override
	public User getUserByUsername(String username) {
		//User u = users.get(username);
		User u = database.getUser(username);
		return u;
	}

	@Override
	public Collection<User> getUsers() {
		//return users.values();
		return database.getUsers();
	}

	@Override
	public void addUser(String name, int password) {
		//users.put(name, new User(name, password));
		User u = new User(name, password);
		database.createUser(u);
		
	}

	public void setCurrentUser(User u) {
		//current = u;
		currentUser = u.getUsername();
	}
	
	public User getCurrent() {
		return database.getUser(currentUser);
		//return current;
	}
	
	@Override	
	public void updateUser(User u) {
		database.updateUser(u);
	}
	
	@Override
	public void deleteUser(User u) {
		if (u.getUsername().equals(currentUser)) {
			currentUser = null;
			currentAccount = null;
		}
		database.deleteUser(u);
	}
	
	@Override
	public void addUserAccount(String name, double balance, double interest, String parent) {
		UserAccount account = new UserAccount(name, balance, interest, parent);
		database.createUserAccount(account);
		Transaction t = new Transaction(balance, new Date(System.currentTimeMillis()), "deposit", "Initial Deposit", name);
		makeTransaction(t);
	}
	
	@Override
	public UserAccount getAccount(String name) {
		return database.getUserAccount(name);
	}
	
	@Override
	public List<UserAccount> getUserAccounts(String parent) {
		return database.getAccounts(parent);
	}
	
	@Override
	public UserAccount getCurrentAccount() {
		return getAccount(currentAccount);
	}
	
	@Override
	public void setCurrentAccount(UserAccount account) {
		currentAccount = account.getName();
	}

	@Override
	public void updateUserAccount(UserAccount account) {
		database.updateUserAccount(account);
		
	}

	@Override
	public void deleteAccount(UserAccount account) {
		database.deleteAccount(account);
	}


	@Override
	public void makeTransaction(Transaction t) {
		database.makeTransaction(t);
		
	}

	@Override
	public List<Transaction> getTransactions(String parent) throws ParseException {
		List<UserAccount> accounts = (List<UserAccount>) getUserAccounts(currentUser);
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < accounts.size(); ++i) {
			String p = accounts.get(i).getName();
			transactions.addAll(database.getTransactions(p));
		}
		return transactions;
	}

	@Override
	public List<Transaction> getDeposits() throws ParseException {
		List<Transaction> transactions = getTransactions(currentUser);
		List<Transaction> output = new ArrayList<Transaction>();
		for (int i = 0; i < transactions.size(); ++i) {
			Transaction t = transactions.get(i);
			if (t.getType().equals("deposit")) {
				output.add(t);
			}
		}
		return output;
	}

	@Override
	public List<Transaction> getWithdrawals() throws ParseException {
		List<Transaction> transactions = getTransactions(currentUser);
		List<Transaction> output = new ArrayList<Transaction>();
		for (int i = 0; i < transactions.size(); ++i) {
			Transaction t = transactions.get(i);
			if (t.getType().equals("withdrawal")) {
				output.add(t);
			}
		}
		return output;
	}

	@Override
	public void deleteTransactions(String parent) {
		database.deleteTransactions(parent);
		
	}

	@Override
	public void setStartAndEndDates(String start, String end) {
		startDate = start;
		endDate = end;
		
	}
	
	public Date getStartDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
