package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author Team 16
 * 
 * Creates tables to hold information
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME_USER = "user";
	public static final String TABLE_NAME_ACCOUNT = "account";
	public static final String TABLE_NAME_TRANS = "trans";
	
	
	//USER
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PASSWORD = "password";
	//public static final String COLUMN_ACCOUNTS = "accounts";
	//public static final String COLUMN_REPORT = "report";
	
	
	//USER ACCOUNT
	public static final String COLUMN_BALANCE = "balance";
	public static final String COLUMN_INTEREST = "interest";
	public static final String COLUMN_PARENT = "parent";
	
	//TRANSACTION
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_COMMENTS = "comments";
	public static final String COLUMN_DATE = "date";
	
	//REPORT
	public static final String COLUMN_START = "start";
	public static final String COLUMN_END = "end";
	
	
	public static final String CREATE_USER_TABLE = "CREATE TABLE " 
			+ TABLE_NAME_USER + "(" 
			+ COLUMN_ID  + " integer primary key autoincrement,"
			+ COLUMN_NAME + " not null," 
			+ COLUMN_PASSWORD + " not null) ;";
	
	
	public static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE "
			+ TABLE_NAME_ACCOUNT + "(" 
			+ COLUMN_ID  + " integer primary key autoincrement,"
			+ COLUMN_NAME + " not null,"
			+ COLUMN_BALANCE + " not null,"
			+ COLUMN_INTEREST + " not null,"
			+ COLUMN_PARENT + " not null) ;";
	
	
	public static final String CREATE_TRANS_TABLE = "CREATE TABLE "
			+ TABLE_NAME_TRANS + "(" 
			+ COLUMN_ID  + " integer primary key autoincrement, "
			+ COLUMN_AMOUNT + " not null, "
			+ COLUMN_TYPE + " not null, "
			+ COLUMN_COMMENTS + " not null, "
			+ COLUMN_DATE + " not null, "
			+ COLUMN_PARENT + " not null) ;";
			
	
	
	private static final String DATABASE_NAME = "planner.db";
	private static final int DATABASE_VERSION = 1;
	
	/**
	 * @param context Information from the database
	 */
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * @param context Information from the data base
	 * @param name
	 * @param factory
	 * @param version
	 */
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * Creates tables for user information, account and transactions
	 */
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER_TABLE);
		
		db.execSQL(CREATE_ACCOUNT_TABLE);
		
		db.execSQL(CREATE_TRANS_TABLE);		

	}

	/**
	 * Replaces old table when new information is added
	 */
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANS);
	    onCreate(db);
	}

}
