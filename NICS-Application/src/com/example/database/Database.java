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
	
	
	public Database(Context context) {
		dbHelper = new DatabaseHelper(context);
		open();
	}
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	@Override
	public void createUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, u.getUsername());
		values.put(dbHelper.COLUMN_PASSWORD, u.getPassword());
		db.insert(dbHelper.TABLE_NAME_USER, null, values);
	}
	
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
	
	@Override
	public void updateUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(dbHelper.COLUMN_NAME, u.getUsername());
		values.put(dbHelper.COLUMN_PASSWORD, u.getPassword());
		
		db.update(dbHelper.TABLE_NAME_USER, values, dbHelper.COLUMN_PASSWORD + " = ?", 
				new String[] { String.valueOf(u.getPassword()) });
	}
	
	@Override
	public void deleteUser(User u) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_USER, dbHelper.COLUMN_NAME + " = ?", 
				new String[] { String.valueOf(u.getUsername()) });
		deleteAccounts(u.getUsername());
	}

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
	
	@Override
	public void deleteAccount(UserAccount account) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_ACCOUNT, dbHelper.COLUMN_NAME + " = ?", 
				new String[] { String.valueOf(account.getName()) });
		deleteTransactions(account.getName());
	}
	
	@Override
	public void deleteAccounts(String parent) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		List<UserAccount> accounts = getAccounts(parent);
		for (UserAccount account : accounts) {
			deleteAccount(account);
		}
	}
	
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


	@Override
	public void deleteTransactions(String parent) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(dbHelper.TABLE_NAME_TRANS, dbHelper.COLUMN_PARENT + " = ?", 
				new String[] { String.valueOf(parent) });
		
	}

}
