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

/**
 * 
 * @author Team 16
 *
 */

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
	
	/**
	 * Instantiates database
	 * 
	 * @param context Database information
	 */
	
	public UserModel(Context context) {
		this.database = new Database(context);
	}
	
	/**
	 * If there has been no user accounts set up, admin is the default
	 */
	
	public void initialize() {
		if (getUserByUsername("admin") == null) {
			addUser("admin", "pass123".hashCode());
		} 
		
	}
	
	/**
	 * Checks to see if the user has an account already
	 * 
	 * @return If user exists
	 */
	
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
	
	/**
	 * Accepts user's name and password on login
	 * 
	 * @return If login information is correct
	 */
	
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
	
	/**
	 * Returns user's username
	 * 
	 * @return Username
	 */
	
	@Override
	public User getUserByUsername(String username) {
		//User u = users.get(username);
		User u = database.getUser(username);
		return u;
	}

	
	/**
	 * Returns the group of users stored in the application
	 * 
	 * @return Users of application
	 */
	
	@Override
	public Collection<User> getUsers() {
		//return users.values();
		return database.getUsers();
	}

	/**
	 * Adds new user to database
	 */
	
	@Override
	public void addUser(String name, int password) {
		//users.put(name, new User(name, password));
		User u = new User(name, password);
		database.createUser(u);
		
	}

	/**
	 * Whoever is currently using the application is made the main focus
	 */
	
	public void setCurrentUser(User u) {
		//current = u;
		if (u == null) {
		    currentUser = "";
		} else {
		    currentUser = u.getUsername();
		}
	}
	
	/**
	 * Returns current user
	 * 
	 * @return Account in use
	 */
	
	public User getCurrent() {
		return database.getUser(currentUser);
		//return current;
	}
	
	/**
	 * If the user has changed, they are made the current user
	 */
	
	@Override	
	public void updateUser(User u) {
		database.updateUser(u);
	}
	
	/**
	 * Removes user from being the focus
	 */
	
	@Override
	public void deleteUser(User u) {
		if (u.getUsername().equals(currentUser)) {
			currentUser = null;
			currentAccount = null;
		}
		database.deleteUser(u);
	}
	
	/**
	 * Creates a user account and then adds a transaction
	 */
	
	@Override
	public void addUserAccount(String name, double balance, double interest, String parent) {
		UserAccount account = new UserAccount(name, balance, interest, parent);
		database.createUserAccount(account);
		Transaction t = new Transaction(balance, new Date(System.currentTimeMillis()), "deposit", "Initial Deposit", name);
		makeTransaction(t);
	}
	
	/**
	 * Gets an account from the main database
	 * 
	 * @return Account associated with name
	 */
	
	@Override
	public UserAccount getAccount(String name) {
		return database.getUserAccount(name);
	}
	
	/**
	 * Gets all accounts associated with a user
	 * 
	 * @return Accounts associated with main account holder
	 */
	
	@Override
	public List<UserAccount> getUserAccounts(String parent) {
		return database.getAccounts(parent);
	}
	
	/**
	 * Gets account in use
	 * 
	 * @return Account in use
	 */
	
	@Override
	public UserAccount getCurrentAccount() {
		return getAccount(currentAccount);
	}
	
	/**
	 * Sets account in use to current account
	 */
	
	@Override
	public void setCurrentAccount(UserAccount account) {
		currentAccount = account.getName();
	}

	/**
	 * Performs updates on current account
	 */
	
	@Override
	public void updateUserAccount(UserAccount account) {
		database.updateUserAccount(account);
		
	}

	/**
	 * Deletes current account from the database
	 */
	
	@Override
	public void deleteAccount(UserAccount account) {
		database.deleteAccount(account);
	}

	/**
	 * Makes a transaction
	 */
	
	@Override
	public void makeTransaction(Transaction t) {
		database.makeTransaction(t);
		
	}

	/**
	 * Puts the transactions into a list
	 * 
	 * @param parent Main account holder
	 * @return Transaction list
	 * @throws ParseException
	 */
	
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
	
	/**
	 * Gets transactions based on account holder
	 * 
	 * @param parent Main account holder
	 * @return Transactions associated with main account holder
	 * @throws ParseException
	 */
	
	public List<Transaction> getTransactionsForAccount(String parent) throws ParseException {
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.addAll(database.getTransactions(parent));
	    return transactions;
	}

	/**
	 * Gets transactions if it is a deposit
	 * 
	 * @return Deposits
	 * @throws ParseException
	 */
	
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
	
	/**
     * Gets transactions if it is a withdrawal
     * 
     * @return Withdrawals
     * @throws ParseException
     */
	
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

	/**
	 * Deletes transactions based on account holder
	 */
	
	@Override
	public void deleteTransactions(String parent) {
		database.deleteTransactions(parent);
		
	}

	/**
	 * Sets the start and end dates for reports   
	 */
	
	@Override
	public void setStartAndEndDates(String start, String end) {
		startDate = start;
		endDate = end;
		
	}
	
	/**
	 * Gets start date for reports
	 * 
	 * @return Start date or nothing
	 */
	
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
	
	/**
	 * Gets end date for reports
	 * 
	 * @return End date or nothing
	 */
	
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
