package com.example.activities;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


import com.example.model.Application;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.model.UserModel;
import com.example.nics_application.R;
import com.example.presenter.AccountViewPresenter;
import com.example.support.ClickListener;
import com.example.support.TransactionAdapter;

import com.example.view.AccountListView;
import com.example.view.AccountView;
/**
 * 
 * @author Team 16
 *
 * Deals with the ability for a user to see their account activity
 */
public class AccountViewActivity extends Activity implements AccountView, OnClickListener {
	
	
	private Button backButton;
	private Button transactionButton;
	private Button chartButton;
	private TextView balanceTextView;
	private ListView list;
	
	private AccountViewPresenter presenter;
	private ClickListener listener;
	
	/**
	 * Creates the account that will be viewed
	 * 
	 * @param savedInstanceState saves state of the application
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_view);
		Application app = Application.INSTANCE;
		initiateButtonsAndViews();
		presenter = new AccountViewPresenter(this, app.getModel());
		populateListView();
	}
	
	/**
	 * Allows user to go to previous page, view transactions
	 * and view the balance.
	 */
	
	public void initiateButtonsAndViews() {
		backButton = (Button)findViewById(R.id.backButton);
		backButton.setOnClickListener(this);
		
		transactionButton = (Button)findViewById(R.id.transactionButton);
		transactionButton.setOnClickListener(this);
		
		
		
		balanceTextView = (TextView)findViewById(R.id.balanceTextView);
		//balanceTextView.setText("text");
		
		list = (ListView) findViewById(R.id.listViewMain);
		
	}
	
	/**
	 * When the button is pushed, the information will be viewed
	 * 
	 * @param v The view that was clicked
	 */
	
	@Override
	public void onClick(View v) {
		presenter.onClick(v);
	}
	
	/**
     * Sets listener for the application
     * 
     * @param lsr Corresponds to the listener
     */
	
	@Override
	public void addSearchRequestNotifyCallback(ClickListener lsr) {
		listener = lsr;
	}
	
	/**
	 * Grabs the information from the user's account
	 * so it can then be displayed in a list view format.
	 * Changes the information to a string for easy viewing.
	 */
	
	private void populateListView() {
		UserModel model = Application.INSTANCE.getModel();
		UserAccount account = model.getCurrentAccount();
		balanceTextView.setText("" + "Balance: " + account.getBalance());
		List<Transaction> transactions;
		try {
			transactions = model.getTransactionsForAccount(account.getName());
			String[] items = {""};
			if (transactions.size() < 0) {
				//items = {""};
			} else {
				items = new String[transactions.size()];
			
				for (int i = 0; i < transactions.size(); i++) {
					items[i] = transactions.get(i).toString();
				}
			}

			ArrayAdapter<String> adapter = new TransactionAdapter(this, R.layout.item_view, items);
			list.setAdapter(adapter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_view, items);
		//list.setAdapter(adapter);
		
		}
	/**
	 * Allows user to add a transaction
	 */
	
	@Override
	public void addTransaction() {
		Intent i = new Intent(this, TransactionViewActivity.class);
		startActivity(i);
		finish();
		
	}

	/**
	 * Allows user to exit transaction screen
	 */
	
	@Override
	public void leaveTransactionScreen() {
		Intent i = new Intent(this, AccountListViewActivity.class);
		startActivity(i);
		finish();
		
	}
}	