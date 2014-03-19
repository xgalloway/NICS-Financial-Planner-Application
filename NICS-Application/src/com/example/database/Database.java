package com.example.database;

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

public class Database implements DatabaseInterface {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] userColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_NAME, 
			dbHelper.COLUMN_PASSWORD, dbHelper.COLUMN_ACCOUNTS, 
			dbHelper.COLUMN_REPORT };
	
	private String[] accountColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_NAME, 
			dbHelper.COLUMN_BALANCE, dbHelper.COLUMN_INTEREST };
	
	private String[] transColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_AMOUNT, 
			dbHelper.COLUMN_TYPE, dbHelper.COLUMN_COMMENTS, dbHelper.COLUMN_DATE };
	
	
	public Database(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	@Override
	public User createUser(String name, String password, String accounts) {
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, name);
		values.put(dbHelper.COLUMN_PASSWORD, password);
		values.put(dbHelper.COLUMN_ACCOUNTS, accounts);
		long insertId = database.insert(dbHelper.TABLE_NAME_USER, null, values);
		
		Cursor cursor = database.query(dbHelper.TABLE_NAME_USER, userColumns, 
				dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		
		User user = cursorToUser(cursor);
		
		cursor.close();
		return user;
	}
	
	private User cursorToUser(Cursor cursor) {
		String accounts = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNTS));
		User user = new User(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME)),
				Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PASSWORD))));
		if (accounts.equals("null")) {
			return user;
		}
		String[] accountNames = accounts.split(",");
		List<UserAccount> accountList = getAccounts();
		for (int i = 0; i < accountList.size(); ++i) {
			for (int j = 0; j < accountNames.length; ++j) {
				if (accountNames[j].equals(accountList.get(i).getName())) {
					user.addAccount(accountList.get(i));
				}
			}
		}
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		Cursor cursor = database.query(dbHelper.TABLE_NAME_USER, userColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User u = cursorToUser(cursor);
			users.add(u);
			cursor.moveToNext();
		}
		cursor.close();
		return users;
	}

	@Override
	public UserAccount createUserAccount(String name, double balance,
			double interestRate, String transactions) {
		
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, name);
		values.put(dbHelper.COLUMN_BALANCE, balance);
		values.put(dbHelper.COLUMN_INTEREST, interestRate);
		long insertId = database.insert(dbHelper.TABLE_NAME_ACCOUNT, null, values);
		
		Cursor cursor = database.query(dbHelper.TABLE_NAME_ACCOUNT, accountColumns,
				dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		
		UserAccount userAccount = cursorToAccount(cursor);
		return null;
	}

	private UserAccount cursorToAccount(Cursor cursor) {
		return null;
	}
	@Override
	public List<UserAccount> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction makeTransaction(double amount, Date date, String type,
			String comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

}
