package com.example.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;

/**
 * 
 * @author Team 16
 *
 * The structure of the application
 */

public class Database implements DatabaseInterface {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] userColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_NAME, 
			dbHelper.COLUMN_PASSWORD,  };
	
	private String[] accountColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_NAME, 
			dbHelper.COLUMN_BALANCE, dbHelper.COLUMN_INTEREST, 
			dbHelper.COLUMN_PARENT };
	
	private String[] transColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_AMOUNT, 
			dbHelper.COLUMN_TYPE, dbHelper.COLUMN_COMMENTS, dbHelper.COLUMN_PARENT, 
			dbHelper.COLUMN_DATE };
	
	/**
	 * 
	 * Creates a layout and table for information to be stored
	 * 
	 * @param context Information being held
	 */
	
	public Database(Context context) {
		dbHelper = new DatabaseHelper(context);
		open();
	}
	
	/**
	 * Allows the table to be written into
	 */
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Closes the database once the information is no longer
	 * being written into it
	 */
	
	public void close() {
		dbHelper.close();
	}
	
	/**
	 * Adds user information to the database
	 * includes username and password.
	 * 
	 * @param u User information
	 */
	
	@Override
	public void createUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, u.getUsername());
		values.put(dbHelper.COLUMN_PASSWORD, u.getPassword());
		db.insert(dbHelper.TABLE_NAME_USER, null, values);
	}
	
	/**
	 * Based on the saved database and text a user has entered,
	 * the username will be retrieved.
	 * 
	 * @param name Name of user
	 */
	
	@Override
	public User getUser(String name) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_NAME_USER + 
				" WHERE " + dbHelper.COLUMN_NAME + " = " + "'" + name + "'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null && cursor.moveToFirst()) {
				User u = new User(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME)), 
					cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PASSWORD)));
				return u;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a list of all the users
	 * 
	 * @return The system's users
	 */
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_NAME_USER;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor == null) {
			return users;
		}
		
		if (cursor.moveToFirst()) {
			while (cursor != null) {
				User u = new User(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME)), 
						cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PASSWORD)));
				users.add(u);
				if (cursor.moveToNext() != true) {
					break;
				}
			}
		}
		
		return users;
	}
	
	/**
	 * If a user changes their username or password,
	 * the table where all the information is stored will be updated
	 * 
	 * @param u User information
	 */
	
	@Override
	public void updateUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, u.getUsername());
		values.put(dbHelper.COLUMN_PASSWORD, u.getPassword());
		
		db.update(dbHelper.TABLE_NAME_USER, values, dbHelper.COLUMN_PASSWORD + " = ?", 
				new String[] { String.valueOf(u.getPassword()) });
	}
	
	/**
	 * Deletes information associated with a user account
	 * 
	 * @param u User information
	 */
	
	@Override
	public void deleteUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_USER, dbHelper.COLUMN_NAME + " = ?", 
				new String[] { String.valueOf(u.getUsername()) });
		deleteAccounts(u.getUsername());
	}

	/**
	 * Puts values associated with a user account in its
	 * respective column
	 * 
	 * @param account A user's account
	 */
	
	@Override
	public void createUserAccount(UserAccount account) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, account.getName());
		values.put(dbHelper.COLUMN_BALANCE, account.getBalance());
		values.put(dbHelper.COLUMN_INTEREST, account.getRate());
		values.put(dbHelper.COLUMN_PARENT, account.getParent());
		database.insert(dbHelper.TABLE_NAME_ACCOUNT, null, values);
		
	}
	
	/**
	 * Returns information associated with an account
	 * 
	 * @param name Name of user
	 * @return Account information or nothing if invalid
	 */
	
	@Override
	public UserAccount getUserAccount(String name) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String query = "SELECT  * FROM " + dbHelper.TABLE_NAME_ACCOUNT + 
				" WHERE " + dbHelper.COLUMN_NAME + " = " + "'" + name + "'";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			cursor.moveToFirst();
			UserAccount account = new UserAccount(
					cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME)),
					cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_BALANCE)),
					cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_INTEREST)),
					cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT)));
			return account;
		} else {
			return null;
		}
	}
	
	/**
	 * Retrieves the accounts from the database
	 * 
	 * @param parent Main account information
	 * @return accounts associated with parent
	 */

	@Override
	public List<UserAccount> getAccounts(String parent) {
		List<UserAccount> accounts = new ArrayList<UserAccount>();
		String query = "SELECT  * FROM " + dbHelper.TABLE_NAME_ACCOUNT + 
				" WHERE " + dbHelper.COLUMN_PARENT + " = " + "'" + parent + "'";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			while (cursor != null) {
				
				String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME));
				double balance = cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_BALANCE));
				double interest = cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_INTEREST));
				String p = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT));
				UserAccount account = new UserAccount(name, balance, interest, p);
				/*UserAccount account = new UserAccount(
						cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME)),
						cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_BALANCE)),
						cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_INTEREST)),
						cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT)));
						*/
				accounts.add(account);
				if (cursor.moveToNext() != true) {
					break;
				}
			}
		}
		return accounts;
	}

	/**
	 * Deletes old information and then rewrites it if
	 * any changes have been made
	 * 
	 * @param account User account
	 */
	
	@Override
	public void updateUserAccount(UserAccount account) {
		/*
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, account.getName());
		values.put(dbHelper.COLUMN_BALANCE, account.getBalance());
		values.put(dbHelper.COLUMN_INTEREST, account.getRate());
		values.put(dbHelper.COLUMN_PARENT, account.getParent());
		System.out.println(account.getBalance());
		db.update(dbHelper.TABLE_NAME_ACCOUNT, values, dbHelper.COLUMN_BALANCE + " = ?", 
				new String[] { String.valueOf(account.getBalance()) });
		*/
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_ACCOUNT, dbHelper.COLUMN_NAME + " = ?", 
				new String[] { String.valueOf(account.getName()) });
		createUserAccount(account);
	}
	
	/**
	 * Deletes transaction associated with an user account
	 * 
	 * @param account User account
	 */
	
	@Override
	public void deleteAccount(UserAccount account) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_ACCOUNT, dbHelper.COLUMN_NAME + " = ?", 
				new String[] { String.valueOf(account.getName()) });
		deleteTransactions(account.getName());
	}
	
	/**
	 * Deletes account from list of accounts
	 * 
	 * @param parent Main account holder
	 */
	
	@Override
	public void deleteAccounts(String parent) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		List<UserAccount> accounts = getAccounts(parent);
		for (UserAccount account : accounts) {
			deleteAccount(account);
		}
	}
	
	/**
	 * Adds transaction to the database
	 * 
	 * @param t Transaction information
	 */
	
	@Override
	public void makeTransaction(Transaction t) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_AMOUNT, t.getAmount());
		values.put(dbHelper.COLUMN_TYPE, t.getType());
		values.put(dbHelper.COLUMN_COMMENTS, t.getComments());
		values.put(dbHelper.COLUMN_DATE, t.getDateString());
		values.put(dbHelper.COLUMN_PARENT, t.getParent());
		db.insert(dbHelper.TABLE_NAME_TRANS, null, values);
		
	}

	/**
	 * Creates a running list of transactions based on date
	 * 
	 * @param parent Main account holder
	 * @return transactions associated or nothing if invalid
	 */
	
	@Override
	public List<Transaction> getTransactions(String parent) throws ParseException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		String query = "SELECT  * FROM " + dbHelper.TABLE_NAME_TRANS +
				" WHERE " + dbHelper.COLUMN_PARENT + " = " + "'" + parent + "'";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor != null) {
					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
					Date date = sdf.parse(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DATE)));
					Transaction t = new Transaction( 
							cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_AMOUNT)),
							date,
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_TYPE)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_COMMENTS)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT)));
					transactions.add(t);
					if (cursor.moveToNext() != true) {
						break;
					}
				}
			}
			return transactions;
		} else {
			return null;
		}
	}

    /**
     * Creates a running list of only deposits
     * 
     * @return All deposits or nothing if invalid
     */
	
	@Override
	public List<Transaction> getDeposits() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		String query = "SELECT  * FROM " + dbHelper.TABLE_NAME_TRANS +
				" WHERE " + dbHelper.COLUMN_TYPE + " = deposit" ;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor != null) {
					
					Date date = new Date(Date.parse(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DATE))));
					Transaction t = new Transaction( 
							cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_AMOUNT)),
							date,
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_TYPE)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_COMMENTS)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT)));
					transactions.add(t);
					if (cursor.moveToNext() != true) {
						break;
					}
				}
			}
			return transactions;
		} else {
			return null;
		}
	}

    /**
     * Creates a running list of only withdrawals
     * 
     * @return All withdrawals or nothing if invalid
     */
	
	@Override
	public List<Transaction> getWithdrawals() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		String query = "SELECT  * FROM " + dbHelper.TABLE_NAME_TRANS + 
				" WHERE " + dbHelper.COLUMN_TYPE + " = withdrawal" ;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor != null) {
					
					Date date = new Date(Date.parse(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DATE))));
					Transaction t = new Transaction( 
							cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_AMOUNT)),
							date,
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_TYPE)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_COMMENTS)),
							cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PARENT)));
					transactions.add(t);
					if (cursor.moveToNext() != true) {
						break;
					}
				}
			}
			return transactions;
		} else {
			return null;
		}
	}

	/**
	 * Deletes transactions associated with an account
	 * 
	 * @param parent Main account holder
	 */
	
	@Override
	public void deleteTransactions(String parent) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_TRANS, dbHelper.COLUMN_PARENT + " = ?", 
				new String[] { String.valueOf(parent) });
		
	}

}
